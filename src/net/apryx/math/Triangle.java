package net.apryx.math;

import net.apryx.graphics.SpriteBatch;
import net.apryx.math.collision.CollisionHelper;

public class Triangle {
	
	private float x1, x2, x3, y1, y2, y3;

	public Triangle(float x1, float y1, float x2, float y2, float x3, float y3) {
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.y1 = y1;
		this.y2 = y2;
		this.y3 = y3;
		
		//if its inside out
		if(!contains(getCenterX(), getCenterY())){
			this.x2 = x3;
			this.x3 = x2;
			this.y2 = y3;
			this.y3 = y2;
		}
	}

	public float getX1() {
		return x1;
	}

	public void setX1(float x1) {
		this.x1 = x1;
	}

	public float getX2() {
		return x2;
	}

	public void setX2(float x2) {
		this.x2 = x2;
	}

	public float getX3() {
		return x3;
	}

	public void setX3(float x3) {
		this.x3 = x3;
	}

	public float getY1() {
		return y1;
	}

	public void setY1(float y1) {
		this.y1 = y1;
	}

	public float getY2() {
		return y2;
	}

	public void setY2(float y2) {
		this.y2 = y2;
	}

	public float getY3() {
		return y3;
	}

	public void setY3(float y3) {
		this.y3 = y3;
	}

	public float getCenterX(){
		return (x1 + x2 + x3) / 3f;
	}

	public float getCenterY(){
		return (y1 + y2 + y3) / 3f;
	}
	
	public boolean contains(float x, float y){
		return CollisionHelper.pointInTriangle(x1, y1, x2, y2, x3, y3, x, y);
	}
	
	public void draw(SpriteBatch batch){
		batch.vertex(x1, y1);
		batch.vertex(x2, y2);
		batch.vertex(x3, y3);
	}
	
	@Override
	public String toString() {
		return ""+x1 + ", "+y1 + ", "+x2 + ", "+y2 + ", "+x3 + ", "+y3;
	}
}
