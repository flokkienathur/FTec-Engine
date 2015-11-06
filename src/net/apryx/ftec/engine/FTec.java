package net.apryx.ftec.engine;

import net.apryx.graphics.GL;
import net.apryx.graphics.Window;

public class FTec {
	
	public static Window window;
	
	public static float deltaTime;
	public static float runTime;
	
	public static void clear(){
		GL.clear(GL.DEPTH_BUFFER_BIT | GL.COLOR_BUFFER_BIT);
	}

	public static void create(Game game){
		 window = new Window(1024,768,false);
		 
		 window.setVSync(false);
		 window.setVisible(true);
		 
		 game.init();
		 
		 long previous = System.nanoTime();
		 long sum = 0;
		 int fps = 0;
		 
		 while(!game.isCloseRequested()){
			 window.pollEvents();
			 
			 long current = System.nanoTime();
			 long delta = current - previous;
			 previous = current;
			 
			 deltaTime = delta / 1000000000f;
			 
			 sum += delta;
			 
			 if(sum > 1000000000){
				 System.out.println("FPS: " + fps);
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
