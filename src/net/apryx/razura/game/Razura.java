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

	public static Texture playerStill;
	public static Texture playerStep1;
	public static Texture playerStep2;
	public static Texture playerFall;
	public static Texture playerJump;
	public static Texture playerJump2;
	public static Texture playerCrouch;
	
	@Override
	public void init() {
		GL.clearColor(0,0,0,1);
		playerStill = TextureLoader.loadTexture(new File("res/player_still.png"));
		playerFall = TextureLoader.loadTexture(new File("res/player_fall.png"));
		playerStep1 = TextureLoader.loadTexture(new File("res/player_step1.png"));
		playerStep2 = TextureLoader.loadTexture(new File("res/player_step2.png"));

		playerJump = TextureLoader.loadTexture(new File("res/player_jump.png"));
		playerJump2 = TextureLoader.loadTexture(new File("res/player_jump2.png"));
		playerCrouch = TextureLoader.loadTexture(new File("res/player_crouch.png"));
		
		level = new World();		

		level.camera.size.x = 1280 / 4f;
		level.camera.size.y = 720 / 4f;

		level.addEntity(new EntityWall(320, 48), 0, 80);
		level.addEntity(new EntityWall(16, 16), 100, 80 - 16);
		level.addEntity(new EntityWall(16, 16), 0, 80 - 16);
		level.addEntity(new EntityWall(32, 16), 100 - 8, 80 - 8);

		level.addEntity(new EntityPlayer(), 16, 16);
		
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
		playerStill.dispose();
		playerStep1.dispose();
		playerStep2.dispose();
		playerJump.dispose();
		playerFall.dispose();
		playerJump2.dispose();
		playerCrouch.dispose();
	}
	
}
