package net.apryx.razura.game;

import net.apryx.ftec.level.Entity;
import net.apryx.graphics.SpriteBatch;
import net.apryx.graphics.sprite.Animation;
import net.apryx.graphics.sprite.Sprite;
import net.apryx.input.Input;
import net.apryx.input.Keys;
import net.apryx.math.collision.Layer;

public class EntityPlayer extends Entity{
	
	private static float speed = 1.5f * 60;						//pixels per second
	private static float gravity = 0.2f * 60 * 60;				//pixels per second^2
	private static float jumpSpeed = 2f * 60;					//pixels per second
	
	private static float groundAcceleration = 1f * 60 * 60; 	//pixels per second^2
	private static float groundFriction = 0.5f * 60 * 60; 		//pixels per second^2
	
	private static float airAcceleration = 0.5f * 60 * 60; 		//pixels per second^2
	private static float airFriction = 0.25f * 60 * 60; 		//pixels per second^2
	
	private Animation stepAnimation;
	private Sprite jump;
	
	public EntityPlayer(){
		layer = Layer.PLAYER;

		//TODO make animation states out of this
		sprite = new Sprite(Razura.playerStill);
		sprite.center();
		
		stepAnimation = new Animation();
		stepAnimation.addSprite(new Sprite(Razura.playerStep1).center());
		stepAnimation.addSprite(new Sprite(Razura.playerStep2).center());
		
		jump = new Sprite(Razura.playerJump);
		jump.center();
	}
	
	@Override
	public void update() {
		
		float currentFriction = groundFriction;
		float currentAcceleration = groundAcceleration;
		float addDirection = 0;
		
		if(!grounded){
			currentFriction = airFriction;
			currentAcceleration = airAcceleration;
		}

		if(Input.isKeyDown(Keys.LEFT)){
			xScale = -1;
			addDirection -= 1;
		}
		if(Input.isKeyDown(Keys.RIGHT)){
			xScale = 1;
			addDirection += 1;
		}
		if(Input.isKeyPressed(Keys.SPACE) && grounded){
			vspeed = -jumpSpeed;
		}
		
		gravity(gravity);
		friction(currentFriction);
		accelerate(speed, addDirection, currentAcceleration);
		applyMotion(Layer.SOLID);
		
		//TODO make animation states out of this
		if(hspeed != 0){
			stepAnimation.update();
		}else{
			stepAnimation.reset();
		}
	}

	public float getLeft(){
		return x - 2;
	}

	public float getRight(){
		return x + 2;
	}
	
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		
		//TODO make animation states out of this
		if(grounded){
			if(hspeed == 0)
				batch.drawSprite(sprite, x, y, xScale, yScale);
			else{
				batch.drawSprite(stepAnimation.getCurrentSprite(), x, y, xScale, yScale);
			}
		}else{
			if(vspeed > 0){
				batch.drawSprite(sprite, x, y, xScale, yScale);				
			}else{
				batch.drawSprite(jump, x, y, xScale, yScale);	
			}
		}
		
	}
	
}
