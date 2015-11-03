#version 150

//get this from somewhere over the rainbow ~
in vec4 color;
in vec3 position;

//pass this to the fragment shader
out vec4 v_Color;

void main()
{
	v_Color = color;
    gl_Position = vec4(position, 1.0);
}