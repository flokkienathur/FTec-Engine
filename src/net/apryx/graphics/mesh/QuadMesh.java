package net.apryx.graphics.mesh;

import net.apryx.graphics.Color4;
import net.apryx.math.Vector2;
import net.apryx.math.Vector3;

public class QuadMesh extends Mesh{
	
	public QuadMesh(){
		this(-1,-1,1,1);
	}
	
	public QuadMesh(float width, float height){
		this(0,0,width,height);
	}
	
	public QuadMesh(float startX, float startY, float endX, float endY){
		//default quad mesh
		vertices = new Vector3[]{
				new Vector3(startX,startY,0),
				new Vector3(endX,startY,0),
				new Vector3(endX,endY,0),
				new Vector3(startX,endY,0),
		};
		colors = new Color4[]{
				Color4.white,
				Color4.white,
				Color4.white,
				Color4.white
		};
		uvs = new Vector2[]{
				new Vector2(0,0),
				new Vector2(1,0),
				new Vector2(1,1),
				new Vector2(0,1),
		};
		triangles = new int[]{
				0,1,2,
				0,2,3
		};
		
		recalculateNormals();
	}
}
