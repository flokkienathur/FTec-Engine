package net.apryx.graphics.camera;

import net.apryx.math.Vector2;

public class PerspectiveCamera extends Camera{
	
	private float fov;
	
	public PerspectiveCamera(float fov, float asp, float n, float f){
		this.fov = fov;
		this.size = new Vector2(asp, 1);
		this.near = n;
		this.far = f;
	}
	
	public void setup(boolean flipped){
		view.setIdentity();

		view.rotateZ(rotation.z);
		view.rotateX(rotation.x);
		view.rotateY(rotation.y);
		
		view.translate(-position.x, -position.y, -position.z);
		
		projection.setIdentity();
		projection.setProjection(fov, size.x / size.y, near, far);
	}

	public float getFov() {
		return fov;
	}

	public void setFov(float fov) {
		this.fov = fov;
	}
}
