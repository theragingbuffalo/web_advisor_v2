package com.oosd.ojar.classsearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.util.UUID;


public class ClassFragment extends Fragment {

    private static final String ARG_CLASS_ID = "class_id";
    private TextView mTitleField;
    private TextView mCodeField;
    private TextView mTimeField;
    private TextView mProfField;
    private TextView mApprovalsField;
    private TextView mDescriptionField;
    private Class mClass;
    private TextView mSaved;
    private Button mSaveButton;

    public static ClassFragment newInstance(UUID id) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CLASS_ID, id);

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
        mClass = ClassRoom.get(getActivity()).getClass((UUID)bundle.getSerializable(ARG_CLASS_ID));
        mTitleField = (TextView) v.findViewById(R.id.classTitle);
        mTitleField.setText(mClass.getTitle());
        mCodeField = (TextView) v.findViewById(R.id.classCode);
        mCodeField.setText(mClass.getCode());
        mTimeField = (TextView) v.findViewById(R.id.classTimes);
        mTimeField.setText(mClass.getMeetingDays() + " " + mClass.getStart() + " - " + mClass.getEnd());
        mApprovalsField= (TextView) v.findViewById(R.id.classApprovals);
        mApprovalsField.setText(mClass.getApprovals());
        mProfField = (TextView) v.findViewById(R.id.classProf);
        mProfField.setText(mClass.getProfessor());
        mSaved = (TextView) v.findViewById(R.id.saveBoolean);
        mSaved.setText(String.valueOf(mClass.isSaved()));
        mDescriptionField = (TextView) v.findViewById(R.id.description);
        mDescriptionField.setText(String.valueOf(mClass.getDescription()));
        mDescriptionField.setMovementMethod(new ScrollingMovementMethod());
        mSaveButton = (Button) v.findViewById(R.id.saveButton);
        if (mClass.isSaved()) mSaveButton.setText("Unsave Class");
        else mSaveButton.setText("Save Class");
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mClass.toggleSave();
                mSaved.setText(String.valueOf(mClass.isSaved()));
                if (mClass.isSaved()) mSaveButton.setText("Unsave Class");
                else mSaveButton.setText("Save Class");
            }
        });

        return v;
    }
}
