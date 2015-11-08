package net.apryx.ftec.level;

import java.util.ArrayList;

import net.apryx.ftec.graphics.Renderer;
import net.apryx.graphics.Camera;

public class Level {
	
	private ArrayList<Entity> entities;
	
	public Camera camera;
	public Renderer renderer;
	
	public Level(Renderer renderer){
		entities = new ArrayList<Entity>();
		this.renderer = renderer;
		camera = new Camera();
	}
	
	public void update(){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).update();
		}
	}
	
	public void render(){
		renderer.setup(camera);
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(renderer);
		}
	}
	
	public void addEntity(Entity entity){
		entities.add(entity);
		entity.setLevel(this);
	}
	
	public void removeEntity(Entity entity){
		entities.remove(entity);
	}
	
	public void dispose(){
		renderer.dispose();
	}
}
