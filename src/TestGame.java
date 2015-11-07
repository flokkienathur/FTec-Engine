import java.io.File;

import net.apryx.ftec.engine.FTec;
import net.apryx.ftec.engine.Game;
import net.apryx.ftec.graphics.Renderer;
import net.apryx.ftec.graphics.TextureLoader;
import net.apryx.graphics.Camera;
import net.apryx.graphics.GL;
import net.apryx.graphics.SpriteBatch;
import net.apryx.graphics.Texture;


public class TestGame extends Game{

	private Texture texture;
	private Renderer renderer;
	private Camera camera;
	private SpriteBatch batch;
	
	private int things = 10;
	//200 * 200 = 40000 tiles
	//40000 tiles = 80000 triangles
	//1000 * 1000 = 1000000 tiles
	//1000000 tiles = 2000000 triangles (2 million)
	
	//things * things * 2 = triangles
	
	@Override
	public void init() {
		GL.clearColor(0,0,0,1);
		
		texture = TextureLoader.loadTexture(new File("res/logo.png"));
		renderer = new Renderer();
		camera = new Camera();
		batch = new SpriteBatch(renderer, things*things*6);
		
		camera.size.x = FTec.window.getWidth() / 1f;
		camera.size.y = FTec.window.getHeight() / 1f;
		
		batch.setTexture(texture);
		
		batch.begin();
		
		for(int y = 0; y < things; y++){
			for(int x = 0; x < things; x++){

				batch.uv(0, 0);
				batch.vertex(x, y, 0);
				batch.uv(1, 0);
				batch.vertex(x+1, y, 0);
				batch.uv(1, 1);
				batch.vertex(x+1, y+1, 0);
				

				batch.uv(0, 0);
				batch.vertex(x, y, 0);
				batch.uv(1, 1);
				batch.vertex(x+1, y+1, 0);
				batch.uv(0, 1);
				batch.vertex(x, y+1, 0);
				
			}
		}
		
		batch.end();
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
		
		renderer.model.scale(32,32, 1);
		
		renderer.draw(batch, texture);
		
	}

	@Override
	public void destroy() {
		batch.dispose();
		renderer.dispose();
		texture.dispose();
	}
	
}
