package net.apryx.math.collision;

import net.apryx.ftec.level.Entity;
import net.apryx.ftec.level.World;

public class Collision2D {
	
	private World world;

	private Entity[] entitiesX;
	private int entityIndexX;
	private Entity[] entitiesY;
	private int entityIndexY;

	public Collision2D(World world){
		this.world = world;
		entitiesX = new Entity[64];
		entitiesY = new Entity[64];
		clearX();
		clearY();
	}
	
	public void moveX(Entity entity, float xMotion, int layer){
		clearY();
		
		for(int i = 0; i < world.getEntities().size(); i++){
			Entity current = world.getEntityByIndex(i);
			
			//if its the source entity
			if(current == entity)
				continue;
			
			//if its not the current layer
			if((current.getLayer() & layer) == 0)
				continue;

			//if there is an overlap
			if(CollisionHelper.overlapsAABB(
					entity.getLeft() + xMotion, entity.getRight() + xMotion, entity.getTop(), entity.getBottom(), 
					current.getLeft(), current.getRight(), current.getTop(), current.getBottom())){
				addEntityX(current);
			}
			
		}
		
		//for each x collider
		for(int i = 0; i < getEntityXCount(); i++){
			Entity current = getEntityX(i);
			xMotion = CollisionHelper.motionDistance1D(xMotion, entity.getLeft(), entity.getRight(), current.getLeft(), current.getRight());
		}
		
		entity.x += xMotion;
	}
	
	public void moveY(Entity entity, float yMotion, int layer){
		clearX();
		
		for(int i = 0; i < world.getEntities().size(); i++){
			Entity current = world.getEntityByIndex(i);
			
			//if its the source entity
			if(current == entity)
				continue;
			
			//if its not the current layer
			if((current.getLayer() & layer) == 0)
				continue;

			//if there is an overlap
			if(CollisionHelper.overlapsAABB(
					entity.getLeft(), entity.getRight(), entity.getTop() + yMotion, entity.getBottom() + yMotion, 
					current.getLeft(), current.getRight(), current.getTop(), current.getBottom())){
				addEntityY(current);
			}
			
		}
		
		//for each y collider
		for(int i = 0; i < getEntityYCount(); i++){
			Entity current = getEntityY(i);
			yMotion = CollisionHelper.motionDistance1D(yMotion, entity.getTop(), entity.getBottom(), current.getTop(), current.getBottom());
		}
		
		entity.y += yMotion;
	}
	
	public boolean move(Entity entity, float xMotion, float yMotion, int layer){
		moveX(entity, xMotion, layer);
		moveY(entity, yMotion, layer);
		
		return false;
	}

	public boolean collidedX(){
		return entityIndexX > 0;
	}
	
	public boolean collidedY(){
		return entityIndexY > 0;
	}


	private void clearX(){
		entityIndexY = 0;
	}
	
	private void clearY(){
		entityIndexY = 0;
	}

	public int getEntityXCount(){
		return entityIndexX;
	}
	
	public Entity getEntityX(int index){
		return entitiesX[index];
	}

	public int getEntityYCount(){
		return entityIndexY;
	}
	
	public Entity getEntityY(int index){
		return entitiesY[index];
	}

	public void addEntityX(Entity entity){
		entitiesX[entityIndexX] = entity;
		entityIndexX++;
	}

	public void addEntityY(Entity entity){
		entitiesY[entityIndexY] = entity;
		entityIndexY++;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}
