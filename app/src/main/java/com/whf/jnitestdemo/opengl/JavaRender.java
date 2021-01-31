package com.whf.jnitestdemo.opengl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.whf.jnitestdemo.AssetUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class JavaRender implements GLSurfaceView.Renderer {

    private static final String TAG = JavaRender.class.getSimpleName();

    private final Context mContext;
    private int program;
    private int vPosition;

    public JavaRender(Context context) {
        mContext = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.i(TAG,"onSurfaceCreated");
        String triangleVertex = AssetUtils.readAssetText(mContext, "triangle_vertex.glsl");
        String triangleFragment = AssetUtils.readAssetText(mContext, "triangle_fragment.glsl");
        program = ShaderUtils.createAndLinkProgram(triangleVertex, triangleFragment);
        //获取Program的vPosition句柄
        vPosition = GLES20.glGetAttribLocation(program, "vPosition");
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.i(TAG,"onSurfaceChanged");
        //创建OpenGL ES绘制窗口
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.i(TAG,"onDrawFrame");
        //启动指定program
        GLES20.glUseProgram(program);
        GLES20.glEnableVertexAttribArray(vPosition);
        //设置顶点数据到Program，参数二为每个顶点的个数
        GLES20.glVertexAttribPointer(vPosition, 3, GLES20.GL_FLOAT, false, 0, createBuffer());
        //参数一为绘制方式；参数二为从数组缓存中哪一位开始画；参数三为绘制顶点数量
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
    }

    private FloatBuffer createBuffer(){
        //x、y、z
        float[] vertexArray = {0.0f, 0.5f, 0.5f, // top
                -0.5f, -0.5f, 0.0f, // bottom left
                0.5f, -0.5f, 0.0f};  // bottom right

        //将数组转换成FloatBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertexArray.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer buffer = byteBuffer.asFloatBuffer();
        buffer.put(vertexArray);
        buffer.position(0);
        return buffer;
    }

}

