package net.apryx.graphics;

import net.apryx.math.Matrix4;
import net.apryx.math.Vector2;
import net.apryx.math.Vector3;

public class Camera {
	
	public Matrix4 view;
	public Matrix4 projection;
	
	public Vector3 position;
	public Vector3 rotation;
	public Vector2 size;
	
	public Camera(){
		position = new Vector3();
		rotation = new Vector3();
		size = new Vector2(1,1);
		view = new Matrix4();
		projection = new Matrix4();
	}

	public void setup(){
		view.setIdentity();

		view.rotateZ(rotation.z);
		view.rotateX(rotation.x);
		view.rotateY(rotation.y);
		view.translate(-position.x, -position.y, -position.z);

		projection = Matrix4.getProjectionMatrix(90, size.x / size.y, 0.01f, 1000);
	}
}
