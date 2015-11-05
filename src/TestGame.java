import java.io.File;

import net.apryx.ftec.engine.FTec;
import net.apryx.ftec.engine.Game;
import net.apryx.ftec.graphics.ShaderLoader;
import net.apryx.ftec.graphics.TextureLoader;
import net.apryx.graphics.GL;
import net.apryx.graphics.ShaderProgram;
import net.apryx.graphics.Texture;
import net.apryx.graphics.mesh.CircleMesh;
import net.apryx.graphics.mesh.Mesh;
import net.apryx.graphics.mesh.MeshRenderer;
import net.apryx.math.Matrix4;


public class TestGame extends Game{

	private Mesh quad;
	private MeshRenderer renderer;
	
	private ShaderProgram program;
	private Texture texture;
	
	private Matrix4 model;
	private Matrix4 projection;
	
	@Override
	public void init() {
		GL.clearColor(0,0,1,1);
		
		quad = new CircleMesh(0.5f,6);
		
		renderer = new MeshRenderer(quad);
		
		texture = TextureLoader.loadTexture(new File("res/thing.jpg"));

		model = new Matrix4();
		projection = new Matrix4();
		
		program = ShaderLoader.createProgram(new File("res/default_vertex.glsl"), new File("res/default_fragment.glsl"));
	}

	@Override
	public void update() {

	}

	@Override
	public void render() {
		FTec.clear();
		
		program.use();
		
		projection.setIdentity();
		projection.setScale(1, 4f/3f, 1);
		
		texture.bind();

		program.setUniformMatrixProjection(projection);
		model.setTranslate(0,0,0);
		program.setUniformMatrixModel(model);

		model.setIdentity();
		renderer.draw();
					
	}

	@Override
	public void destroy() {
		renderer.dispose();
		
		program.dispose();
		texture.dispose();
	}
	
}
