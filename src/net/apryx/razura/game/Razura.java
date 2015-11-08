package net.apryx.razura.game;
import net.apryx.ftec.engine.FTec;
import net.apryx.ftec.engine.Game;
import net.apryx.ftec.graphics.Renderer;
import net.apryx.ftec.level.Level;
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
		level.camera.size.x = FTec.window.getWidth() / 2f;
		level.camera.size.y = FTec.window.getHeight() / 2f;
		
		level.addEntity(new PlayerEntity());
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
