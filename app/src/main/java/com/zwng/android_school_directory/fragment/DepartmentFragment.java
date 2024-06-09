package com.zwng.android_school_directory.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zwng.android_school_directory.R;
import com.zwng.android_school_directory.activity.DetailDepartmentActivity;
import com.zwng.android_school_directory.adapter.DepartmentAdapter;
import com.zwng.android_school_directory.model.DepartmentModel;
import com.zwng.android_school_directory.util.FirebaseDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class DepartmentFragment extends Fragment {
    public DepartmentFragment() {
        // Required empty public constructor
    }

    private ListView lvDepartment;
    private List<DepartmentModel> departmentModelList;
    private DepartmentAdapter departmentAdapter;
    private FirebaseDatabaseHelper firebaseDatabaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_department, container, false);
        lvDepartment = view.findViewById(R.id.lvDepartment);

        departmentModelList = new ArrayList<>();
        departmentAdapter = new DepartmentAdapter(view.getContext(), departmentModelList);
        lvDepartment.setAdapter(departmentAdapter);

        firebaseDatabaseHelper = new FirebaseDatabaseHelper();

        firebaseDatabaseHelper.loadDepartment(departmentModelList, departmentAdapter);

        lvDepartment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), DetailDepartmentActivity.class);

                intent.putExtra("department", departmentModelList.get(position));
                startActivity(intent);
            }
        });

        return view;
    }
}