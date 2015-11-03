package net.apryx.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class ShaderProgram {
	
	int id = 0;
	
	private int flag;
	
	public ShaderProgram(){
		id = GL20.glCreateProgram();
	}
	
	public void attach(Shader shader){
		if(!shader.isCompiled())
			throw new IllegalArgumentException("Shader is not compiled");
		GL20.glAttachShader(id, shader.getId());
	}
	
	public void detach(Shader shader){
		GL20.glDetachShader(id, shader.getId());
	}
	
	public void link(){
		GL20.glLinkProgram(id);
	}
	
	public void use(){
		GL20.glUseProgram(id);
	}
	
	public void reset(){
		GL20.glUseProgram(0);
	}
	
	public int getId(){
		return id;
	}
	
	public boolean isLinked(){
		return GL20.glGetProgrami(id, GL20.GL_LINK_STATUS) == GL11.GL_TRUE;
	}
	
	public void bindAttributeLocation(int index, String name){
		GL20.glBindAttribLocation(id, index, name);
	}
	
	public int getUniformLocation(String name){
		return GL20.glGetUniformLocation(id, name);
	}
	
	public int getAttribLocation(String name){
		return GL20.glGetAttribLocation(id, name);
	}
	
	public void enableVertexAttribArray(int attributeLocation){
		if(attributeLocation < 0)
			return;
		GL20.glEnableVertexAttribArray(attributeLocation);
	}
	
	public void disableVertexAttribArray(int attributeLocation){
		if(attributeLocation < 0)
			return;
		GL20.glDisableVertexAttribArray(attributeLocation);
	}
	
	public void vertexAttribPointer(int attribLocation, int numVertices, int stride, int offset){
		vertexAttribPointer(attribLocation, numVertices, GL11.GL_FLOAT, false, stride, offset);
	}
	
	public void vertexAttribPointer(int attributeLocation, int size, int type, boolean normalized, int stride, long offset){
		GL20.glVertexAttribPointer(attributeLocation, size, type, normalized, stride, offset);
	}
	
	public void dispose(){
		GL20.glDeleteProgram(id);
	}
	
	public int getBatchFlags(){
		return flag;
	}
}
