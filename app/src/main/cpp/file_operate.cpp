#include <jni.h>
#include <string>
#include <stdio.h>
#include <stdlib.h>
#include "android_log.h"


extern "C"
JNIEXPORT jstring JNICALL
Java_com_whf_jnitestdemo_JniInterface_readFile(JNIEnv *env, jobject) {
    const char error_desc[] = "open file fail!";
    const char success_desc[] = "open file success!";

    const jstring error_string = env->NewStringUTF(error_desc);
    const jstring success_string = env->NewStringUTF(success_desc);

    FILE *fp;
    if ((fp = fopen("/sdcard/Hello.txt", "r")) == NULL) {
        LOGE(error_desc);
        return error_string;
    }

    /*//从fp所指文件的当前指针位置读取一个字符
    int ch = fgetc(fp);
    //判断刚读取的字符是否是文件结束符
    while (ch != EOF) {
        //若不是结束符，将它输出到屏幕上显示
        LOGD("%c", ch);
        //继续从fp所指文件中读取下一个字符
        ch = fgetc(fp);
    }*/


    //一次读指定长度，读取失败或者遇到ENF返回NULL
    //如果先采用fgetc读取完毕，再通过fgets读，会直接返回NULL，因为该文件已经读到最后了
    char read_buffer[15];
    if (fgets(read_buffer, 15, fp) != NULL) {
        LOGD("%s", read_buffer);
        jstring read_result = env->NewStringUTF(read_buffer);
        fclose(fp);
        return read_result;
    }

    //关闭fp所指文件
    fclose(fp);
    return success_string;
}