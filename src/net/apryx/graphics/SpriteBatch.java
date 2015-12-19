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
import net.apryx.graphics.opengl.VAO;
import net.apryx.graphics.opengl.VBO;
import net.apryx.graphics.sprite.Sprite;
import net.apryx.math.Mathf;
import net.apryx.math.Matrix4;
import net.apryx.utils.BufferUtils;

public class SpriteBatch {

	private static final int SIZEOF_FLOAT = 4;
	
	private static final int SIZEOF_VERTEX = SIZEOF_FLOAT * 3;
	private static final int SIZEOF_COLOR = SIZEOF_FLOAT * 4;
	private static final int SIZEOF_UV = SIZEOF_FLOAT * 2;
	private static final int SIZEOF_NORMAL = SIZEOF_FLOAT * 3;

	private static final int SIZEOF_VERTEX_DATA = SIZEOF_VERTEX + SIZEOF_COLOR + SIZEOF_UV + SIZEOF_NORMAL;
	
	private ShaderProgram defaultShader;
	private Texture defaultTexture;
	private Camera defaultCamera;
	
	private FloatBuffer vertexBuffer;
	
	private VBO vbo;
	private VAO vao;
	
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
	
	private float rest[] = {1,1,1,1, 0,0, 0,0,1};
	
	public SpriteBatch(int vertexCount){
		super();
		
		limit = vertexCount;
		
		vbo = new VBO(VBO.ARRAY_BUFFER);
		vao = new VAO();

		//amount of floats
		vertexBuffer = BufferUtils.createFloatBuffer(vertexCount * SIZEOF_VERTEX_DATA / SIZEOF_FLOAT);

		//load the empty data
		vbo.bufferData(null, vertexCount * SIZEOF_VERTEX_DATA, drawMode);

		//enable all the bullcrap here
		vao.enableVertexAttribArray(ShaderConstants.POSITION_INDEX);
		vao.enableVertexAttribArray(ShaderConstants.COLOR_INDEX);
		vao.enableVertexAttribArray(ShaderConstants.UV_INDEX);
		vao.enableVertexAttribArray(ShaderConstants.NORMAL_INDEX);
		
		//setup the pointers right
		vao.setPointer(ShaderConstants.POSITION_INDEX, vbo, 3, SIZEOF_VERTEX_DATA, 0);
		vao.setPointer(ShaderConstants.COLOR_INDEX, vbo, 4, SIZEOF_VERTEX_DATA, SIZEOF_VERTEX);
		vao.setPointer(ShaderConstants.UV_INDEX, vbo, 2, SIZEOF_VERTEX_DATA, SIZEOF_VERTEX + SIZEOF_COLOR);
		vao.setPointer(ShaderConstants.NORMAL_INDEX, vbo, 3, SIZEOF_VERTEX_DATA, SIZEOF_VERTEX + SIZEOF_COLOR + SIZEOF_UV);
		
		//create the default shaders and shit
		defaultShader = ShaderLoader.createProgram(new File("res/default_vertex.glsl"), new File("res/default_fragment.glsl"));
		defaultTexture = TextureLoader.loadTexture(1, 1, BufferUtils.createFloatBuffer(new float[]{1,1,1}), Texture.RGB);
		defaultCamera = new OrthagonalCamera(1, 1);
		
		//set them
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
		//{0,1,2,3, 4,5, 6,7,8};
		//{1,1,1,1, 0,0, 0,0,1};
		rest[6] = x;
		rest[7] = y;
		rest[8] = z;
	}
	
	public void uv(float x, float y){
		//{0,1,2,3, 4,5, 6,7,8};
		//{1,1,1,1, 0,0, 0,0,1};
		rest[4] = x;
		rest[5] = y;
	}
	
	public void color(float r, float g, float b){
		color(r,g,b,1);
	}
	
	public void color(float r, float g, float b, float a){
		//{0,1,2,3, 4,5, 6,7,8};
		//{1,1,1,1, 0,0, 0,0,1};
		rest[0] = r;
		rest[1] = g;
		rest[2] = b;
		rest[3] = a;
	}
	
	public void vertex(float x, float y){
		vertex(x,y,0);
	}
	
	public void vertex(float x, float y, float z){
		if(limit - idx <= 0)
			flush();
		
		vertexBuffer.put(x).put(y).put(z).put(rest);
		
		idx++;
	}
	
