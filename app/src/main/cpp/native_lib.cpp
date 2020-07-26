#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL Java_com_whf_jnitestdemo_JniInterface_stringFromJNI__(
        JNIEnv *env, jobject) {

    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_whf_jnitestdemo_JniInterface_stringFromJNI__Ljava_lang_String_2(
        JNIEnv *env, jobject, jstring) {

    std::string hello = "Hello from C";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jclass JNICALL
Java_com_whf_jnitestdemo_JniInterface_getObjectClass(JNIEnv *env, jobject, jobject obj) {
    return env->GetObjectClass(obj);
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_whf_jnitestdemo_JniInterface_getObjectFiled(JNIEnv *env, jobject, jobject obj) {
    jclass clazz = env->GetObjectClass(obj);
    jfieldID nameId = env->GetFieldID(clazz, "name", "Ljava/lang/String;");
    return (jstring) env->GetObjectField(obj, nameId);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_whf_jnitestdemo_JniInterface_setStaticFiled(JNIEnv *env, jobject, jobject obj) {
    jclass clazz = env->GetObjectClass(obj);
    jfieldID nameId = env->GetStaticFieldID(clazz, "tag", "Ljava/lang/String;");

    char ch[] = "Hello C++";
    jstring new_str = env->NewStringUTF(ch);
    env->SetStaticObjectField(clazz, nameId, new_str);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_whf_jnitestdemo_JniInterface_callMethod(JNIEnv *env, jobject, jobject obj) {
    jclass clazz = env->GetObjectClass(obj);
    jmethodID methodId = env->GetMethodID(clazz, "toast", "()V");
    env->CallVoidMethod(obj, methodId);
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_whf_jnitestdemo_JniInterface_getArrayLength(JNIEnv *env, jobject, jintArray arrayArgs) {
    jint *args = env->GetIntArrayElements(arrayArgs, NULL);
    env->ReleaseIntArrayElements(arrayArgs, args, 0);

    jsize length = env->GetArrayLength(arrayArgs);
    printf("Jni_Test array length = %d", length);
    return length;
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_whf_jnitestdemo_JniInterface_createPerson(JNIEnv *env, jobject, jclass clazz) {

    jmethodID methodOne = env->GetStaticMethodID(clazz, "printLog", "()V");
    env->CallStaticVoidMethod(clazz, methodOne);

    jmethodID constructorMid = env->GetMethodID(clazz, "<init>", "(Ljava/lang/String;Ljava/lang/String;)V");

    const char *charArg1 = "Hello Java!";
    const char *charArg2 = "Hello Jni!";
    jstring arg1 = env->NewStringUTF(charArg1);
    jstring arg2 = env->NewStringUTF(charArg2);

    return env->NewObject(clazz, constructorMid, arg1, arg2);
}