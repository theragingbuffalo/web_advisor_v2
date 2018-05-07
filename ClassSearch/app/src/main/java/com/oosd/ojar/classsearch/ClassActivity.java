package com.oosd.ojar.classsearch;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;



public class ClassActivity extends SingleFragmentActivity {
    private static final String EXTRA_CLASS_CODE =
            "com.oosd.ojar.classCode";
    private static final String EXTRA_CLASS_SYNONYM =
            "com.oosd.ojar.classSynonym";
    private static final String EXTRA_CLASS_TITLE =
            "com.oosd.ojar.classTitle";
    private static final String EXTRA_CLASS_MEETINGDAYS =
            "com.oosd.ojar.classMeetingDays";
    private static final String EXTRA_CLASS_START =
            "com.oosd.ojar.classStart";
    private static final String EXTRA_CLASS_END =
            "com.oosd.ojar.classEnd";
    private static final String EXTRA_CLASS_PROFESSOR =
            "com.oosd.ojar.classProfessor";
    private static final String EXTRA_CLASS_APPROVALS =
            "com.oosd.ojar.classApprovals";

    public static Intent newIntent(Context packageContext, String code, int synonym, String title, String meetingDays,
                                   String start, String end, String professor, String approvals) {
        Intent intent = new Intent(packageContext, ClassActivity.class);
        intent.putExtra(EXTRA_CLASS_CODE, code);
        intent.putExtra(EXTRA_CLASS_SYNONYM, synonym);
        intent.putExtra(EXTRA_CLASS_TITLE, title);
        intent.putExtra(EXTRA_CLASS_MEETINGDAYS, meetingDays);
        intent.putExtra(EXTRA_CLASS_START, start);
        intent.putExtra(EXTRA_CLASS_END, end);
        intent.putExtra(EXTRA_CLASS_PROFESSOR, professor);
        intent.putExtra(EXTRA_CLASS_APPROVALS, approvals);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String code = (String) getIntent().getSerializableExtra(EXTRA_CLASS_CODE);
        int synonym = (int) getIntent().getSerializableExtra(EXTRA_CLASS_SYNONYM);
        String title = (String) getIntent().getSerializableExtra(EXTRA_CLASS_TITLE);
        String meetingDays = (String) getIntent().getSerializableExtra(EXTRA_CLASS_MEETINGDAYS);
        String start = (String) getIntent().getSerializableExtra(EXTRA_CLASS_START);
        String end = (String) getIntent().getSerializableExtra(EXTRA_CLASS_END);
        String professor = (String) getIntent().getSerializableExtra(EXTRA_CLASS_PROFESSOR);
        String approvals = (String) getIntent().getSerializableExtra(EXTRA_CLASS_APPROVALS);
        return ClassFragment.newInstance(code, synonym, title, meetingDays, start, end, professor, approvals);
    }
}
