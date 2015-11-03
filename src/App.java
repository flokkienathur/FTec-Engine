import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.lwjgl.opengl.GL11;

import net.apryx.graphics.GL;
import net.apryx.graphics.Shader;
import net.apryx.graphics.ShaderProgram;
import net.apryx.graphics.VAO;
import net.apryx.graphics.VBO;
import net.apryx.graphics.Window;
import net.apryx.utils.BufferUtils;

public class App {
	
	public static void main(String[] args) throws InterruptedException {
		Window window = new Window(1024, 768);
		window.setVisible(true);
		
		System.out.println(GL11.glGetString(GL11.GL_VERSION));
		
		Shader vertexShader = new Shader(Shader.VERTEX_SHADER);
		vertexShader.source(new File("res/default_vertex.glsl"));
		vertexShader.compile();
		
		Shader fragmentShader = new Shader(Shader.FRAGMENT_SHADER);
		fragmentShader.source(new File("res/default_fragment.glsl"));
		fragmentShader.compile();
		
		ShaderProgram shaderProgram = new ShaderProgram();
		
		shaderProgram.attach(fragmentShader);
		shaderProgram.attach(vertexShader);

		shaderProgram.bindAttributeLocation(0, "position");
		shaderProgram.bindAttributeLocation(1, "color");
		
		shaderProgram.link();
		
		shaderProgram.use();
		
		VBO vertices = new VBO(VBO.ARRAY_BUFFER);
		VBO colors = new VBO(VBO.ARRAY_BUFFER);
		
		vertices.bind();
		vertices.bufferData(BufferUtils.createFloatBuffer(new float[]{
				-0.5f,-0.5f,
				-0.5f,0.5f,
				0.5f,0.5f,
				0.5f,-0.5f,
		}));
		vertices.unbind();
		
		colors.bind();
		colors.bufferData(BufferUtils.createFloatBuffer(new float[]{
				1,0,0,
				0,1,0,
				0,0,1,
				1,0,1,
		}));
		colors.unbind();

		VAO vao = new VAO();
		vao.bind();
		vao.setPointer(0, vertices, 2, 0, 0);
		vao.setPointer(1, colors, 3, 0, 0);
		
		//set the clear color to black, with 100% alpha
		GL.clearColor(0, 0, 0, 1);
		
		//clear the screen
		GL.clear(GL.COLOR_BUFFER_BIT);

		shaderProgram.enableVertexAttribArray(0);
		shaderProgram.enableVertexAttribArray(1);		
		
		GL.drawArrays(GL.QUADS, 0, 4);

		//swap the back buffer with the front buffer
		window.swap();
		
		//wait one second
		window.sleep(1000);
		
		//delete the resources
		shaderProgram.dispose();
		vertexShader.dispose();
		fragmentShader.dispose();
		
		vao.dispose();
		vertices.dispose();
		
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
