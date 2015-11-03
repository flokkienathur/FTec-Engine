package net.apryx.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class VAO {
	
	private int id;
	
	public VAO(){
		id = GL30.glGenVertexArrays();
	}
	
	public void bind(){
		GL30.glBindVertexArray(id);
	}
	
	public void unbind(){
		GL30.glBindVertexArray(0);
	}
	
	public void setPointer(int slot, VBO vbo, int size, int stride, long offset){
		vbo.bind();
		
		//fill the VAO slot with this vbo
		GL20.glVertexAttribPointer(slot, size, GL11.GL_FLOAT, false, stride, offset);
		
		vbo.unbind();
	}
	
	public void dispose(){
		GL30.glDeleteVertexArrays(id);
	}
}
