package net.apryx.graphics.mesh;

import net.apryx.graphics.Color4;
import net.apryx.math.Mathf;
import net.apryx.math.Vector2;
import net.apryx.math.Vector3;

public class CircleMesh extends Mesh{
	
	public CircleMesh(){
		this(1);
	}
	
	public CircleMesh(float radius){
		this(radius, 8);
	}
	
	public CircleMesh(float radius, int iterations){
		this(0,0,radius, iterations);
	}
	
	public CircleMesh(float x, float y, float radius, int iterations){
		vertices = new Vector3[iterations + 1];
		colors = new Color4[iterations + 1];
		uvs = new Vector2[iterations + 1];
		
		triangles = new int[iterations * 3];
		
		//middle
		vertices[0] = new Vector3(x,y,0);
		colors[0] = Color4.white;
		uvs[0] = new Vector2(0.5f, 0.5f);
		
		for(int i = 0; i < iterations; i ++){
			int index = i + 1;
			float angle = Mathf.PI * 2 / iterations * i;
			float c = Mathf.cos(angle);
			float s = Mathf.sin(angle);
			
			vertices[index] = new Vector3(x + c * radius, y + s * radius, 0);
			colors[index] = Color4.white;
			uvs[index] = new Vector2(0.5f + c / 2, 0.5f + s / 2);
			
			if(i < iterations - 1){
				triangles[i * 3 + 0] = 0;
				triangles[i * 3 + 1] = i + 1;
				triangles[i * 3 + 2] = i + 2;
			}
		}
		
		triangles[triangles.length - 2] = iterations;
		triangles[triangles.length - 1] = 1;
		
		recalculateNormals();
	}
}
