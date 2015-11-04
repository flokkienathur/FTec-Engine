#version 150

uniform sampler2D sampler;

in vec4 v_Color;
in vec2 v_Uv;

void main()
{
    gl_FragColor = texture(sampler, v_Uv) * v_Color;
}