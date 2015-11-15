package net.apryx.razura.game;
import java.io.File;

import net.apryx.ftec.engine.FTec;
import net.apryx.ftec.engine.Game;
import net.apryx.ftec.graphics.TextureLoader;
import net.apryx.ftec.level.Level;
import net.apryx.graphics.GL;
import net.apryx.graphics.Texture;

public class Razura extends Game{

	private Level level;
	
	@Override
	public void init() {
		GL.clearColor(0,0,0,1);
		
		level = new Level();

		Texture t = TextureLoader.loadTexture(new File("res/logo.png"));
		Texture t2 = TextureLoader.loadTexture(new File("res/tile.png"));

		for(int i = 0; i < 10000; i++){
			level.addEntity(new TestEntity(t));
		}
		for(int i = 0; i < 10000; i++){
			level.addEntity(new TestEntity(t2));
		}
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
