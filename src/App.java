import java.io.File;
import java.io.FileNotFoundException;
import java.nio.FloatBuffer;
import java.util.Scanner;

import net.apryx.graphics.Batch;
import net.apryx.graphics.GL;
import net.apryx.graphics.Shader;
import net.apryx.graphics.ShaderProgram;
import net.apryx.graphics.Texture;
import net.apryx.graphics.VAO;
import net.apryx.graphics.VBO;
import net.apryx.graphics.Window;
import net.apryx.utils.BufferUtils;

public class App {
	
	public static void main(String[] args) throws InterruptedException {
		Window window = new Window(1024, 768);
		window.setVisible(true);
		
		Shader vertexShader = new Shader(Shader.VERTEX_SHADER);
		vertexShader.source(new File("res/default_vertex.glsl"));
		vertexShader.compile();
		
		Shader fragmentShader = new Shader(Shader.FRAGMENT_SHADER);
		fragmentShader.source(new File("res/default_fragment.glsl"));
		fragmentShader.compile();
		
		Shader otherFragment = new Shader(Shader.FRAGMENT_SHADER);
		otherFragment.source(new File("res/test_fragment.glsl"));
		otherFragment.compile();
		
		Shader otherVertex = new Shader(Shader.VERTEX_SHADER);
		otherVertex.source(new File("res/test_vertex.glsl"));
		otherVertex.compile();

		ShaderProgram shaderProgram = new ShaderProgram();
		ShaderProgram otherShader = new ShaderProgram();
		
		shaderProgram.attach(fragmentShader);
		shaderProgram.attach(vertexShader);

		otherShader.attach(otherVertex);
		otherShader.attach(otherFragment);

		shaderProgram.bindAttributeLocation(Batch.POSITION_INDEX, "position");
		shaderProgram.bindAttributeLocation(Batch.COLOR_INDEX, "color");
		shaderProgram.bindAttributeLocation(Batch.UV_INDEX, "uv");
		
		otherShader.bindAttributeLocation(Batch.POSITION_INDEX, "position");
		otherShader.bindAttributeLocation(Batch.COLOR_INDEX, "color");
		otherShader.bindAttributeLocation(Batch.UV_INDEX, "uv");
		
		shaderProgram.link();
		otherShader.link();
		
		
		VBO vertices = new VBO(VBO.ARRAY_BUFFER);
		VBO colors = new VBO(VBO.ARRAY_BUFFER);
		VBO uvs = new VBO(VBO.ARRAY_BUFFER);
		
		vertices.bufferData(BufferUtils.createFloatBuffer(new float[]{
				1,0,
				0,1,
				-1,0,
				0,-1
		}));
		
		colors.bufferData(BufferUtils.createFloatBuffer(new float[]{
				1,1,1,
				1,1,1,
				1,1,1,
				1,1,1,
		}));
		
		uvs.bufferData(BufferUtils.createFloatBuffer(new float[]{
				0,0,
				1,0,
				1,1,
				0,1
		}));
		
		VBO indices = new VBO(VBO.ELEMENT_ARRAY_BUFFER);
		indices.bufferData(BufferUtils.createIntBuffer(new int[]{
				0,1,2,
				0,2,3
		}));

		VAO vao = new VAO();
		
		vao.setPointer(Batch.POSITION_INDEX, vertices, 2, 0, 0);
		vao.setPointer(Batch.COLOR_INDEX, colors, 3, 0, 0);
		vao.setPointer(Batch.UV_INDEX, uvs, 2, 0, 0);
		
		vao.setIndices(indices);
		
		vao.enableVertexAttribArray(Batch.POSITION_INDEX);
		vao.enableVertexAttribArray(Batch.COLOR_INDEX);
		vao.enableVertexAttribArray(Batch.UV_INDEX);
		
		FloatBuffer textureData = BufferUtils.createFloatBuffer(new float[]{
				0.5f, 0.5f, 0.5f,   1.0f, 1.0f, 1.0f,
			    1.0f, 1.0f, 1.0f,   0.5f, 0.5f, 0.5f
		});
		
		
		Texture t = new Texture();
		
		t.bind();
		t.texParameter(Texture.WRAP_S, Texture.CLAMP);
		t.texParameter(Texture.WRAP_T, Texture.CLAMP);
		t.texParameter(Texture.MIN_FILTER, Texture.NEAREST);
		t.texParameter(Texture.MAG_FILTER, Texture.NEAREST);
		t.setData(textureData, Texture.RGB);
		
		t.unbind();
		
		//set the clear color to black, with 100% alpha
		GL.clearColor(0, 0, 0, 1);
		
		//clear the screen
		GL.clear(GL.COLOR_BUFFER_BIT);
		
		long previous = System.nanoTime();
		long sum = 0;
		int fps = 0;
		
		while(!window.isCloseRequested()){
			long now = System.nanoTime();
			
			long delta = now - previous;
			sum += delta;
			if(sum > 1000000000){
				sum -= 1000000000;
				System.out.println("FPS: " + fps);
				fps = 0;
			}

			previous = now;
			
			window.pollEvents();

			t.bind();
			otherShader.use();
			vao.draw(GL.TRIANGLES, 0, 6);
			
			fps++;
			
			window.swap();
			window.sleep(16);
		}
		
		//delete the resources
		otherShader.dispose();
		otherFragment.dispose();
		otherVertex.dispose();
		shaderProgram.dispose();
		vertexShader.dispose();
		fragmentShader.dispose();
		
		vao.dispose();
		
		vertices.dispose();
		uvs.dispose();
		colors.dispose();
		
		GL.checkError();
		
		//destroy the window
		window.destroy();
	}
	
	public static String fileToString(String path){
		File file = new File(path);
		StringBuilder out = new StringBuilder();
		Scanner s = null;
		try {
			s = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(s.hasNextLine()){
			out.append(s.nextLine());
		}
		s.close();
		return out.toString();
	}
}
