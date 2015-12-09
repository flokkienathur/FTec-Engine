package net.apryx.dungeon;

import net.apryx.ftec.engine.FTec;
import net.apryx.ftec.level.World;
import net.apryx.graphics.camera.PerspectiveCamera;

public class DungeonWorld extends World{
	
	private Dungeon dungeon;
	
	public DungeonWorld(){
		super();
		
		camera = new PerspectiveCamera(90, (float)FTec.window.getWidth()/(float)FTec.window.getHeight(), 0.01f, 100f);
		camera.position.y = 0.6f;
		
		batch.setCamera(camera);
		
		dungeon = new Dungeon();
	}
	
	public void render(){
		
		batch.setCamera(camera);
		
		batch.begin();
		
		dungeon.draw(batch);
		
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(batch);
		}
		
		batch.end();
	}
}
