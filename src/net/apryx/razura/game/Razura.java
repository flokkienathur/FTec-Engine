package net.apryx.razura.game;
import net.apryx.ftec.engine.FTec;
import net.apryx.ftec.engine.Game;
import net.apryx.ftec.level.World;
import net.apryx.graphics.opengl.GL;

public class Razura extends Game{

	private World level;
	
	@Override
	public void init() {
		GL.clearColor(0,0,0,1);
		
		level = new World();

		level.addEntity(new EntityPlayer(), 16, 16);

		level.addEntity(new EntityWall(256, 16), 16, 16 + 256);
		level.addEntity(new EntityWall(256, 16), 16 + 256 + 128, 16 + 256);
		
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
