package net.apryx.razura.game;
import net.apryx.ftec.engine.FTec;
import net.apryx.ftec.engine.Game;
import net.apryx.ftec.graphics.Renderer;
import net.apryx.ftec.level.Level;
import net.apryx.ftec.level.Tilemap;
import net.apryx.graphics.GL;


public class Razura extends Game{

	private Renderer renderer;
	private Level level;
	
	@Override
	public void init() {
		GL.clearColor(0,0,0,1);
		
		renderer = new Renderer();
		level = new Level(renderer);
		
		//check for divideability?
		level.camera.size.x = 16f;
		level.camera.size.y = 9f;
		
		level.addEntity(new PlayerEntity());
		level.addEntity(new Tilemap(level.renderer));
	}

	@Override
	public void update() {
		level.update();
	}

	@Override
	public void render() {
		FTec.clear();
		
		level.render();
	}

	@Override
	public void destroy() {
		renderer.dispose();
	}
	
}
