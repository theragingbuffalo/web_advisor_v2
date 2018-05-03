package com.oosd.ojar.classsearch;

import android.support.v4.app.Fragment;

public class ClassActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ClassFragment();
    }
}
