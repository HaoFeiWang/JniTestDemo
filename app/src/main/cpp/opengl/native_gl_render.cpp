//
// Created by LXQ on 2021/1/31.
//

#include "native_gl_render.h"
#include <GLES3/gl3.h>

NativeGLRender *NativeGLRender::mInstance = nullptr;

NativeGLRender::NativeGLRender() {

}

NativeGLRender::~NativeGLRender() {

}

void NativeGLRender::OnSurfaceCreated() {
    glClearColor(1.0f, 1.0f, 0.5f, 1.0f);
}

void NativeGLRender::OnSurfaceChanged(int width, int height) {
    glViewport(0, 0, width, height);
}

void NativeGLRender::OnDrawFrame() {

}

NativeGLRender *NativeGLRender::getInstance() {
    if (mInstance == nullptr) {
        mInstance = new NativeGLRender();
    }
    return mInstance;
}



