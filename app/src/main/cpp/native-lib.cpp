#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_whf_jnitestdemo_JniTest_stringFromJNI__(
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
JNIEXPORT jclass JNICALL
Java_com_whf_jnitestdemo_JniTest_getObjectClass(JNIEnv *env, jobject, jobject obj) {
    return env->GetObjectClass(obj);
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_whf_jnitestdemo_JniTest_getObjectFiled(JNIEnv *env, jobject, jobject obj) {
    jclass clazz = env->GetObjectClass(obj);
    jfieldID nameId = env->GetFieldID(clazz,"name","Ljava/lang/String;");
    return (jstring) env->GetObjectField(obj, nameId);
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_whf_jnitestdemo_JniTest_setStaticFiled(JNIEnv *env, jobject, jobject obj) {
    jclass clazz = env->GetObjectClass(obj);
    jfieldID nameId = env->GetStaticFieldID(clazz,"tag","Ljava/lang/String;");

    jstring old_str = (jstring) env->GetStaticObjectField(clazz, nameId);
    const char* str = env->GetStringUTFChars(old_str, JNI_FALSE);


    char ch[30] = "Hello C++";
    jstring new_str = env -> NewStringUTF(ch);
    env -> SetStaticObjectField(clazz, nameId, new_str);
}