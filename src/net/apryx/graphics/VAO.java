package net.apryx.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class VAO {
	
	private int id;
	private VBO indices;
	
	public VAO(){
		id = GL30.glGenVertexArrays();
	}
	
	public void bind(){
		GL30.glBindVertexArray(id);
	}
	
	public void unbind(){
		GL30.glBindVertexArray(0);
	}
	
	public void setIndices(VBO indices){
		this.indices = indices;
		bind();
		indices.bind();
		unbind();
		indices.unbind();
	}
	
	public void setPointer(int slot, VBO vbo, int size, int stride, long offset){
		bind();
		vbo.bind();
		
		//fill the VAO slot with this vbo
		GL20.glVertexAttribPointer(slot, size, GL11.GL_FLOAT, false, stride, offset);
		
		vbo.unbind();
		unbind();
	}
	
	public void enableVertexAttribArray(int slot){
		bind();
		GL20.glEnableVertexAttribArray(slot);
		unbind();
	}
	
	public void drawArrays(int mode, int start, int count){
		bind();
		GL11.glDrawArrays(mode, start, count);
		unbind();
	}
	
	public void drawElements(int mode, int start, int count){
		bind();
		//because intel gpu's dont save the element arrays
		if(indices != null)
			indices.bind();
		GL11.glDrawElements(mode, count, GL11.GL_UNSIGNED_INT, start * 4);
		unbind();
	}
	
	public void dispose(){
		GL30.glDeleteVertexArrays(id);
	}
}
