#version 150

uniform sampler2D sampler;

in vec4 v_Position;
in vec4 v_Color;
in vec2 v_Uv;

void main()
{
    gl_FragColor = v_Color * texture(sampler, v_Uv);
    float a = gl_FragColor.a;
    gl_FragColor.a = a;
}