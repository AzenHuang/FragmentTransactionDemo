package com.github.azenhuang.fragmentdemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by huangyongzheng on 6/16/16.
 */
public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(this, "onCreate"+">>>>savedInstanceState:"+savedInstanceState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.d(this, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d(this, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d(this, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d(this, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d(this, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d(this, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.d(this, "onSaveInstanceState"+">>>>outState:"+outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtils.d(this, "onRestoreInstanceState"+">>>>savedInstanceState:"+savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtils.d(this, "onConfigurationChanged"+">>>>>>>newConfig:"+newConfig);
    }


}
