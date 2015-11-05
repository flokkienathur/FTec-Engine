package net.apryx.graphics.mesh;

import net.apryx.graphics.Color4;
import net.apryx.math.Vector2;
import net.apryx.math.Vector3;

public class Mesh {
	
	public Vector3[] vertices;
	public Color4[] colors;
	public Vector2[] uvs;
	public Vector3[] normals;
	
	public int[] triangles;
	
	public Mesh(){
		vertices = new Vector3[0];
		colors = new Color4[0];
		uvs = new Vector2[0];
		normals = new Vector3[0];
		
		triangles = new int[0];
	}
	
	public int getVertexCount(){
		return vertices.length;
	}
	
	public int getTriangleCount(){
		return triangles.length / 3;
	}
}
