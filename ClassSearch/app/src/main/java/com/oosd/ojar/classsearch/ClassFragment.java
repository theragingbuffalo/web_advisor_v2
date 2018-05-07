package com.oosd.ojar.classsearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.ref.SoftReference;


public class ClassFragment extends Fragment {

    private static final String ARG_CLASS_CODE = "class_code";
    private static final String ARG_CLASS_SYNONYM = "class_synonym";
    private static final String ARG_CLASS_TITLE = "class_title";
    private static final String ARG_CLASS_MEETINGDAYS = "class_meetingdays";
    private static final String ARG_CLASS_START = "class_start";
    private static final String ARG_CLASS_END = "class_end";
    private static final String ARG_CLASS_PROFESSOR = "class_professor";
    private static final String ARG_CLASS_APPROVALS = "class_approvals";
    private TextView mTitleField;
    private TextView mCodeField;
    private TextView mTimeField;
    private TextView mProfField;
    private TextView mApprovalsField;

    public static ClassFragment newInstance(String code, int synonym, String title, String meetingDays,
                                            String start, String end, String professor, String approvals) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CLASS_CODE, code);
        args.putSerializable(ARG_CLASS_SYNONYM, synonym );
        args.putSerializable(ARG_CLASS_TITLE, title);
        args.putSerializable(ARG_CLASS_MEETINGDAYS, meetingDays);
        args.putSerializable(ARG_CLASS_START, start);
        args.putSerializable(ARG_CLASS_END, end);
        args.putSerializable(ARG_CLASS_PROFESSOR, professor);
        args.putSerializable(ARG_CLASS_APPROVALS, approvals);

        ClassFragment fragment = new ClassFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_class, container, false);
        Bundle bundle = getArguments();
        mTitleField = (TextView) v.findViewById(R.id.classTitle);
        mTitleField.setText(bundle.getString(ARG_CLASS_TITLE));
        mCodeField = (TextView) v.findViewById(R.id.classCode);
        mCodeField.setText(bundle.getString(ARG_CLASS_CODE ));
        mTimeField = (TextView) v.findViewById(R.id.classTimes);
        mTimeField.setText(bundle.getString(ARG_CLASS_MEETINGDAYS) + " " + bundle.getString(ARG_CLASS_START) + " - " + bundle.getString(ARG_CLASS_END));
        mApprovalsField= (TextView) v.findViewById(R.id.classApprovals);
        mApprovalsField.setText(bundle.getString(ARG_CLASS_APPROVALS));
        mProfField = (TextView) v.findViewById(R.id.classProf);
        mProfField.setText(bundle.getString(ARG_CLASS_PROFESSOR));

        return v;
    }
}
