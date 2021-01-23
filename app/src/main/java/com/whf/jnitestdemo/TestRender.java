package com.whf.jnitestdemo;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class TestRender implements GLSurfaceView.Renderer {

    private static final String TAG = TestRender.class.getSimpleName();

    private final Context mContext;
    private int program;
    private int vPosition;

    public TestRender(Context context) {
        mContext = context;
    }

    private void createProgram(Context context) {
        GLES20.glGetAttribLocation(program, "vPosition");
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        String triangleVertex = AssetUtils.readAssetText(mContext, "triangle_vertex.glsl");
        String triangleFragment = AssetUtils.readAssetText(mContext, "triangle_fragment.glsl");
        program = ShaderUtils.createAndLinkProgram(triangleVertex, triangleFragment);
        //获取Program的vPosition句柄
        vPosition = GLES20.glGetAttribLocation(program, "vPosition");
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //创建OpenGL ES绘制窗口
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //启动指定program
        GLES20.glUseProgram(program);
        GLES20.glEnableVertexAttribArray(vPosition);
        GLES20.glVertexAttribPointer(vPosition, 3, GLES20.GL_FLOAT, false, 0, createBuffer());
        //参数一为绘制方式；参数二为从数组缓存中哪一位开始画；参数三为绘制顶点数量
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
    }

    private FloatBuffer createBuffer(){
        float[] vertexArray = {0.0f, 0.5f, 0.0f, // top
                -0.5f, -0.5f, 0.0f, // bottom left
                0.5f, -0.5f, 0.0f};  // bottom right
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertexArray.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer buffer = byteBuffer.asFloatBuffer();
        buffer.put(vertexArray);
        buffer.position(0);
        return buffer;
    }

}

