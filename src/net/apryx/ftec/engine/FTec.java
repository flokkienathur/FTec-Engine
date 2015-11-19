package net.apryx.ftec.engine;

import org.lwjgl.opengl.GL11;

import net.apryx.graphics.Window;
import net.apryx.graphics.opengl.GL;
import net.apryx.timing.Time;

public class FTec {
	
	public static Window window;
	
	//local copy for reasons
	private static float deltaTime;
	private static float runTime;
	
	public static void clear(){
		GL.clear(GL.DEPTH_BUFFER_BIT | GL.COLOR_BUFFER_BIT);
	}

	public static void create(Game game){
		 window = new Window(1280, 720, false);
		 
		 window.setVSync(true);
		 window.setVisible(true);
		 
		 window.setCursorHidden(false);
		 
		 GL11.glEnable(GL11.GL_DEPTH_TEST);
		 GL11.glEnable(GL11.GL_BLEND); 
		 GL11.glBlendFunc (GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		 //GL11.glEnable(GL11.GL_CULL_FACE);
		 
		 game.init();
		 
		 long previous = System.nanoTime();
		 long sum = 0;
		 int fps = 0;
		 
		 Time.deltaTime = 1f / 60f;
		 
		 while(!game.isCloseRequested()){
			 window.pollEvents();
			 
			 long current = System.nanoTime();
			 long delta = current - previous;
			 previous = current;
			 
			 deltaTime = delta / 1000000000f;
			 runTime += deltaTime;
			 
			 Time.deltaTime = (Time.deltaTime * 99 + deltaTime) / 100f;
			 Time.runTime = runTime;
			 
			 sum += delta;
			 
			 if(sum > 1000000000){
				 System.out.println("FPS: " + fps);
				 Time.fps = fps;
				 sum -= 1000000000;
				 fps = 0;
			 }
			 
			 
			 game.update();
			 game.render();
			 fps++;
			 
			 window.swap();
		 }
		 
		 game.destroy();
		 
		 window.destroy();
	}
}
