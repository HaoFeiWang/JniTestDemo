package com.whf.jnitestdemo.opengl;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class NativeRender implements GLSurfaceView.Renderer {

    private JniRendererInterface jniRendererInterface;

    public NativeRender(){
        jniRendererInterface = new JniRendererInterface();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        jniRendererInterface.init();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {

    }
}
