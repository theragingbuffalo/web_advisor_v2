package com.oosd.ojar.classsearch;

import android.content.Intent;
import android.support.v4.app.Fragment;

public class ClassListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return ClassListFragment.newInstance(getIntent().getStringExtra("searchParam"));
    }
}
