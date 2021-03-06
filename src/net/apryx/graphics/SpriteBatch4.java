package net.apryx.graphics;

import java.io.File;
import java.nio.FloatBuffer;

import net.apryx.ftec.graphics.ShaderConstants;
import net.apryx.ftec.graphics.ShaderLoader;
import net.apryx.ftec.graphics.TextureLoader;
import net.apryx.graphics.camera.Camera;
import net.apryx.graphics.camera.OrthagonalCamera;
import net.apryx.graphics.opengl.GL;
import net.apryx.graphics.opengl.ShaderProgram;
import net.apryx.graphics.opengl.Texture;
import net.apryx.graphics.opengl.VBO;
import net.apryx.graphics.sprite.Sprite;
import net.apryx.math.Mathf;
import net.apryx.math.Matrix4;
import net.apryx.utils.BufferUtils;

@Deprecated
public class SpriteBatch4 extends Batch{
	
	private ShaderProgram defaultShader;
	private Texture defaultTexture;
	private Camera defaultCamera;

	private FloatBuffer vertexBuffer;
	private FloatBuffer colorBuffer;
	private FloatBuffer uvBuffer;
	private FloatBuffer normalBuffer;
	
	private boolean drawing = false;
	private int idx = 0;
	private int length = 0;
	private int limit;
	
	private int drawCalls;
	
	private Texture texture;
	private Camera camera;
	private ShaderProgram shader;
	private Surface surface;
	private Matrix4 modelMatrix;
	private Color4 blend;
	
	private int drawMode = VBO.DYNAMIC_DRAW;
	
	private float[] normal = {0,0,1};
	private float[] color = {1,1,1,1};
	private float[] uv = {0,0};
	
	public SpriteBatch4(int vertexCount){
		super();
		
		limit = vertexCount;

		vertexBuffer = BufferUtils.createFloatBuffer(vertexCount * 3);
		colorBuffer = BufferUtils.createFloatBuffer(vertexCount * 4);
		uvBuffer = BufferUtils.createFloatBuffer(vertexCount * 2);
		normalBuffer = BufferUtils.createFloatBuffer(vertexCount * 3);

		vertices.bufferData(null, vertexCount * 3 * 4, drawMode);
		colors.bufferData(null, vertexCount * 4 * 4, drawMode);
		uvs.bufferData(null, vertexCount * 2 * 4, drawMode);
		normals.bufferData(null, vertexCount * 3 * 4, drawMode);

		vao.enableVertexAttribArray(ShaderConstants.POSITION_INDEX);
		vao.enableVertexAttribArray(ShaderConstants.COLOR_INDEX);
		vao.enableVertexAttribArray(ShaderConstants.UV_INDEX);
		vao.enableVertexAttribArray(ShaderConstants.NORMAL_INDEX);
		
		vao.setPointer(ShaderConstants.POSITION_INDEX, vertices, 3, 0, 0);
		vao.setPointer(ShaderConstants.COLOR_INDEX, colors, 4, 0, 0);
		vao.setPointer(ShaderConstants.UV_INDEX, uvs, 2, 0, 0);
		vao.setPointer(ShaderConstants.NORMAL_INDEX, normals, 3, 0, 0);
		
		defaultShader = ShaderLoader.createProgram(new File("res/default_vertex.glsl"), new File("res/default_fragment.glsl"));
		defaultTexture = TextureLoader.loadTexture(1, 1, BufferUtils.createFloatBuffer(new float[]{1,1,1}), Texture.RGB);
		defaultCamera = new OrthagonalCamera(1, 1);
		
		setTexture(defaultTexture);
		setShader(defaultShader);
		setCamera(defaultCamera);
		
		modelMatrix = new Matrix4();
		modelMatrix.setIdentity();
		blend = Color4.white;
	}
	
	public boolean isDrawing() {
		return drawing;
	}

	public void begin(){
		if(drawing)
			return;
		
		drawCalls = 0;
		drawing = true;
		idx = 0;
	}
	
	public void normal(float x, float y, float z){
		normal[0] = x;
		normal[1] = y;
		normal[2] = z;
	}
	
	public void uv(float x, float y){
		uv[0] = x;
		uv[1] = y;
	}
	
	public void color(float r, float g, float b){
		color(r,g,b,1);
	}
	
	public void color(float r, float g, float b, float a){
		color[0] = r;
		color[1] = g;
		color[2] = b;
		color[3] = a;
	}
	
	public void vertex(float x, float y){
		vertex(x,y,0);
	}
	
	public void vertex(float x, float y, float z){
		if(limit - idx <= 0)
			flush();
		
		vertexBuffer.put(x).put(y).put(z);
		colorBuffer.put(color);
		uvBuffer.put(uv);
		normalBuffer.put(normal);
		idx++;
	}
	
	public void flush(){
		if(!drawing)
			return;
		
		if(idx <= 0)
			return;
		
		vertexBuffer.position(0);
		colorBuffer.position(0);
		uvBuffer.position(0);
		normalBuffer.position(0);
		
		//update vbo's
//		vertices.bufferData(vertexBuffer, drawMode);
//		colors.bufferData(colorBuffer, drawMode);
//		uvs.bufferData(uvBuffer, drawMode);
//		normals.bufferData(normalBuffer, drawMode);
		
		vertices.bufferSubData(vertexBuffer, idx * 3);
		colors.bufferSubData(colorBuffer, idx * 4);
		uvs.bufferSubData(uvBuffer, idx * 2);
		normals.bufferSubData(normalBuffer, idx * 3);
		
		length = idx;
		idx = 0;
		
		draw();
	}
	
