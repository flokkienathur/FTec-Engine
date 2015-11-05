package net.apryx.graphics.mesh;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import net.apryx.ftec.graphics.Batch;
import net.apryx.graphics.GL;
import net.apryx.graphics.VAO;
import net.apryx.graphics.VBO;
import net.apryx.utils.BufferUtils;

public class MeshBatch {
	
	private Mesh mesh;
	
	private VBO vertices;
	private VBO colors;
	private VBO uvs;
	
	private VBO indices;
	
	private VAO vao;
	
	public MeshBatch(Mesh mesh){
		vertices = new VBO(VBO.ARRAY_BUFFER);
		colors = new VBO(VBO.ARRAY_BUFFER);
		uvs = new VBO(VBO.ARRAY_BUFFER);
		
		indices = new VBO(VBO.ELEMENT_ARRAY_BUFFER);
		
		vao = new VAO();
		
		this.mesh = mesh;
		init();
	}
	
	private void init(){
		int modelSize = mesh.getVertexCount();
		//3 components per vertex
		FloatBuffer v = BufferUtils.createFloatBuffer(modelSize * 3);
		
		//4 components per color
		FloatBuffer c = BufferUtils.createFloatBuffer(modelSize * 4);
		
		//4 components per uv
		FloatBuffer u = BufferUtils.createFloatBuffer(modelSize * 2);
		
		for(int i = 0; i < modelSize; i++){
			v.put(mesh.vertices[i].x);
			v.put(mesh.vertices[i].y);
			v.put(mesh.vertices[i].z);

			c.put(mesh.colors[i].r);
			c.put(mesh.colors[i].g);
			c.put(mesh.colors[i].b);
			c.put(mesh.colors[i].a);

			u.put(mesh.uvs[i].x);
			u.put(mesh.uvs[i].y);
		}
		
		//three indices per triangle
		IntBuffer idx = BufferUtils.createIntBuffer(mesh.triangles.length);
		idx.put(mesh.triangles);
		
		idx.flip();
		v.flip();
		c.flip();
		u.flip();
		
		vertices.bufferData(v);
		colors.bufferData(c);
		uvs.bufferData(u);
		
		indices.bufferData(idx);
		
		vao.setPointer(Batch.POSITION_INDEX, vertices, 3, 0, 0);
		vao.setPointer(Batch.COLOR_INDEX, colors, 4, 0, 0);
		vao.setPointer(Batch.UV_INDEX, uvs, 2, 0, 0);

		vao.enableVertexAttribArray(Batch.POSITION_INDEX);
		vao.enableVertexAttribArray(Batch.COLOR_INDEX);
		vao.enableVertexAttribArray(Batch.UV_INDEX);
		
		vao.setIndices(indices);
	}
	
	public void updateMesh(){
		init();
	}
	
	public void updateMesh(Mesh mesh){
		this.mesh = mesh;
		init();
	}
	
	public void draw(){
		vao.draw(GL.TRIANGLES, 0, mesh.triangles.length);
	}
	
	public void dispose(){
		vao.dispose();
		
		vertices.dispose();
		colors.dispose();
		uvs.dispose();
		indices.dispose();
	}
}
