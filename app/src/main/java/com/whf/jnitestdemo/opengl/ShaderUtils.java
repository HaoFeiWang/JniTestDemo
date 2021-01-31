package com.whf.jnitestdemo.opengl;

import android.opengl.GLES20;
import android.util.Log;

public class ShaderUtils {

    private static final String TAG = ShaderUtils.class.getSimpleName();

    /**
     * @param shaderType {@link GLES20#GL_VERTEX_SHADER} {@link GLES20#GL_FRAGMENT_SHADER}
     * @param shaderSource glsl源码
     * @return shader
     */
    private static int compileShader(int shaderType,String shaderSource){
        //创建空Shader，返回值为Shader句柄
        int shader = GLES20.glCreateShader(shaderType);
        if (shader == 0){
            return 0;
        }

        //加载Shader源码
        GLES20.glShaderSource(shader,shaderSource);
        //编译Shader
        GLES20.glCompileShader(shader);
        int[] compileStatus = new int[1];
        //获取Shader编译状态
        GLES20.glGetShaderiv(shader,GLES20.GL_COMPILE_STATUS,compileStatus,0);
        if (compileStatus[0] == 0){
            Log.e(TAG,"compile shader error = "+GLES20.glGetShaderInfoLog(shader));
            GLES20.glDeleteShader(shader);
            shader = 0;
        }

        return shader;
    }

    public static int createAndLinkProgram(String vertexCode,String fragmentCode){
        //创建一个空的Program，返回值为Program句柄
        int program = GLES20.glCreateProgram();
        if (program == 0){
            return program;
        }

        //编译Shader
        int vertexShader = compileShader(GLES20.GL_VERTEX_SHADER,vertexCode);
        int fragmentShader = compileShader(GLES20.GL_FRAGMENT_SHADER,fragmentCode);

        //绑定Shader和Program
        GLES20.glAttachShader(program,vertexShader);
        GLES20.glAttachShader(program,fragmentShader);
        //链接Program
        GLES20.glLinkProgram(program);

        int[] linkStatus = new int[1];
        //获取链接Program状态
        GLES20.glGetProgramiv(program,GLES20.GL_LINK_STATUS,linkStatus,0);
        if (linkStatus[0] == 0){
            Log.e(TAG, "link program error = " + GLES20.glGetProgramInfoLog(program));
            GLES20.glDeleteProgram(program);
            program = 0;
        }

        return program;
    }
}
