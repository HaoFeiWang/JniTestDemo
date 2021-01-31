package com.whf.jnitestdemo.opengl;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.whf.jnitestdemo.R;

public class OpenGlActivity extends AppCompatActivity {

    private GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gl);
        glSurfaceView = findViewById(R.id.gl_view);

        glSurfaceView.setEGLContextClientVersion(2);
        //glSurfaceView.setRenderer(new JavaRender(this));
        glSurfaceView.setRenderer(new NativeRender());
        //设置GlSurfaceView渲染模式，一定要在setRenderer之后调用
        //RENDERMODE_WHEN_DIRTY只有在调用requestRender或者onResume等方法时才渲染
        //RENDERMODE_CONTINUOUSLY表示一直渲染
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }
}
