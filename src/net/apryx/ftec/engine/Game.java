package net.apryx.ftec.engine;

public abstract class Game {
	
	public abstract void init();
	public abstract void update();
	public abstract void render();
	public abstract void destroy();
	
	public boolean isCloseRequested(){
		return FTec.window.isCloseRequested();
	}
	
}
