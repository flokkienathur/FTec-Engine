package net.apryx.ftec.level;

import java.util.ArrayList;
import java.util.List;

import net.apryx.ftec.engine.FTec;
import net.apryx.graphics.SpriteBatch;
import net.apryx.graphics.camera.Camera;
import net.apryx.graphics.camera.OrthagonalCamera;

public class World {
	
	protected ArrayList<Entity> entities;
	
	public Camera camera;
	
	protected SpriteBatch batch;
	
	public World(){
		entities = new ArrayList<Entity>();
		camera = new OrthagonalCamera(FTec.window.getWidth(), FTec.window.getHeight());
		
		batch = new SpriteBatch(1800*3);
		
		batch.setCamera(camera);
	}
	
	public void update(){	
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).update();
		}
	}
	
	public void render(){
		batch.setCamera(camera);
		
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
	
	public void addEntity(Entity entity, float x, float y){
		entity.x = x;
		entity.y = y;
		entities.add(entity);
		entity.setLevel(this);
	}
	
	public void removeEntity(Entity entity){
		entities.remove(entity);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Entity> T getEntity(Class<T> ec){
		
		for(Entity e : entities)
			if(e.getClass() == ec)
				return (T) e;
		
		return null;
	}
	
	public Entity getEntityByIndex(int index){
		return entities.get(index);
	}
	
	public List<Entity> getEntities(){
		return entities;
	}
	
	public void dispose(){
		batch.dispose();
	}
}
