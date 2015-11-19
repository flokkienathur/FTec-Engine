package net.apryx.razura.game;

import net.apryx.ftec.level.Entity;
import net.apryx.graphics.Sprite;
import net.apryx.graphics.SpriteBatch;
import net.apryx.input.Input;
import net.apryx.input.Keys;
import net.apryx.math.Mathf;
import net.apryx.math.collision.Collision2D;
import net.apryx.math.collision.Layer;
import net.apryx.timing.Time;

public class EntityPlayer extends Entity{
	
	private static float speed = 3 * 60;		//pixels per second
	private static float gravity = 0.5f * 60 * 60;	//pixels per second^2
	private static float jumpSpeed = 6f * 60;	//pixels per second
	
	private static float groundAcceleration = 1f * 60 * 60; //pixels per second^2
	private static float groundFriction = 0.5f * 60 * 60; //pixels per second^2
	
	private static float airAcceleration = 0.5f * 60 * 60; //pixels per second^2
	private static float airFriction = 0.25f * 60 * 60; //pixels per second^2
	
	private float facing = 1;
	private boolean grounded = false;
	
	public EntityPlayer(){
		layer = Layer.PLAYER;
		
		sprite = new Sprite(Razura.player);
		sprite.center();
	}
	
	@Override
	public void update() {
		super.update();
		
		gravity(gravity);
		
		float friction = groundFriction;
		float acceleration = groundAcceleration;
		
		if(!grounded){
			friction = airFriction;
			acceleration = airAcceleration;
		}
		
		friction(friction);
		
		if(Input.isKeyDown(Keys.LEFT)){
			facing = -1;
			accelerate(speed, -1, acceleration);
		}if(Input.isKeyDown(Keys.RIGHT)){
			facing = 1;
			accelerate(speed, 1, acceleration);
		}
		
		if(Input.isKeyPressed(Keys.SPACE) && grounded){
			vspeed = -jumpSpeed;
		}
		
		grounded = false;
		Collision2D collision = new Collision2D(this.level);
		collision.move(this, hspeed * Time.deltaTime, vspeed * Time.deltaTime, Layer.SOLID);
		
		if(collision.collidedY()){
			vspeed = 0;
			if(collision.collidedBottom()){
				grounded = true;
			}
		}
		if(collision.collidedX()){
			hspeed = 0;
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		
		batch.drawSprite(sprite, x, y, facing, 1);
		
		/*batch.color(0, 0, 0);
		
		batch.vertex(getLeft(), getTop());
		batch.vertex(getRight(), getBottom());
		batch.vertex(getRight(), getTop());

		batch.vertex(getLeft(), getTop());
		batch.vertex(getLeft(), getBottom());
		batch.vertex(getRight(), getBottom());*/
		
	}
	
}
