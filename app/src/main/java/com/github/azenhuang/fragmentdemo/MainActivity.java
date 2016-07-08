package com.github.azenhuang.fragmentdemo;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static AtomicInteger FRAGMENT_COUNTER = new AtomicInteger(0);

    private LinearLayout fragmentContainer;
    private Button add;
    private Button remove;
    private Button attach;
    private Button detach;
    private Button show;
    private Button hide;
    private Button replace;
    private TextView counterText;
    private LogTextView logTextView;
    private Button clearButton;

//    private int totalCount = 0;
//    private int detachCount = 0;
//    private int hideCount = 0;
    private static String KEY_TOTAL_TAGS = "totalFragmentTags";
    private static String KEY_DETACHED_TAGS = "detachedFragmentTags";
    private static String KEY_HIDE_TAGS = "hidenFragmentTags";
    private ArrayList<String> totalFragmentTags;
    private ArrayList<String> detachedFragmentTags;
    private ArrayList<String> hidenFragmentTags;

    private MyFragment.LogListener logListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState ==null) {
            totalFragmentTags = new ArrayList<>();
            detachedFragmentTags = new ArrayList<>();
            hidenFragmentTags = new ArrayList<>();
        } else {
            totalFragmentTags  = savedInstanceState.getStringArrayList(KEY_TOTAL_TAGS);
            detachedFragmentTags = savedInstanceState.getStringArrayList(KEY_DETACHED_TAGS);
            hidenFragmentTags = savedInstanceState.getStringArrayList(KEY_HIDE_TAGS);
        }

        setContentView(R.layout.activity_main);
        setupView();
        logListener = new LogFragment.LogListener() {
            @Override
            public void onLog(LogFragment tag, String msg) {
                logTextView.appendLog(tag.getTag(), msg);
            }
        };
//        String tag = makeFragmentTag(totalFragmentTags.size()+1);
//        addFragment(R.id.fragment_container, MyFragment.newInstance(logListener), tag);
//        totalFragmentTags.add(tag);

    }

    private void setupView() {
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);
        remove = (Button) findViewById(R.id.remove);
        remove.setOnClickListener(this);
        attach = (Button) findViewById(R.id.attach);
        attach.setOnClickListener(this);
        detach = (Button) findViewById(R.id.detach);
        detach.setOnClickListener(this);
        show = (Button) findViewById(R.id.show);
        show.setOnClickListener(this);
        hide = (Button) findViewById(R.id.hide);
        hide.setOnClickListener(this);
        replace = (Button) findViewById(R.id.replace);
        replace.setOnClickListener(this);
        counterText = (TextView) findViewById(R.id.counter);
        logTextView = (LogTextView) findViewById(R.id.log_text);
        clearButton = (Button) findViewById(R.id.clear_log);
        clearButton.setOnClickListener(this);


        remove.setClickable(true);
        replace.setClickable(true);
        detach.setClickable(true);
        hide.setClickable(true);
        show.setClickable(false);
        attach.setClickable(false);

        updateCounterText();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(KEY_TOTAL_TAGS, totalFragmentTags);
        outState.putStringArrayList(KEY_DETACHED_TAGS, detachedFragmentTags);
        outState.putStringArrayList(KEY_HIDE_TAGS, hidenFragmentTags);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.add:
                String addTag = makeFragmentTag(FRAGMENT_COUNTER.incrementAndGet());
                addFragment(R.id.fragment_container, MyFragment.newInstance(logListener), addTag);
                totalFragmentTags.add(addTag);
                remove.setClickable(true);
                break;
            case R.id.remove:
                if (checkFragmentCount()){
                    String removeTag = totalFragmentTags.remove(totalFragmentTags.size()-1-detachedFragmentTags.size()-hidenFragmentTags.size());
                    removeFragment(getFragmentByTag(removeTag));
                    if(totalFragmentTags.size()<=0){
                        remove.setClickable(false);
                        replace.setClickable(false);
                    }
                }
                break;
            case R.id.detach:
                if (checkFragmentCount()){
                    String detachTag = totalFragmentTags.get(totalFragmentTags.size()-1-detachedFragmentTags.size()-hidenFragmentTags.size());
                    detachFragment(getFragmentByTag(detachTag));
                    detachedFragmentTags.add(detachTag);
                    if(detachedFragmentTags.size() == totalFragmentTags.size()){
                        detach.setClickable(false);
                    }
                    attach.setClickable(true);
                }
                break;
            case R.id.attach:
                if (detachedFragmentTags.size()>0) {
                    String detachTag = detachedFragmentTags.remove(detachedFragmentTags.size()-1);
                    attachFragment(getFragmentByTag(detachTag));
                    if (detachedFragmentTags.size()==0){
                        attach.setClickable(false);
                    }
                    detach.setClickable(true);
                }
                break;
            case R.id.show:
                if (hidenFragmentTags.size()>0) {
                    String showTag = hidenFragmentTags.remove(hidenFragmentTags.size()-1);
                    showFragment(getFragmentByTag(showTag));
                    if (hidenFragmentTags.size() == 0) {
                        show.setClickable(false);
                    }
                    hide.setClickable(true);
                }
                break;
            case R.id.hide:
                if (checkFragmentCount()) {
                    String hideTag = totalFragmentTags.get(totalFragmentTags.size()-1-detachedFragmentTags.size()-hidenFragmentTags.size());
                    hideFragment(getFragmentByTag(hideTag));
                    hidenFragmentTags.add(hideTag);
                    if (hidenFragmentTags.size() == totalFragmentTags.size()) {
                        hide.setClickable(false);
                    }
                    show.setClickable(true);
                }
                break;

            case R.id.replace:
                if (totalFragmentTags.size()>0){
                    String replaceTag = makeFragmentTag(FRAGMENT_COUNTER.incrementAndGet());
                    replaceFragment(R.id.fragment_container, MyFragment.newInstance(logListener), replaceTag);
                    totalFragmentTags.clear();
                    totalFragmentTags.add(replaceTag);
                    hidenFragmentTags.clear();
                    detachedFragmentTags.clear();
                    remove.setClickable(true);
                    replace.setClickable(true);
                    detach.setClickable(true);
                    hide.setClickable(true);
                    show.setClickable(false);
                    attach.setClickable(false);
                }
                break;
            case R.id.clear_log:
                logTextView.clearLogs();
                break;
        }

        updateCounterText();
    }

    private boolean checkFragmentCount() {
        return totalFragmentTags.size()>detachedFragmentTags.size()+hidenFragmentTags.size();
    }

    private void updateCounterText() {
        counterText.setText(getString(R.string.counter_format, totalFragmentTags.size(), detachedFragmentTags.size(), hidenFragmentTags.size()));
    }

    private String makeFragmentTag(int fragmentNumber) {
        return makeFragmentTag(MyFragment.class, fragmentNumber);
    }

    private String makeFragmentTag(Class<? extends Fragment> clz, int fragmentNumber){
        return clz.getSimpleName()+"-"+fragmentNumber;
    }
}
