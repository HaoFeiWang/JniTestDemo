package com.whf.jnitestdemo.jnitest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by @author WangHaoFei on 2019/1/22.
 */

public class Person {

    public String name = "Hello C++";
    public static String tag = "Hello Java";
    private Context mContext;

    public Person(String arg1,String arg2){
        this.name = arg1;
        Log.d("Jni_Test","person print arg2 = "+arg2);
    }

    public Person(Context context){
        this.mContext = context;
    }

    private void toast(){
        Toast.makeText(mContext,"Call Method!",Toast.LENGTH_SHORT).show();
    }

    private static void printLog(){
        Log.d("Jni_Test","person print log!");
    }
}
