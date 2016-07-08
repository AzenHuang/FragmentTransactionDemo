package com.github.azenhuang.fragmentdemo;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by huangyongzheng on 6/16/16.
 */
public class BaseActivity extends LogActivity {
    protected int addFragment( @IdRes int containerViewId, Fragment fragment) {
        return addFragment(containerViewId, fragment, null);
    }

    protected int addFragment( @IdRes int containerViewId,
                               Fragment fragment, @Nullable String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager!=null) {
            return fragmentManager.beginTransaction().add(containerViewId, fragment, tag).commit();
        }
        return -1;
    }


    protected int removeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager!=null) {
            return fragmentManager.beginTransaction().remove(fragment).commit();
        }
        return -1;
    }

    protected int attachFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager!=null) {
            return fragmentManager.beginTransaction().attach(fragment).commit();
        }
        return -1;
    }

    protected int detachFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager!=null) {
            return fragmentManager.beginTransaction().detach(fragment).commit();
        }
        return -1;
    }

    protected int showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager!=null) {
            return fragmentManager.beginTransaction().show(fragment).commit();
        }
        return -1;
    }

    protected int hideFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager!=null) {
            return fragmentManager.beginTransaction().hide(fragment).commit();
        }
        return -1;
    }

    protected int replaceFragment(@IdRes int containerViewId, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager!=null) {
            return fragmentManager.beginTransaction().replace(containerViewId, fragment, null).commit();
        }
        return -1;
    }

    protected int replaceFragment(@IdRes int containerViewId,
                               Fragment fragment, @Nullable String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager!=null) {
            return fragmentManager.beginTransaction().replace(containerViewId, fragment, tag).commit();
        }
        return -1;
    }



    protected Fragment getFragmentByTag(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager!=null) {
           return fragmentManager.findFragmentByTag(tag);
        }
        return null;
    }

    protected Fragment getFragmentById(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager!=null) {
            return fragmentManager.findFragmentById(id);
        }
        return null;
    }
}
