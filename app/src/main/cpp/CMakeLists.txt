# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# 指定需要CMAKE的最小版本
cmake_minimum_required(VERSION 3.4.1)

#set(my_lib_path ${CMAKE_SOURCE_DIR}/libs/${ANDROID_ABI})
#set(CMAKE_CXX_FLAGS ""${CMAKE_CXX_FLAGS} -L {my_lib_path} "")

file(GLOB JNI_SRC
        "${CMAKE_SOURCE_DIR}/*.h"
        "${CMAKE_SOURCE_DIR}/*.cpp"
        "${CMAKE_SOURCE_DIR}/jnitest/*.cpp"
        "${CMAKE_SOURCE_DIR}/opengl/*.cpp")
#aux_source_directory("."  JNI_SRC)

# 创建并命名库，将其设置为静态的或共享，并提供其源代码的相对路径。
# 你可以定义多个library库，并使用CMake来构建。
# Gradle会自动将包共享库关联到你的apk程序。
add_library( # 设置库的名称
        native-lib

        # 将库设置为共享库
        SHARED

        # 依赖的cpp文件，每添加一个 C/C++文件都要添加到这里，不然不会被编译
        ${JNI_SRC})


# 搜索指定预先构建的库和存储路径变量，因为CMake包括系统库搜索,
# 只需要指定想添加公共NDK库的名称，在CMake验证库之前存在完成构建
find_library( # 设置path变量的名称
        log-lib

        # 在CMake定位前指定的NDK库名称
        log)

find_library(
        opengl-lib
        libGLESv3.so
)


# 指定库CMake应该链接到目标库中，可以链接多个库，比如定义库，构建脚本，预先构建的第三方库或者系统库
target_link_libraries( # 指定目标库
        native-lib

        # 目标库到日志库的链接 包含在NDK
        ${log-lib}
        ${opengl-lib}
        )