package com.oosd.ojar.classsearch;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;

import java.util.UUID;


public class ClassActivity extends SingleFragmentActivity {
    private static final String EXTRA_CLASS_ID =
            "com.oosd.ojar.classID";

    public static Intent newIntent(Context packageContext, UUID id) {
        Intent intent = new Intent(packageContext, ClassActivity.class);
        intent.putExtra(EXTRA_CLASS_ID, id);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID id = (UUID) getIntent().getSerializableExtra(EXTRA_CLASS_ID);
        return ClassFragment.newInstance(id);
    }
}
