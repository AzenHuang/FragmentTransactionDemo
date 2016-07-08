package com.github.azenhuang.fragmentdemo;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by huangyongzheng on 6/16/16.
 */
public class LogFragment extends Fragment{

    private LogListener mLogListener;
    public interface LogListener{
        void onLog(LogFragment tag, String msg);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        d(this, "onAttach"+">>>>context:"+context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        d(this, "onCreate"+">>>>savedInstanceState:"+savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        d(this, "onCreateView"+">>>>savedInstanceState:"+savedInstanceState);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        d(this, "onViewCreated"+">>>>savedInstanceState:"+savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        d(this, "onActivityCreated"+">>>>savedInstanceState:"+savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        d(this, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        d(this, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        d(this, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        d(this, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        d(this, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        d(this, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        d(this, "onDetach");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        d(this, "onSaveInstanceState"+">>>>outState:"+outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        d(this, "onConfigurationChanged"+">>>>>>>newConfig:"+newConfig);
    }
    
    protected void d (LogFragment tag, String msg){
        LogUtils.d(tag, msg);
        if (mLogListener!=null) {
            String logMsg = msg;
            int index = msg.indexOf(">>>");
            if (index>0){
                logMsg = msg.substring(0, index);
            }
            mLogListener.onLog(tag, logMsg);
        }
    }

    public LogListener getmLogListener() {
        return mLogListener;
    }

    public void setmLogListener(LogListener mLogListener) {
        this.mLogListener = mLogListener;
    }
}
