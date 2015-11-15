package net.apryx.ftec.level;

import java.util.ArrayList;

import net.apryx.graphics.Camera;
import net.apryx.graphics.SpriteBatch;

public class Level {
	
	private ArrayList<Entity> entities;
	
	public Camera camera;
	
	private SpriteBatch batch;
	
	public Level(){
		entities = new ArrayList<Entity>();
		camera = new Camera();
		
		batch = new SpriteBatch(1024);
		
		batch.setCamera(camera);
	}
	
	public void update(){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).update();
		}
	}
	
	public void render(){
		batch.begin();
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(batch);
		}
		batch.end();
	}
	
	public void addEntity(Entity entity){
		entities.add(entity);
		entity.setLevel(this);
	}
	
	public void removeEntity(Entity entity){
		entities.remove(entity);
	}
	
	public void dispose(){
		batch.dispose();
	}
}
