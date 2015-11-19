package net.apryx.ftec.level;

import net.apryx.graphics.Sprite;
import net.apryx.graphics.SpriteBatch;
import net.apryx.math.Mathf;
import net.apryx.timing.Time;

public class Entity {
	
	protected World level;
	
	protected int layer;
	protected Sprite sprite;
	public float x, y;
	protected float hspeed, vspeed;
	
	public Entity(){
		sprite = new Sprite(null, 32, 32);
	}
	
	public void update(){
	}
	
	public void render(SpriteBatch batch){
		
	}
	
	public void gravity(float gravity){
		vspeed += gravity * Time.deltaTime;
	}

	public void accelerate(float wishspeed, float dir, float acceleration){
		float currentSpeed = hspeed * dir;
		
		if(currentSpeed > wishspeed)
			return;
		
		currentSpeed += acceleration * Time.deltaTime;
		if(currentSpeed > wishspeed)
			currentSpeed = wishspeed;
		
		hspeed = currentSpeed * dir;
	}
	
	public void friction(float friction){
		float dir = Mathf.sign(hspeed);
		float currentSpeed = hspeed * dir;
		
		if(currentSpeed == 0)
			return;
		
		currentSpeed -= friction * Time.deltaTime;
		
		if(currentSpeed < 0)
			currentSpeed = 0;
		
		hspeed = currentSpeed * dir;
	}
	
	public void setLevel(World level) {
		this.level = level;
	}
	
	public World getLevel() {
		return level;
	}
	
	public float getLeft(){
		return x - sprite.getxOffset();
	}
	public float getRight(){
		return x - sprite.getxOffset() + sprite.getWidth();
	}
	public float getTop(){
		return y - sprite.getyOffset();
	}
	public float getBottom(){
		return y - sprite.getyOffset() + sprite.getHeight();
	}
	
	public int getLayer() {
		return layer;
	}
	
}
