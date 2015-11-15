package net.apryx.graphics.camera;

import net.apryx.math.Matrix4;

public class PerspectiveCamera extends Camera{
	
	private float fov;
	
	public PerspectiveCamera(float fov){
		this.fov = fov;
		this.near = 0.1f;
		this.far = 100f;
	}
	
	public void setup(boolean flipped){
		view.setIdentity();

		view.rotateZ(rotation.z);
		view.rotateX(rotation.x);
		view.rotateY(rotation.y);
		view.translate(-position.x, -position.y, -position.z);
		
		projection = Matrix4.getProjectionMatrix(fov, size.x / size.y, near, far);
	}

	public float getFov() {
		return fov;
	}

	public void setFov(float fov) {
		this.fov = fov;
	}
}
