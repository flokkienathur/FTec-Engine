package net.apryx.graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL15;

public class VBO {
	
	public static final int ARRAY_BUFFER = GL15.GL_ARRAY_BUFFER;
	public static final int ELEMENT_ARRAY_BUFFER = GL15.GL_ELEMENT_ARRAY_BUFFER;

	public static final int STREAM_DRAW = GL15.GL_STREAM_DRAW;
	public static final int STATIC_DRAW = GL15.GL_STATIC_DRAW;
	public static final int DYNAMIC_DRAW = GL15.GL_DYNAMIC_DRAW;
	
	private int id;
	private int type;
	
	public VBO(int type){
		this.type = type;
		id = GL15.glGenBuffers();
	}
	
	public void bufferData(FloatBuffer buffer){
		bufferData(buffer, STATIC_DRAW);
	}
	
	public void bufferData(FloatBuffer buffer, int drawManner){
		bind();
		GL15.glBufferData(type, buffer, drawManner);
		unbind();
	}
	
	public void bufferData(IntBuffer buffer){
		bufferData(buffer, STATIC_DRAW);
	}
	
	public void bufferData(IntBuffer buffer, int drawManner){
		bind();
		GL15.glBufferData(type, buffer, drawManner);
		unbind();
	}
	
	
	public void bind(){
		GL15.glBindBuffer(type, id);
	}
	
	public void unbind(){
		GL15.glBindBuffer(type, 0);
	}
	
	public void dispose(){
		GL15.glDeleteBuffers(id);
	}
}
