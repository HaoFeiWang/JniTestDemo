//
// Created by root on 2019/1/22.
// 动态注册
//
#include <jni.h>
#include <stdio.h>
#include <string>
#include "android_log.h"


#ifdef __cplusplus
extern "C" {
#endif

//定义类名
static const char *className = "com/whf/jnitestdemo/JniInterface";

//定义对应Java native方法的 C++ 函数，函数名可以随意命名
static jstring sayHello(JNIEnv *env, jobject) {
    LOGI("hello, this is native log.");
    const char *hello = "Hello from C++.";
    return env->NewStringUTF(hello);
}

/*
 * 定义函数映射表（是一个数组，可以同时定义多个函数的映射）
 * 参数1：Java 方法名
 * 参数2：方法描述符，也就是签名
 * 参数3：C++定义对应 Java native方法的函数名
 */
static JNINativeMethod jni_Methods_table[] = {
        {"dynamicRegister", "()Ljava/lang/String;", (void *) sayHello},
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
    registerNativeMethods(env, className, jni_Methods_table,
                          sizeof(jni_Methods_table) / sizeof(JNINativeMethod));
    return JNI_VERSION_1_4;
}

#ifdef __cplusplus
}
#endif