package net.apryx.ftec.level;

import net.apryx.ftec.graphics.Renderer;
import net.apryx.graphics.Color4;
import net.apryx.math.Transform;

public class Entity {
	
	protected Level level;
	protected Transform transform;
	
	public Entity(){
		transform = new Transform(0, 0, 1, 1);
	}
	
	public void update(){
		
	}
	
	public void render(Renderer renderer){
		renderer.blend = Color4.white;
		renderer.drawRectangle(transform.position.x, transform.position.y, transform.bounds.width, transform.bounds.height);
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public Level getLevel() {
		return level;
	}
}
