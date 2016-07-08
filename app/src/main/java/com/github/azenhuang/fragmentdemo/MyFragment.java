package com.github.azenhuang.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by huangyongzheng on 6/16/16.
 */
public class MyFragment extends LogFragment {

    public MyFragment(){
    }
    public static MyFragment newInstance (LogListener logListener){
        MyFragment myFragment = new MyFragment();
        myFragment.setmLogListener(logListener);
        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        TextView text = new TextView(getContext());
        text.setText(getTag());
        return text;
    }


}
