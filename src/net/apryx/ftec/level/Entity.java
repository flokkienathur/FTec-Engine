package net.apryx.ftec.level;

import net.apryx.ftec.graphics.Renderer;

public class Entity {
	
	protected Level level;
	
	public void update(){
		
	}
	
	public void render(Renderer renderer){
		
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public Level getLevel() {
		return level;
	}
}
