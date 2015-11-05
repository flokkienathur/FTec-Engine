package net.apryx.ftec.graphics;

import java.io.File;

import net.apryx.graphics.Camera;
import net.apryx.graphics.GL;
import net.apryx.graphics.ShaderProgram;
import net.apryx.graphics.Texture;
import net.apryx.graphics.mesh.MeshBatch;
import net.apryx.graphics.mesh.QuadMesh;
import net.apryx.math.Matrix4;
import net.apryx.utils.BufferUtils;

public class Renderer {

	private ShaderProgram defaultShader;
	
	private MeshBatch quad;
	
	private Texture white;
	
	private ShaderProgram shader;

	public Matrix4 model;
	public Camera camera;
	
	public Renderer(){
		defaultShader = ShaderLoader.createProgram(new File("res/default_vertex.glsl"), new File("res/default_fragment.glsl"));
		
		quad = new MeshBatch(new QuadMesh(0,0,1,1));
		
		white = TextureLoader.loadTexture(1, 1, BufferUtils.createFloatBuffer(new float[]{1,1,1}), Texture.RGB);
		
		setShader(defaultShader);
		
		model = new Matrix4();
		camera = new Camera();
	}
	
	public void setShader(ShaderProgram program){
		shader = program;
	}
	
	public void clear(){
		GL.clear(GL.DEPTH_BUFFER_BIT | GL.COLOR_BUFFER_BIT);
	}
	
	public void setup(){
		shader.use();
		
		camera.setup();
		
		shader.setUniformMatrixView(camera.view);
		shader.setUniformMatrixProjection(camera.projection);
	}
	
	public void drawTexture(Texture texture, float x, float y, float w, float h){
		model.setIdentity();
		model.setTranslate(x, y, 0);
		model.setScale(w, h, 0);
		
		draw(quad, texture);
	}
	
	public void draw(MeshBatch renderer, Texture texture){
		shader.setUniformMatrixModel(model);
		texture.bind();
		
		renderer.draw();
	}
	
	public void draw(MeshBatch renderer){
		draw(renderer, white);
	}
	
	public void dispose(){
		defaultShader.dispose();
		quad.dispose();
		white.dispose();
	}
	
}
