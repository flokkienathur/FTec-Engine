package net.apryx.razura.game;

import net.apryx.ftec.level.Entity;
import net.apryx.graphics.Sprite;
import net.apryx.graphics.SpriteBatch;
import net.apryx.input.Input;
import net.apryx.input.Keys;
import net.apryx.math.collision.Collision2D;
import net.apryx.math.collision.Layer;
import net.apryx.timing.Time;

public class EntityPlayer extends Entity{
	
	private static float speed = 1.5f * 60;		//pixels per second
	private static float gravity = 0.3f * 60 * 60;	//pixels per second^2
	private static float jumpSpeed = 4f * 60;	//pixels per second
	
	private static float groundAcceleration = 1f * 60 * 60; //pixels per second^2
	private static float groundFriction = 0.5f * 60 * 60; //pixels per second^2
	
	private static float airAcceleration = 0.5f * 60 * 60; //pixels per second^2
	private static float airFriction = 0.25f * 60 * 60; //pixels per second^2
	
	private float facing = 1;
	private boolean grounded = false;
	
	private Sprite[] step;
	private float stepIndex = 0;
	
	private Sprite jump;
	
	public EntityPlayer(){
		layer = Layer.PLAYER;
		
		sprite = new Sprite(Razura.playerStill);
		sprite.center();
		
		step = new Sprite[2];
		
		step[0] = new Sprite(Razura.playerStep1);
		step[1] = new Sprite(Razura.playerStep2);
		
		step[0].center();
		step[1].center();
		
		jump = new Sprite(Razura.playerJump);
		jump.center();
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
		if(hspeed != 0){
			stepIndex += Time.deltaTime * 8;
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		
		if(grounded){
			if(hspeed == 0)
				batch.drawSprite(sprite, x, y, facing, 1);
			else{
				int index = (int)stepIndex % 2;
				batch.drawSprite(step[index], x, y, facing, 1);
			}
		}else{
			if(vspeed > 0){
				batch.drawSprite(sprite, x, y, facing, 1);				
			}else{
				batch.drawSprite(jump, x, y, facing, 1);	
			}
		}
		
	}
	
}
