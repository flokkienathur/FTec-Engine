package net.apryx.ftec.level;

import net.apryx.graphics.Sprite;
import net.apryx.graphics.SpriteBatch;

public class Entity {
	
	protected Level level;
	
	protected Sprite sprite;
	protected float x, y;
	protected float hspeed, vspeed;
	
	public Entity(){
		sprite = new Sprite(null, 32, 32);
	}
	
	public void update(){
		
	}
	
	public void render(SpriteBatch batch){
		
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public Level getLevel() {
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
	
}
