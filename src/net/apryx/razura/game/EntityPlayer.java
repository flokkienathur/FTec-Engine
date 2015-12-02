package net.apryx.razura.game;

import net.apryx.ftec.level.Entity;
import net.apryx.graphics.SpriteBatch;
import net.apryx.graphics.sprite.Animation;
import net.apryx.graphics.sprite.AnimationCondition;
import net.apryx.graphics.sprite.AnimationController;
import net.apryx.graphics.sprite.AnimationState;
import net.apryx.graphics.sprite.AnimationTransition;
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
	
	private AnimationController controller;
	
	public EntityPlayer(){
		layer = Layer.PLAYER;

		sprite = new Sprite(Razura.playerStill);
		sprite.center();
		
		Sprite step1 = new Sprite(Razura.playerStep1).center();
		Sprite step2 = new Sprite(Razura.playerStep2).center();
		
		Animation stepAnimation = new Animation();
		stepAnimation.addSprite(step1);
		stepAnimation.addSprite(step2);
		
		Sprite jump = new Sprite(Razura.playerJump);
		jump.center();
		
		//Animation controller stuff
		
		//Create the controller
		controller = new AnimationController();
		
		//Set its default values
		controller.setValue("hspeed", 0);
		controller.setValue("vspeed", 0);
		controller.setValue("grounded", false);

		//Animation states
		AnimationState idleState = new AnimationState(new Animation(sprite));
		
		AnimationState jumpState = new AnimationState(new Animation(jump));
		AnimationState fallState = new AnimationState(new Animation(sprite));
		AnimationState stepState = new AnimationState(stepAnimation);

		//Animation Condition
		AnimationCondition<Float> hspeedGreater = new AnimationCondition<Float>("hspeed", 20f, AnimationCondition.GREATER);
		AnimationCondition<Float> hspeedLess = new AnimationCondition<Float>("hspeed", -20f, AnimationCondition.LESS);
		AnimationCondition<Float> hspeedZero = new AnimationCondition<Float>("hspeed", 0f, AnimationCondition.EQUAL);
		
		AnimationCondition<Boolean> notGrounded = new AnimationCondition<Boolean>("grounded", false, AnimationCondition.EQUAL);
		AnimationCondition<Boolean> grounded = new AnimationCondition<Boolean>("grounded", true, AnimationCondition.EQUAL);

		AnimationCondition<Float> vspeedGreater = new AnimationCondition<Float>("vspeed", 0f, AnimationCondition.GREATER);
		
		//Animation Transitions
		AnimationTransition idleToStepTransition = new AnimationTransition(stepState).addCondition(hspeedGreater).addCondition(grounded);
		AnimationTransition idleToStepTransition2 = new AnimationTransition(stepState).addCondition(hspeedLess).addCondition(grounded);
		
		AnimationTransition stepToIdleTransition = new AnimationTransition(idleState).addCondition(hspeedZero);
		AnimationTransition stepToIdleTransition2 = new AnimationTransition(idleState).addCondition(notGrounded);
		
		AnimationTransition idleToJump = new AnimationTransition(jumpState).addCondition(notGrounded);
		AnimationTransition jumpToFall = new AnimationTransition(fallState).addCondition(vspeedGreater);
		
		AnimationTransition airToIdle = new AnimationTransition(idleState).addCondition(grounded);

		//Add the animation transitions
		idleState.addTransition(idleToStepTransition);
		idleState.addTransition(idleToStepTransition2);
		
		stepState.addTransition(stepToIdleTransition);
		stepState.addTransition(stepToIdleTransition2);
		
		idleState.addTransition(idleToJump);
		
		jumpState.addTransition(jumpToFall);
		jumpState.addTransition(airToIdle);
		
		fallState.addTransition(airToIdle);
		
		//Set the state to the default idle state
		controller.setState(idleState);
		
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
		if(Input.isKeyDown(Keys.SPACE) && grounded){
			vspeed = -jumpSpeed;
		}
		
		gravity(gravity);
		friction(currentFriction);
		accelerate(speed, addDirection, currentAcceleration);
		
		applyMotion(Layer.SOLID);

		controller.setValue("hspeed", hspeed);
		controller.setValue("vspeed", vspeed);
		controller.setValue("grounded", grounded);
		controller.update();
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		
		batch.drawSprite(controller.getCurrentSprite(), x, y, xScale, yScale);
	}
	
	public float getLeft(){
		return x - 2;
	}

	public float getRight(){
		return x + 2;
	}
	
}
