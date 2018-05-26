package com.oosd.ojar.classsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by James on 5/2/2018.
 */

public class SearchFragment extends Fragment {
    private Button mSearchButton;
    private Button mSeeSavedButton;
    private Spinner mAreaSpinner;
    private Spinner mDepartmentSpinner;
    private EditText mTitleKeywordInput;
    private SearchActivity mSearchActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        new DownloadListings((SearchActivity)getActivity()).execute("https://gustavus.edu/registrar/webadvisor/mstrfall.html"); // starting listings/descriptions download

        mAreaSpinner = (Spinner)view.findViewById(R.id.area_spinner);
        mDepartmentSpinner = (Spinner)view.findViewById(R.id.department_spinner);
        mTitleKeywordInput = (EditText)view.findViewById(R.id.title_keyword);
        mSearchButton = (Button)view.findViewById(R.id.search_button);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // get search parameter from input fields, initiate search and open detail view
                Intent intent = new Intent(getActivity(), ClassListActivity.class);
                String param = "";
                String areaApproval = mAreaSpinner.getSelectedItem().toString();
                if (!areaApproval.equals("Select Area Approval"))
                    param += "AREA:" + areaApproval + "&&";
                String department = mDepartmentSpinner.getSelectedItem().toString();
                if (!department.equals("Select Department"))
                    param += "DEP:" + department + "&&";
                String title_keyword = mTitleKeywordInput.getText().toString();
                if (!title_keyword.isEmpty())
                    param += "TK:" + title_keyword.toLowerCase() + "&&";
                if (!param.isEmpty()) param = param.substring(0, param.length() - 2);
                intent.putExtra("searchParam", param);
                getActivity().startActivity(intent);
            }
        });
        mSeeSavedButton = (Button)view.findViewById(R.id.toSaved_button);
        mSeeSavedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ClassListActivity.class);
                String param = "ISSAVED" + "&&";
                intent.putExtra("searchParam", param);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}
