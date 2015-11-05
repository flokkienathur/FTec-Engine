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
import net.apryx.utils.BufferUtils;


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
		
		//quad = new QuadMesh(-0.5f, -0.5f, 0.5f, 0.5f);
		quad = new CircleMesh(0.5f,12);
		
		renderer = new MeshRenderer(quad);
		
		texture = TextureLoader.loadTexture(2, 2, BufferUtils.createFloatBuffer(new float[]{
				1.0f, 1.0f, 1.0f,   0.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,   1.0f, 1.0f, 1.0f
		}),Texture.RGB);

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
		
		int calls = 0;

		model.setIdentity();
		for(float y = -2; y < 2; y += 0.1f){
			for(float x = -2; x < 2; x += 0.1f){
				model.setTranslate(x,y,0);
				model.setScale(0.1f, 0.1f, 1);
				program.setUniformMatrixModel(model);
				calls++;
				renderer.draw();
			}
		}
		
		System.out.println(calls);
					
	}

	@Override
	public void destroy() {
		renderer.dispose();
		
		program.dispose();
		texture.dispose();
	}
	
}
