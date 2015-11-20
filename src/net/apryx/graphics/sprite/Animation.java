package net.apryx.graphics.sprite;

import java.util.ArrayList;

import net.apryx.timing.Time;

public class Animation {
	
	private int currentIndex = 0;
	private float fps = 8;
	private float time = 0;
	private boolean finished = false;
	
	//TODO replace this list for a faster approach
	private ArrayList<Sprite> sprites;
	
	public Animation(){
		sprites = new ArrayList<Sprite>();
	}
	
	public Animation(Sprite... sprites){
		this.sprites = new ArrayList<Sprite>();
		
		for(Sprite s : sprites){
			this.sprites.add(s);
		}
	}

	public void update(){
		finished = false;
		//keep index if fps <= 0
		if(fps > 0 && sprites.size() > 0){
			time += Time.deltaTime * fps;
			currentIndex = (int) time % sprites.size();
			if(time > sprites.size()){
				time -= sprites.size();
				finished = true;
			}
		}
	}
	
	public Sprite getCurrentSprite(){
		if(sprites.size() > 0)
			return sprites.get(currentIndex);
		else
			return null;
	}
	
	public void addSprite(Sprite sprite){
		sprites.add(sprite);
	}
	
	public void removeSprite(Sprite sprite){
		sprites.remove(sprite);
	}
	
	public Sprite getSprite(int index){
		return sprites.get(index);
	}
	
	public void setFPS(float fps){
		this.fps = fps;
	}
	
	public float getFPS(){
		return fps;
	}
	
	public boolean isFinished(){
		return finished;
	}
	
	public void setSpriteIndex(int index){
		currentIndex = index;
	}
	
	public void reset(){
		time = 0;
	}
	
}
