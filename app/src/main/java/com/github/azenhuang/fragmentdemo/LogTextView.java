package com.github.azenhuang.fragmentdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by huangyongzheng on 6/16/16.
 */
public class LogTextView extends TextView {
    private ArrayList<String> mTags = new ArrayList<>();
    private ArrayList<String> mMessages = new ArrayList<>();
    private int mMaxTagLength = 0;
    private static int EXTRA_SPACE_COUNT = 2;


    public LogTextView(Context context) {
        super(context);
    }

    public LogTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LogTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LogTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void appendLog(String tag, String message) {
        mTags.add(tag);
        mMessages.add(message);
        int tagLength = tag.length();
        if (tagLength>mMaxTagLength) {
            mMaxTagLength = tagLength;
            reset();
        } else {
            append(formatTagMessage(tag, message));
        }
    }

    public void clearLogs(){
        mTags.clear();
        mMessages.clear();
        mMaxTagLength = 0;
        setText("");
    }

    public void reset() {
        StringBuilder sb = new StringBuilder();
        int size = mTags.size();
        for(int i = 0; i<size; i++) {
            sb.append(formatTagMessage(mTags.get(i), mMessages.get(i)));
        }
        setText(sb.toString());
    }

    private String formatTagMessage(String tag, String message) {
        int tagWidth = mMaxTagLength + EXTRA_SPACE_COUNT;
        return String.format("%-"+tagWidth+"s %s\n", tag, message);
    }
}
