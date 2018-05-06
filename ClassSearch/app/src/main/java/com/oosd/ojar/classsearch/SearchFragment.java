package com.oosd.ojar.classsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by James on 5/2/2018.
 */

public class SearchFragment extends Fragment {
    private Button mSearchButton;
    private SearchActivity mSearchActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        new DownloadListings((SearchActivity)getActivity()).execute("https://gustavus.edu/registrar/webadvisor/mstrfall.html");
        mSearchButton = (Button) view.findViewById(R.id.search_button);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ClassListActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
}
