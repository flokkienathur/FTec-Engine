#version 150

//get this from somewhere over the rainbow ~
in vec3 position;
in vec4 color;
in vec2 uv;


//pass this to the fragment shader
out vec4 v_Color;
out vec2 v_Uv;

void main()
{
	v_Uv = uv;
	v_Color = color;
    gl_Position = vec4(position / 2.0, 1.0);
}