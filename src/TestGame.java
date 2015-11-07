import java.io.File;

import net.apryx.ftec.engine.FTec;
import net.apryx.ftec.engine.Game;
import net.apryx.ftec.graphics.Renderer;
import net.apryx.ftec.graphics.TextureLoader;
import net.apryx.graphics.Camera;
import net.apryx.graphics.GL;
import net.apryx.graphics.SpriteBatch;
import net.apryx.graphics.Texture;
import net.apryx.math.Mathf;


public class TestGame extends Game{

	private Texture texture;
	private Renderer renderer;
	private Camera camera;
	private SpriteBatch batch;
	
	@Override
	public void init() {
		GL.clearColor(0,0,0,1);
		
		texture = TextureLoader.loadTexture(new File("res/logo.png"));
		renderer = new Renderer();
		camera = new Camera();
		batch = new SpriteBatch(renderer, 120);
		
		camera.size.x = 80;
		camera.size.y = 60;
	}

	float a = 0;
	@Override
	public void update() {
		a += FTec.deltaTime;
	}

	@Override
	public void render() {
		FTec.clear();
		
		renderer.model.setIdentity();
		renderer.setup(camera);
		
		renderer.model.translate(40, 30, 0);
		
		batch.setTexture(texture);
		
		batch.begin();

		batch.uv(0, 0);
		batch.vertex(Mathf.cos(a) * 16 + 16, 0, 0);
		batch.uv(0, 1);
		batch.vertex(0, 32, 0);
		batch.uv(1, 1);
		batch.vertex(48, 32 * (Mathf.sin(a) + 1), 0);
		
		batch.end();
		
		renderer.drawTexture(texture, -16, -16, 32, 32);
	}

	@Override
	public void destroy() {
		batch.dispose();
		renderer.dispose();
		texture.dispose();
	}
	
}
