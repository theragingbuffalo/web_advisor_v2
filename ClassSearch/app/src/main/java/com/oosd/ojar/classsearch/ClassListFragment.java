package com.oosd.ojar.classsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ClassListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ClassAdapter mAdapter;

    public static ClassListFragment newInstance(String searchParam) {
        Bundle args = new Bundle();
        args.putString("searchParam", searchParam);

        ClassListFragment fragment = new ClassListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_list, container, false);

        mRecyclerView = (RecyclerView) view
                .findViewById(R.id.class_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        Bundle bundle = getArguments();
        ClassRoom classRoom = ClassRoom.get(getActivity());
        List<Class> classes = classRoom.getClasses(bundle.getString("searchParam"));

        mAdapter = new ClassAdapter(classes);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class ClassHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Class mClass;

        private TextView mTitleTextView;
        private TextView mProfTextView;
        private TextView mCodeTextView;
        private TextView mTimeTextView;
        private TextView mDaysTextView;

        public ClassHolder(LayoutInflater inflater, ViewGroup parent) { // called for each class in RecyclerView
            super(inflater.inflate(R.layout.list_item_class, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.class_title);
            mProfTextView = (TextView) itemView.findViewById(R.id.class_prof);
            mCodeTextView = (TextView) itemView.findViewById(R.id.class_code);
            mTimeTextView = (TextView) itemView.findViewById(R.id.class_time);
            mDaysTextView = (TextView) itemView.findViewById(R.id.class_days);
        }

        public void bind(Class course) { // set data for each course
            mClass = course;
            mTitleTextView.setText(mClass.getTitle());
            mProfTextView.setText(mClass.getProfessor());
            mCodeTextView.setText(mClass.getCode());
            mTimeTextView.setText(mClass.getStart() + " - " + mClass.getEnd());
            mDaysTextView.setText(mClass.getMeetingDays());
        }

        @Override
        public void onClick(View view) { // open detail view on click
            Intent intent = ClassActivity.newIntent(getActivity(), mClass.getId());
            getActivity().startActivity( intent );
        }
    }

    private class ClassAdapter extends RecyclerView.Adapter<ClassHolder> {

        private List<Class> mClasses;

        public ClassAdapter(List<Class> classes) {
            mClasses = classes;
        }

        @Override
        public ClassHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ClassHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ClassHolder holder, int position) {
            Class course = mClasses.get(position);
            holder.bind(course);
        }

        @Override
        public int getItemCount() {
            return mClasses.size();
        }
    }
}
