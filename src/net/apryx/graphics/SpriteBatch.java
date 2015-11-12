package net.apryx.graphics;

import java.nio.FloatBuffer;

import net.apryx.ftec.graphics.Renderer;
import net.apryx.ftec.graphics.ShaderConstants;
import net.apryx.utils.BufferUtils;

public class SpriteBatch extends Batch{

	private FloatBuffer vertexBuffer;
	private FloatBuffer colorBuffer;
	private FloatBuffer uvBuffer;
	private FloatBuffer normalBuffer;
	
	private boolean drawing = false;
	private int idx = 0;
	private int length = 0;
	
	private Renderer renderer;
	private Texture texture;
	
	private float[] normal = {0,0,1};
	private float[] color = {1,1,1,1};
	private float[] uv = {0,0};
	
	public SpriteBatch(Renderer renderer, int vertexCount){
		super();
		
		this.renderer = renderer;

		vertexBuffer = BufferUtils.createFloatBuffer(vertexCount * 3);
		colorBuffer = BufferUtils.createFloatBuffer(vertexCount * 4);
		uvBuffer = BufferUtils.createFloatBuffer(vertexCount * 2);
		normalBuffer = BufferUtils.createFloatBuffer(vertexCount * 3);

		vao.enableVertexAttribArray(ShaderConstants.POSITION_INDEX);
		vao.enableVertexAttribArray(ShaderConstants.COLOR_INDEX);
		vao.enableVertexAttribArray(ShaderConstants.UV_INDEX);
		vao.enableVertexAttribArray(ShaderConstants.NORMAL_INDEX);
		
		vao.setPointer(ShaderConstants.POSITION_INDEX, vertices, 3, 0, 0);
		vao.setPointer(ShaderConstants.COLOR_INDEX, colors, 4, 0, 0);
		vao.setPointer(ShaderConstants.UV_INDEX, uvs, 2, 0, 0);
		vao.setPointer(ShaderConstants.NORMAL_INDEX, normals, 3, 0, 0);
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer renderer) {
		//TODO switching while drawing
		this.renderer = renderer;
	}
	
	public boolean isDrawing() {
		return drawing;
	}

	public void begin(){
		if(drawing)
			return;
		drawing = true;
		idx = 0;
	}
	
	public void normal(float x, float y, float z){
		normal[0] = x;
		normal[1] = y;
		normal[2] = z;
	}
	
	public void uv(float x, float y){
		uv[0] = x;
		uv[1] = y;
	}
	
	public void color(float r, float g, float b){
		color(r,g,b,1);
	}
	
	public void color(float r, float g, float b, float a){
		color[0] = r;
		color[1] = g;
		color[2] = b;
		color[3] = a;
	}
	
	public void vertex(float x, float y, float z){
		vertexBuffer.put(x).put(y).put(z);
		colorBuffer.put(color);
		uvBuffer.put(uv);
		normalBuffer.put(normal);
		idx++;
	}
	
	public void flush(){
		if(!drawing)
			return;
		
		vertexBuffer.flip();
		colorBuffer.flip();
		uvBuffer.flip();
		normalBuffer.flip();
		
		int vbodraw = VBO.STATIC_DRAW;
		
		//update vbo's
		vertices.bufferData(vertexBuffer, vbodraw);
		colors.bufferData(colorBuffer, vbodraw);
		uvs.bufferData(uvBuffer, vbodraw);
		normals.bufferData(normalBuffer, vbodraw);
		
		length = idx;
		idx = 0;
		
		renderer.draw(this, texture);
	}
	
	public void end(){
		if(drawing)
			flush();
		
		drawing = false;
	}

	@Override
	public void draw() {
		vao.drawArrays(GL.TRIANGLES, 0, length);
	}
}
