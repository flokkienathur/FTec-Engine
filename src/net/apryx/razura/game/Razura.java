package net.apryx.razura.game;
import java.io.File;

import net.apryx.ftec.engine.FTec;
import net.apryx.ftec.engine.Game;
import net.apryx.ftec.graphics.TextureLoader;
import net.apryx.ftec.level.World;
import net.apryx.graphics.opengl.GL;
import net.apryx.graphics.opengl.Texture;

public class Razura extends Game{

	private World level;
	
	public static Texture player;
	
	@Override
	public void init() {
		GL.clearColor(0,0,0,1);
		player = TextureLoader.loadTexture(new File("res/player.png"));
		
		level = new World();		

		level.camera.size.x = FTec.window.getWidth() / 4;
		level.camera.size.y = FTec.window.getHeight() / 4;

		level.addEntity(new EntityPlayer(), 16, 16);

		level.addEntity(new EntityWall(320, 48), 0, 134);
		
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
		player.dispose();
	}
	
}
