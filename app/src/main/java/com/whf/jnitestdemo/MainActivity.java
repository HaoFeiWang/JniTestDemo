package com.whf.jnitestdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.jni_test_one).setOnClickListener(this);
        findViewById(R.id.jni_test_two).setOnClickListener(this);
        findViewById(R.id.jni_test_three).setOnClickListener(this);
        findViewById(R.id.jni_test_4).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jni_test_one:
                jniTestOne();
                break;
            case R.id.jni_test_two:
                jniTestTwo();
                break;
            case R.id.jni_test_three:
                jniTestThree();
                break;
            case R.id.jni_test_4:
                jniTest4();
                break;
        }
    }

    private void jniTestOne() {
        JniTest jniTest = new JniTest();
        String str = jniTest.stringFromJNI();
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    private void jniTestTwo() {
        JniTest jniTest = new JniTest();
        Class clazz = jniTest.getObjectClass(jniTest);
        Toast.makeText(this, clazz.getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    private void jniTestThree() {
        JniTest jniTest = new JniTest();
        String filedValue = jniTest.getObjectFiled(jniTest);
        Toast.makeText(this, filedValue, Toast.LENGTH_SHORT).show();
    }

    private void jniTest4() {
        JniTest jniTest = new JniTest();
        jniTest.setStaticFiled(jniTest);
        Toast.makeText(this, JniTest.tag, Toast.LENGTH_SHORT).show();
    }
}
