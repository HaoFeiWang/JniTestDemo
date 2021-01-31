//
// Created by root on 2019/1/22.
// 动态注册
//
#include <jni.h>
#include <stdio.h>
#include <string>
#include "../android_log.h"
#include "native_gl_render.h"

#ifdef __cplusplus
extern "C" {
#endif

#define jniClassName "com/whf/jnitestdemo/opengl/JniRendererInterface"

JNIEXPORT void JNICALL OpenGL_init(JNIEnv *env, jobject instance) {
    LOGD("jni call OpenGL_init");
    NativeGLRender::getInstance();
}

JNIEXPORT void JNICALL OpenGL_release(JNIEnv *env, jobject instance) {
    LOGD("jni call OpenGL_release");

}

JNIEXPORT void JNICALL OpenGL_setImageData(JNIEnv *env, jobject instance,
                                           jint format, jint width, jint height,
                                           jbyteArray imageData) {
    LOGD("jni call OpenGL_setImageData");
}

JNIEXPORT void JNICALL OpenGL_onSurfaceCreated(JNIEnv *env, jobject instance) {
    LOGD("jni call OpenGL_onSurfaceCreated");
    NativeGLRender::getInstance()->OnSurfaceCreated();

}

JNIEXPORT void JNICALL
OpenGL_onSurfaceChanged(JNIEnv *env, jobject instance, jint width, jint height) {
    LOGD("jni call OpenGL_onSurfaceChanged");
    NativeGLRender::getInstance()->OnSurfaceChanged(width, height);
}

JNIEXPORT void JNICALL OpenGL_onDrawFrame(JNIEnv *env, jobject instance) {
    LOGD("jni call OpenGL_onDrawFrame");
    NativeGLRender::getInstance()->OnDrawFrame();
}

/*
 * 定义函数映射表（是一个数组，可以同时定义多个函数的映射）
 * 参数1：Java 方法名
 * 参数2：方法描述符，也就是签名
 * 参数3：C++定义对应 Java native方法的函数名
 */
static JNINativeMethod jni_Methods_table[] = {
        {"init",             "()V",      (void *) OpenGL_init},
        {"release",          "()V",      (void *) OpenGL_release},
        {"setImageData",     "(III[B)V", (void *) OpenGL_setImageData},
        {"onSurfaceCreated", "()V",      (void *) OpenGL_onSurfaceCreated},
        {"onSurfaceChanged", "(II)V",    (void *) OpenGL_onSurfaceChanged},
        {"onDrawFrame",      "()V",      (void *) OpenGL_onDrawFrame},
};

//根据函数映射表注册函数
static int registerNativeMethods(JNIEnv *env, const char *className,
                                 const JNINativeMethod *gMethods, int numMethods) {
    jclass clazz;
    LOGI("Registering %s natives\n", className);
    clazz = (env)->FindClass(className);
    if (clazz == NULL) {
        LOGE("Native registration unable to find class '%s'\n", className);
        return JNI_ERR;
    }

    if ((env)->RegisterNatives(clazz, gMethods, numMethods) < 0) {
        LOGE("Register natives failed for '%s'\n", className);
        return JNI_ERR;
    }
    //删除本地引用
    (env)->DeleteLocalRef(clazz);
    return JNI_OK;
}

//在Java中通过System.loadLibrary加载完JNI动态库之后，会自动调用JNI_OnLoad函数，完成动态注册；
jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    LOGI("call JNI_OnLoad");
    JNIEnv *env = NULL;
    //判断 JNI 版本是否为JNI_VERSION_1_4
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_4) != JNI_OK) {
        return JNI_EVERSION;
    }
    registerNativeMethods(env, jniClassName, jni_Methods_table,
                          sizeof(jni_Methods_table) / sizeof(JNINativeMethod));
    return JNI_VERSION_1_4;
}

#ifdef __cplusplus
}
#endif