	public void end(){
		if(drawing)
			flush();
		
		drawing = false;
	}

	@Override
	public void draw() {
		if(surface != null)
			surface.bind();
		
		shader.use();
		
		camera.setup(surface != null);
		
		shader.setUniformMatrixView(camera.view);
		shader.setUniformMatrixProjection(camera.projection);
		
		shader.setUniformMatrixModel(modelMatrix);
		
		shader.setUniformBlend(blend);
		
		if(texture != null)
			texture.bind();
		else
			defaultTexture.bind();
		
		drawCalls++;

		vao.drawArrays(GL.TRIANGLES, 0, length);
		
		if(surface != null)
			surface.unbind();
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		if(texture == null)
			texture = defaultTexture;
		
		if(drawing){
			if(texture.getId() != this.texture.getId()){
				flush();
			}
		}
		
		this.texture = texture;
	}
	
	public void setSurface(Surface surface) {
		if(drawing){
			if(surface == null && this.surface != null)
				flush();
			else if(this.surface == null && surface != null)
				flush();
			else if(!(surface == null && this.surface == null) &&surface.getId() != this.surface.getId()){
				flush();
			}
		}
		this.surface = surface;
	}
	
	public Surface getSurface() {
		return surface;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		if(camera == null)
			camera = defaultCamera;
		
		if(drawing)
			flush();
		this.camera = camera;
	}

	public ShaderProgram getShader() {
		return shader;
	}

	public void setShader(ShaderProgram shader) {
		if(shader == null)
			shader = defaultShader;
		
		if(drawing){
			if(shader.getId() != this.shader.getId()){
				flush();
			}
		}
		
		this.shader = shader;
	}
	
	public void drawSprite(Sprite sprite, float x, float y){
		drawSprite(sprite,x,y,1,1);
	}
	
	public void drawSprite(Sprite sprite, float x, float y, float xScale, float yScale){
		drawSprite(sprite,x,y,xScale,yScale,0);
	}
	
	public void drawSprite(Sprite sprite, float x, float y, float rotation){
		drawSprite(sprite,x,y,1,1,rotation);
	}
	
	public void drawSprite(Sprite sprite, float x, float y, float xScale, float yScale, float rotation){
		drawTexture(sprite.getTexture(), sprite.getWidth(), sprite.getHeight(), sprite.getxOffset(), sprite.getyOffset(), x, y, xScale, yScale, rotation);
	}
	
	public void drawTexture(Texture texture, float width, float height, float x, float y, float xScale, float yScale, float rotation){
		drawTexture(texture, width, height, 0, 0, x, y, xScale, yScale, rotation);
	}
	
	public void drawTexture(Texture texture, float width, float height, float xOffset, float yOffset, float x, float y, float xScale, float yScale, float rotation){
		float s, c, x1, x2, x3, x4, y1, y2, y3, y4, w, h, xo, yo;
		Texture t;
		
		w = width * xScale;
		h = height * yScale;

		xo = xOffset * xScale;
		yo = yOffset * yScale;
		
		if(rotation == 0){
			//1 --- 2
			//|		|
			//4 --- 3
			
			x1 = x - xo;
			x2 = x1 + w;
			x3 = x2;
			x4 = x1;
			
			y1 = y - yo;
			y2 = y1;
			
			y3 = y1 + h;
			y4 = y3;
			
		}else{
			s = Mathf.sin(rotation);
			c = Mathf.cos(rotation);
			
			//c -s  0
			//s  c  0
			//0  0  1

			x1 = x + ((-xo) * c - (-yo) * s);
			y1 = y + ((-xo) * s + (-yo) * c);

			x2 = x + ((-xo + w) * c - (-yo) * s);
			y2 = y + ((-xo + w) * s + (-yo) * c);

			x3 = x + ((-xo + w) * c - (-yo + h) * s);
			y3 = y + ((-xo + w) * s + (-yo + h) * c);

			x4 = x + ((-xo) * c - (-yo + h) * s);
			y4 = y + ((-xo) * s + (-yo + h) * c);
			
		}
		
		//1 --- 2
		//|		|
		//4 --- 3
		
		t = texture;
		
		setTexture(t);
		
		if(limit - idx < 6){
			flush();
		}
		
		uv(t.getTexCoordX(),t.getTexCoordY());
		vertex(x1, y1);
		uv(t.getTexCoordX2(),t.getTexCoordY2());
		vertex(x3, y3);
		uv(t.getTexCoordX2(),t.getTexCoordY());
		vertex(x2, y2);

		uv(t.getTexCoordX(),t.getTexCoordY());
		vertex(x1, y1);
		uv(t.getTexCoordX(),t.getTexCoordY2());
		vertex(x4, y4);
		uv(t.getTexCoordX2(),t.getTexCoordY2());
		vertex(x3, y3);
	}
	
	public int getDrawCalls(){
		return drawCalls;
	}

	public int getDrawMode() {
		return drawMode;
	}

	public void setDrawMode(int drawMode) {
		this.drawMode = drawMode;
	}
	
	public void dispose(){
		defaultTexture.dispose();
		defaultShader.dispose();
	}
}
