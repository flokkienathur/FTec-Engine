package net.apryx.ftec.graphics;

import java.io.File;

import net.apryx.graphics.opengl.GL;
import net.apryx.graphics.opengl.Shader;
import net.apryx.graphics.opengl.ShaderProgram;

/**
 * Loads shaders and sets things up for the Engine pipeline
 * @author Folkert
 */
public class ShaderLoader {
	
	public ShaderLoader(){
	}
	
	public static Shader createVertexShader(File file){
		Shader shader = new Shader(Shader.VERTEX_SHADER);
		shader.source(file);
		shader.compile();
		GL.checkError();
		//if the compile worked
		if(shader.isCompiled()){
			return shader;
		}else{
			shader.dispose();
			return null;
		}
	}
	
	public static Shader createFragmentShader(File file){
		Shader shader = new Shader(Shader.FRAGMENT_SHADER);
		shader.source(file);
		shader.compile();
		GL.checkError();
		//if the compile worked
		if(shader.isCompiled()){
			return shader;
		}else{
			shader.dispose();
			return null;
		}
	}
	
	public static ShaderProgram createProgram(File vertex, File fragment){
		return createProgram(
				createVertexShader(vertex),
				createFragmentShader(fragment)
				);
	}
	
	public static ShaderProgram createProgram(Shader vertex, Shader fragment){
		ShaderProgram program = new ShaderProgram();
		program.attach(vertex);
		program.attach(fragment);
		
		program.bindAttributeLocation(ShaderConstants.POSITION_INDEX, ShaderConstants.POSITION_ATTR);
		program.bindAttributeLocation(ShaderConstants.COLOR_INDEX, ShaderConstants.COLOR_ATTR);
		program.bindAttributeLocation(ShaderConstants.UV_INDEX, ShaderConstants.UV_ATTR);
		program.bindAttributeLocation(ShaderConstants.NORMAL_INDEX, ShaderConstants.NORMAL_ATTR);
		
		program.link();
		
		return program;
	}
	
}
