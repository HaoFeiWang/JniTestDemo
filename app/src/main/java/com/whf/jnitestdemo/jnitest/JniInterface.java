package com.whf.jnitestdemo.jnitest;


/**
 * Created by @author WangHaoFei on 2019/1/21.
 */

public class JniInterface {

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

    /**
     * 调用指定对象的指定方法
     */
    public native void callMethod(Object obj);

    /**
     * 调用指定对象的指定方法
     */
    public native int getArrayLength(int[] args);

    /**
     * 创建一个指定对象
     */
    public native Person createPerson(Class clazz);

    /**
     * 文件读取
     */
    public native String readFile();
}
