package com.whf.jnitestdemo;

/**
 * Created by @author WangHaoFei on 2019/1/21.
 */

public class JniTest {

    static {
        System.loadLibrary("native-lib");
    }

    private String name = "Hello C++";
    public static String tag = "Hello Java";

    public native String stringFromJNI();

    public native String stringFromJNI(String src);

    /**
     * 获取指定对象的Class
     */
    public native Class getObjectClass(Object obj);


    /**
     * 获取指定对象的属性
     */
    public native String getObjectFiled(Object obj);


    /**
     * 修改指定类的静态属性
     */
    public native void setStaticFiled(Object obj);
}
