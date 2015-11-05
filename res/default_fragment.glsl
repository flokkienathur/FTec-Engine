#version 150

uniform sampler2D sampler;

in vec4 v_Color;
in vec2 v_Uv;

void main()
{
    gl_FragColor =v_Color * texture(sampler, vec2(v_Uv.x, 1.0 - v_Uv.y));
}