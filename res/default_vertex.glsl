#version 150

//get this from somewhere over the rainbow ~
in vec3 position;
in vec4 color;
in vec2 uv;

uniform mat4 u_MatrixModel;
uniform mat4 u_MatrixProjection;
uniform mat4 u_MatrixView;
uniform vec4 u_Blend;


//pass this to the fragment shader
out vec4 v_Position;
out vec4 v_Color;
out vec2 v_Uv;

void main()
{
	v_Uv = uv;
	v_Color = color * u_Blend;
    v_Position = u_MatrixView * u_MatrixModel * vec4(position, 1.0);
    gl_Position = u_MatrixProjection * v_Position;
}