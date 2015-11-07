package net.apryx.ftec.graphics;

import java.io.File;

import net.apryx.graphics.Batch;
import net.apryx.graphics.Camera;
import net.apryx.graphics.Color4;
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
	
	public Color4 blend;
	
	public Renderer(){
		defaultShader = ShaderLoader.createProgram(new File("res/default_vertex.glsl"), new File("res/default_fragment.glsl"));
		
		quad = new MeshBatch(new QuadMesh(0,0,1,1));
		
		white = TextureLoader.loadTexture(1, 1, BufferUtils.createFloatBuffer(new float[]{1,1,1}), Texture.RGB);
		
		setShader(defaultShader);
		
		model = new Matrix4();
		
		blend = Color4.white.clone();
	}
	
	public void setShader(ShaderProgram program){
		shader = program;
	}
	
	public void clear(){
		GL.clear(GL.DEPTH_BUFFER_BIT | GL.COLOR_BUFFER_BIT);
	}
	
	public void setup(Camera camera){
		shader.use();
		
		camera.setup();
		
		shader.setUniformMatrixView(camera.view);
		shader.setUniformMatrixProjection(camera.projection);
	}
	
	public void drawRectangle(float x, float y, float w, float h){
		drawTexture(white, x, y, w, h);
	}
	
	public void drawTexture(Texture texture, float x, float y, float w, float h){
		model.translate(x, y, 0);
		model.scale(w, h, 0);
		
		draw(quad, texture);
		
		model.scale(1f/w, 1f/h, 0);
		model.translate(-x, -y, 0);
	}
	
	public void draw(Batch renderer, Texture texture){
		shader.setUniformMatrixModel(model);
		
		shader.setUniformBlend(blend);
		
		if(texture != null)
			texture.bind();
		else
			white.bind();
		
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
