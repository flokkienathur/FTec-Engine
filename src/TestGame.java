import java.io.File;

import net.apryx.ftec.engine.FTec;
import net.apryx.ftec.engine.Game;
import net.apryx.ftec.graphics.Renderer;
import net.apryx.ftec.graphics.TextureLoader;
import net.apryx.graphics.GL;
import net.apryx.graphics.Texture;


public class TestGame extends Game{

	private Texture texture;
	private Renderer renderer;
	
	
	@Override
	public void init() {
		GL.clearColor(0,0,0,1);
		
		texture = TextureLoader.loadTexture(new File("res/logo.png"));
		renderer = new Renderer();
	}

	@Override
	public void update() {

	}

	@Override
	public void render() {
		FTec.clear();

		renderer.camera.size.x = 80;
		renderer.camera.size.y = 60;
		
		renderer.setup();
		
		renderer.drawTexture(texture, 0, 0, 32, 32);
	}

	@Override
	public void destroy() {
		renderer.dispose();
		texture.dispose();
	}
	
}
