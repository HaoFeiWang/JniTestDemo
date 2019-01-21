package com.whf.jnitestdemo;

/**
 * Created by @author WangHaoFei on 2019/1/21.
 */

public class JniTest {

//    // Used to load the 'native-lib' library on application startup.
//    static {
//        System.loadLibrary("native-lib");
//    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String stringFromJNI(String src);

    public native int intFrom_JNI();
}
