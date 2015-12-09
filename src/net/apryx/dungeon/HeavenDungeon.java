package net.apryx.dungeon;

import java.io.File;

import net.apryx.ftec.engine.FTec;
import net.apryx.ftec.engine.Game;
import net.apryx.ftec.graphics.TextureLoader;
import net.apryx.ftec.level.World;
import net.apryx.graphics.opengl.GL;
import net.apryx.graphics.opengl.Texture;
import net.apryx.input.Input;
import net.apryx.math.Mathf;

public class HeavenDungeon extends Game{

	public static Texture tile;
	public static Texture fire;
	public static Texture rock;
	public static Texture person;

	protected World world;
	
	@Override
	public void init() {
		GL.clearColor(0,0,1,1);

		tile = TextureLoader.loadTexture(new File("res/tileSheet.png"));
		fire = TextureLoader.loadTexture(new File("res/fire.png"));
		rock = TextureLoader.loadTexture(new File("res/rock.png"));
		person = TextureLoader.loadTexture(new File("res/person.png"));
		
		Input.setMouseGrabbed(true);
		
		world = new DungeonWorld();
		
		int b = 40;
		
		int a = (int) Mathf.random() * b + b/2;
		
		for(int i = 0; i < a; i++){
			world.addEntity(new EntityThing(fire), Mathf.random() * 8 + 1, Mathf.random() * 8 + 1);			
		}
		
		a = (int) Mathf.random() * b + b/2;
		
		for(int i = 0; i < a; i++){
			world.addEntity(new EntityThing(rock), Mathf.random() * 8 + 1, Mathf.random() * 8 + 1);			
		}
		
		a = (int) Mathf.random() * b + b/2;
		
		for(int i = 0; i < a; i++){
			world.addEntity(new EntityThing(person), Mathf.random() * 8 + 1, Mathf.random() * 8 + 1);			
		}
		world.addEntity(new EntityPlayer(), 3, 3);
	}

	@Override
	public void update() {
		world.update();
	}

	@Override
	public void render() {
		FTec.clear();
		
		world.render();
	}

	@Override
	public void destroy() {
		world.dispose();
		tile.dispose();
		fire.dispose();
		rock.dispose();
		person.dispose();
	}
	
	public static void main(String[] args){
		FTec.create(new HeavenDungeon());
	}
	
}
