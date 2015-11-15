package net.apryx.razura.game;
import net.apryx.ftec.engine.FTec;
import net.apryx.ftec.engine.Game;
import net.apryx.ftec.level.Level;
import net.apryx.graphics.opengl.GL;

public class Razura extends Game{

	private Level level;
	
	@Override
	public void init() {
		GL.clearColor(0,0,0,1);
		
		level = new Level();
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
		level.dispose();
	}
	
}
