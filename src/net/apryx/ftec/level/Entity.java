package net.apryx.ftec.level;

import net.apryx.graphics.SpriteBatch;
import net.apryx.graphics.sprite.Sprite;
import net.apryx.math.Mathf;
import net.apryx.math.collision.Collision2D;
import net.apryx.timing.Time;

public class Entity {
	
	protected World level;
	
	protected int layer;
	protected Sprite sprite;
	public float x, y;
	protected float hspeed, vspeed;
	protected boolean grounded = false;
	protected float xScale = 1, yScale = 1;
	
	//used for the move method
	private float accumX = 0;
	private float accumY = 0;
	
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
		if(dir == 0)
			return;
		
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
		
		if(currentSpeed < 0){
			currentSpeed = 0;
			hspeed = 0;
		}else{
			hspeed = currentSpeed * dir;			
		}
		
	}
	
	public void applyMotion(){
		applyMotion(0);
	}
	
	public Collision2D applyMotion(int layer){
		return applyMotion(layer, true);
	}
	
	public Collision2D applyMotion(int layer, boolean nullifyOnCollision){
		accumX += hspeed * Time.deltaTime;
		accumY += vspeed * Time.deltaTime;
		
		boolean move = false;
		
		float xM = 0;
		if(Mathf.abs(accumX) > Mathf.EPSILON * 10f){
			xM = accumX;
			accumX = 0;
			move = true;
		}
		float yM = 0;
		if(Mathf.abs(accumY) > Mathf.EPSILON * 10f){
			yM = accumY;
			accumY = 0;
			move = true;
		}
		
		if(!move){
			return null;
		}
		
		if(layer == 0){
			x += xM;
			y += yM;
			return null;
		}else{
			Collision2D collision = new Collision2D(this.level);
			collision.move(this, xM, yM, layer);
			if(yM != 0){
				if(collision.collidedBottom()){
					grounded = true;
				}else{
					grounded = false;
				}
			}
			if(nullifyOnCollision){
				if(collision.collidedY()){
					vspeed = 0;
				}
				if(collision.collidedX()){
					hspeed = 0;
				}
			}
			
			return collision;
		}
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
