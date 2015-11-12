package net.apryx.graphics.mesh;

import net.apryx.graphics.Color4;
import net.apryx.math.Vector2;
import net.apryx.math.Vector3;

public class CubeMesh extends Mesh{
	
	public CubeMesh(float size){
		this(size,size,size);
	}
	
	public CubeMesh(){
		this(-1,-1,-1,1,1,1);
	}
	
	public CubeMesh(float w, float h, float z){
		this(-w/2,-h/2,-z/2,w/2,h/2,z/2);
	}
	
	public CubeMesh(float startX, float startY, float startZ, float endX, float endY, float endZ){
		//default quad mesh
		vertices = new Vector3[]{
				new Vector3(startX,startY,startZ), 	//
				new Vector3(endX,startY,startZ),	//
				new Vector3(endX,endY,startZ),		//
				new Vector3(startX,endY,startZ),	//

				new Vector3(startX,startY,endZ),	//
				new Vector3(endX,startY,endZ),		//
				new Vector3(endX,endY,endZ),		//
				new Vector3(startX,endY,endZ),		//
		};
		triangles = new int[]{
				//front
				0,2,1,
				0,3,2,
				
				//back
				4,5,6,
				4,6,7,
				
				//left
				6,5,1,
				6,1,2,
				
				//right
				7,0,4,
				7,3,0,
				
				//top
				6,2,3,
				6,3,7,
				
				//bottom
				5,0,1,
				5,4,0
				
		};
		colors = new Color4[]{
				Color4.white,
				Color4.white,
				Color4.white,
				Color4.white,

				Color4.white,
				Color4.white,
				Color4.white,
				Color4.white
		};
		uvs = new Vector2[]{
				new Vector2(1,1),
				new Vector2(0,1),
				new Vector2(0,0),
				new Vector2(1,0),

				new Vector2(0,1),
				new Vector2(1,1),
				new Vector2(1,0),
				new Vector2(0,0),
		};
		
		recalculateNormals();
	}
}
