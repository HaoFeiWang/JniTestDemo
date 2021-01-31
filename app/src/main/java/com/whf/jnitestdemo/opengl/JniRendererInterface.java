package com.whf.jnitestdemo.opengl;

public class JniRendererInterface {

    public native void init();

    public native void release();

    public native void setImageData(int format, int width, int height, byte[] bytes);

    public native void onSurfaceCreated();

    public native void onSurfaceChanged(int width, int height);

    public native void onDrawFrame();
}
