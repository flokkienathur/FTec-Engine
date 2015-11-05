package net.apryx.ftec.graphics;

import java.io.File;

import net.apryx.graphics.GL;
import net.apryx.graphics.Shader;
import net.apryx.graphics.ShaderProgram;

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
		
		program.bindAttributeLocation(Batch.POSITION_INDEX, Batch.POSITION_ATTR);
		program.bindAttributeLocation(Batch.COLOR_INDEX, Batch.COLOR_ATTR);
		program.bindAttributeLocation(Batch.UV_INDEX, Batch.UV_ATTR);
		program.bindAttributeLocation(Batch.NORMAL_INDEX, Batch.NORMAL_ATTR);
		
		program.link();
		
		return program;
	}
	
}
