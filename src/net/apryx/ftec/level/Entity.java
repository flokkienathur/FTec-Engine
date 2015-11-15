package net.apryx.ftec.level;

import net.apryx.graphics.SpriteBatch;
import net.apryx.math.Transform;

public class Entity {
	
	public Level level;
	public Transform transform;
	
	public Entity(){
		transform = new Transform(0, 0, 1, 1);
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
}