	public void flush(){
		if(!drawing)
			return;
		
		if(idx <= 0)
			return;
		
		vertexBuffer.position(0);
		
		//update vbo's
		
		//upload the data to the vbo
		//in float count
		vbo.bufferSubData(vertexBuffer, idx * SIZEOF_VERTEX_DATA / SIZEOF_FLOAT);
		
		length = idx;
		idx = 0;
		
		draw();
	}
	
	public void end(){
		if(drawing)
			flush();
		
		drawing = false;
	}

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
	
	/**
	 * Draws a plane (same as quad, but laying on the ground)
	 * @param texture
	 * @param x
	 * @param y
	 * @param z
	 * @param up
	 * @param width
	 * @param height
	 * @param xOffset
	 * @param yOffset
	 */
	public void drawPlane(Texture texture, float x, float y, float z, float up, float width, float height, float xOffset, float yOffset){
		setTexture(texture);
		
		if(up > 0){
			uv(texture.getTexCoordX(), texture.getTexCoordY());
			vertex(x - xOffset, y, z - yOffset);
			uv(texture.getTexCoordX2(), texture.getTexCoordY2());
			vertex(x - xOffset + width, y, z - yOffset + height);
			uv(texture.getTexCoordX2(), texture.getTexCoordY());
			vertex(x - xOffset + width, y, z - yOffset);
			
			
			uv(texture.getTexCoordX(), texture.getTexCoordY());
			vertex(x - xOffset, y, z - yOffset);
			uv(texture.getTexCoordX(), texture.getTexCoordY2());
			vertex(x - xOffset, y, z - yOffset + height);
			uv(texture.getTexCoordX2(), texture.getTexCoordY2());
			vertex(x - xOffset + width, y, z - yOffset + height);
		}else{
			uv(texture.getTexCoordX(), texture.getTexCoordY());
			vertex(x - xOffset, y, z - yOffset);
			uv(texture.getTexCoordX2(), texture.getTexCoordY());
			vertex(x - xOffset + width, y, z - yOffset);
			uv(texture.getTexCoordX2(), texture.getTexCoordY2());
			vertex(x - xOffset + width, y, z - yOffset + height);
			
			
			uv(texture.getTexCoordX(), texture.getTexCoordY());
			vertex(x - xOffset, y, z - yOffset);
			uv(texture.getTexCoordX2(), texture.getTexCoordY2());
			vertex(x - xOffset + width, y, z - yOffset + height);
			uv(texture.getTexCoordX(), texture.getTexCoordY2());
			vertex(x - xOffset, y, z - yOffset + height);
		}
		
	}
	
	/**
	 * Draws a quad standing up, nx and ny are the normals (for a 3d that would be vec3(nx,0,ny) )
	 * 
	 * @param texture
	 * @param x position x
	 * @param y position y
	 * @param z position z
	 * @param nx Normal x
	 * @param ny Normal z
	 * @param width Quads width
	 * @param height Quads height
	 * @param xOffset Offset
	 * @param yOffset Offset
	 */
	public void drawQuad(Texture texture, float x, float y, float z, float nx, float ny, float width, float height, float xOffset, float yOffset){
		
		float dxs = (-xOffset) * nx;
		float dys = (-xOffset) * -ny;
		float dxe = (-xOffset + width) * nx;
		float dye = (-xOffset + width) * -ny; 
		
		float hs = y - yOffset;
		float he = y - yOffset + height;
		
		setTexture(texture);
		
		uv(texture.getTexCoordX2(), texture.getTexCoordY2());
		vertex( x + dys, hs, z+dxs);
		uv(texture.getTexCoordX(), texture.getTexCoordY());
		vertex( x + dye, he, z+dxe);
		uv(texture.getTexCoordX(), texture.getTexCoordY2());
		vertex( x + dye, hs, z+dxe);
		

		uv(texture.getTexCoordX2(), texture.getTexCoordY2());
		vertex( x + dys, hs, z+dxs);
		uv(texture.getTexCoordX2(), texture.getTexCoordY());
		vertex( x + dys, he, z+dxs);
		uv(texture.getTexCoordX(), texture.getTexCoordY());
		vertex( x + dye, he, z+dxe);
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
