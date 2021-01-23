package com.whf.jnitestdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        findViewById(R.id.jni_test_5).setOnClickListener(this);
        findViewById(R.id.jni_test_6).setOnClickListener(this);
        findViewById(R.id.jni_test_7).setOnClickListener(this);
        findViewById(R.id.jni_test_8).setOnClickListener(this);
        findViewById(R.id.jni_test_9).setOnClickListener(this);
        findViewById(R.id.jni_test_10).setOnClickListener(this);

        requestPermission();
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 60);
        }
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
            case R.id.jni_test_5:
                jniTest5();
                break;
            case R.id.jni_test_6:
                jniTest6();
                break;
            case R.id.jni_test_7:
                jniTest7();
                break;
            case R.id.jni_test_8:
                jniTest8();
                break;
            case R.id.jni_test_9:
                jniTest9();
                break;
            case R.id.jni_test_10:
                startActivity(new Intent(this, OpenGlActivity.class));
                break;
        }
    }

    private void jniTest9() {
        JniInterface jniInterface = new JniInterface();
        String value = jniInterface.readFile();
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }

    private void jniTest8() {
        JniInterface jniInterface = new JniInterface();
        String value = jniInterface.dynamicRegister();
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
    }

    private void jniTest7() {
        JniInterface jniInterface = new JniInterface();
        Person person = jniInterface.createPerson(Person.class);
        Toast.makeText(this, person.name, Toast.LENGTH_SHORT).show();
    }

    private void jniTest6() {
        JniInterface jniInterface = new JniInterface();
        int[] args = {1, 2, 3, 4, 5};
        int length = jniInterface.getArrayLength(args);
        Toast.makeText(this, "Length = " + length, Toast.LENGTH_SHORT).show();
    }

    private void jniTest5() {
        Person person = new Person(this);
        JniInterface jniInterface = new JniInterface();
        jniInterface.callMethod(person);
    }

    private void jniTest4() {
        Person person = new Person(this);
        JniInterface jniInterface = new JniInterface();
        jniInterface.setStaticFiled(person);
        Toast.makeText(this, Person.tag, Toast.LENGTH_SHORT).show();
    }

    private void jniTestThree() {
        Person person = new Person(this);
        JniInterface jniInterface = new JniInterface();
        String filedValue = jniInterface.getObjectFiled(person);
        Toast.makeText(this, filedValue, Toast.LENGTH_SHORT).show();
    }

    private void jniTestTwo() {
        Person person = new Person(this);
        JniInterface jniInterface = new JniInterface();
        Class clazz = jniInterface.getObjectClass(person);
        Toast.makeText(this, clazz.getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    private void jniTestOne() {
        JniInterface jniInterface = new JniInterface();
        String str = jniInterface.stringFromJNI();
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
