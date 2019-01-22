//
// Created by root on 2019/1/22.
// 日志工具
//

#include "android/log.h"

#ifndef LOG_TAG

//Log 的 tag 名字
#define LOG_TAG "JNI_LOG"

//定义各种类型 Log 的函数别名（省略号表示形参个数和类型不确定的函数）
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG ,__VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG ,__VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,LOG_TAG ,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,LOG_TAG ,__VA_ARGS__)
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,LOG_TAG ,__VA_ARGS__)

#endif


