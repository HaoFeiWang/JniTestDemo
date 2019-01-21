#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_whf_jnitestdemo_JniTest_stringFromJNI(
        JNIEnv *env, jobject) {

    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_whf_jnitestdemo_JniTest_stringFromJNI__Ljava_lang_String_2(
        JNIEnv *env, jobject, jstring) {

    std::string hello = "Hello from C";
    return env->NewStringUTF(hello.c_str());
}


extern "C"
JNIEXPORT jint JNICALL
Java_com_whf_jnitestdemo_JniTest_intFrom_1JNI(
        JNIEnv *env, jobject) {

    // TODO
}
