package net.apryx.graphics.camera;

import net.apryx.math.Matrix4;

public class OrthagonalCamera extends Camera{
	
	public OrthagonalCamera(float width, float height){
		super();
		size.x = width;
		size.y = height;
		this.near = -100f;
		this.far = 100f;
	}
	
	public void setup(){
		view.setIdentity();

		view.rotateZ(rotation.z);
		view.rotateX(rotation.x);
		view.rotateY(rotation.y);
		view.translate(-position.x, -position.y, -position.z);
		
		projection = Matrix4.getOrthagonalMatrix(0, size.x, size.y, 0, -100, 100);
	}
}
