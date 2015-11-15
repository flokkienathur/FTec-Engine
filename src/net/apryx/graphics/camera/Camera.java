package net.apryx.graphics.camera;

import net.apryx.math.Matrix4;
import net.apryx.math.Vector2;
import net.apryx.math.Vector3;

public abstract class Camera {
	
	public Matrix4 view;
	public Matrix4 projection;
	
	public Vector3 position;
	public Vector3 rotation;
	public Vector2 size;
	
	protected float near, far;
	
	public Camera(){
		position = new Vector3();
		rotation = new Vector3();
		size = new Vector2(1,1);
		view = new Matrix4();
		projection = new Matrix4();
	}

	public abstract void setup();
	
	public float getNear() {
		return near;
	}

	public void setNear(float near) {
		this.near = near;
	}

	public float getFar() {
		return far;
	}

	public void setFar(float far) {
		this.far = far;
	}
}
