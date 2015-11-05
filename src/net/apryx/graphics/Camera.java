package net.apryx.graphics;

import net.apryx.math.Matrix4;
import net.apryx.math.Vector2;

public class Camera {
	
	public Matrix4 view;
	public Matrix4 projection;
	
	public Vector2 position;
	public Vector2 size;
	
	public Camera(){
		position = new Vector2();
		size = new Vector2(1,1);
		view = new Matrix4();
		projection = new Matrix4();
	}

	public void setup(){
		view.setTranslate(position.x, position.y, 0);
		projection.setIdentity();

		//set the translate to top left
		projection.setTranslate(-1, 1, 0);
		//scale and invert y
		projection.setScale(1f/(0.5f*size.x), -1f/(0.5f*size.y), 1);
	}
}
