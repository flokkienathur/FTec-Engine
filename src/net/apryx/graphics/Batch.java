package net.apryx.graphics;

public abstract class Batch {
	protected VBO vertices;
	protected VBO colors;
	protected VBO uvs;
	protected VBO normals;
	
	protected VBO indices;
	
	protected VAO vao;
	
	public Batch(){
		vertices = new VBO(VBO.ARRAY_BUFFER);
		colors = new VBO(VBO.ARRAY_BUFFER);
		uvs = new VBO(VBO.ARRAY_BUFFER);
		normals = new VBO(VBO.ARRAY_BUFFER);
		
		indices = new VBO(VBO.ELEMENT_ARRAY_BUFFER);

		vao = new VAO();
	}
	
	public abstract void draw();
	
	public void dispose(){
		vao.dispose();
		
		vertices.dispose();
		colors.dispose();
		uvs.dispose();
		indices.dispose();
	}
}
