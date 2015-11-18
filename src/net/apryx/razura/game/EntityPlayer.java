package net.apryx.razura.game;

import net.apryx.ftec.level.Entity;
import net.apryx.graphics.SpriteBatch;
import net.apryx.input.Input;
import net.apryx.input.Keys;
import net.apryx.math.collision.Collision2D;
import net.apryx.math.collision.Layer;
import net.apryx.timing.Time;

public class EntityPlayer extends Entity{
	
	private static float speed = 6 * 60;		//pixels per second
	private static float gravity = 1 * 60 * 60;	//pixels per second^2
	private static float jumpSpeed = 12 * 60;	//pixels per second
	
	public EntityPlayer(){
		layer = Layer.PLAYER;
	}
	
	@Override
	public void update() {
		super.update();
		
		vspeed += gravity * Time.deltaTime;
		
		hspeed = 0;
		
		if(Input.isKeyDown(Keys.LEFT)){
			hspeed = -speed;
		}if(Input.isKeyDown(Keys.RIGHT)){
			hspeed = speed;
		}if(Input.isKeyPressed(Keys.SPACE)){
			vspeed = -jumpSpeed;
		}
		
		Collision2D collision = new Collision2D(this.level);
		collision.move(this, hspeed * Time.deltaTime, vspeed * Time.deltaTime, Layer.SOLID);
		
		if(collision.collidedY())
			vspeed = 0;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		
		batch.color(1, 0, 0);
		
		batch.vertex(getLeft(), getTop());
		batch.vertex(getRight(), getBottom());
		batch.vertex(getRight(), getTop());

		batch.vertex(getLeft(), getTop());
		batch.vertex(getLeft(), getBottom());
		batch.vertex(getRight(), getBottom());
		
	}
	
}
