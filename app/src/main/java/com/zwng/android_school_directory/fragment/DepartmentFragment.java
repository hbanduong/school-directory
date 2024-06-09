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
import com.zwng.android_school_directory.model.EmployeeModel;
import com.zwng.android_school_directory.util.FirebaseDatabaseHelper;
import com.zwng.android_school_directory.util.SearchHandler;

import java.util.ArrayList;
import java.util.List;

public class DepartmentFragment extends Fragment implements SearchHandler {
    public DepartmentFragment() {
        // Required empty public constructor
    }

    private ListView lvDepartment;
    private List<DepartmentModel> departmentModelList;
    private DepartmentAdapter departmentAdapter;
    private FirebaseDatabaseHelper firebaseDatabaseHelper;
    private List<DepartmentModel> filteredDepartmentList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_department, container, false);
        lvDepartment = view.findViewById(R.id.lvDepartment);

        departmentModelList = new ArrayList<>();
        filteredDepartmentList = new ArrayList<>(departmentModelList);
        departmentAdapter = new DepartmentAdapter(view.getContext(), filteredDepartmentList);
        lvDepartment.setAdapter(departmentAdapter);

        firebaseDatabaseHelper = new FirebaseDatabaseHelper();

        firebaseDatabaseHelper.loadDepartment(departmentModelList, new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void departmentIsLoaded(List<DepartmentModel> departments) {
                filteredDepartmentList.clear();
                filteredDepartmentList.addAll(departments);
                departmentAdapter.notifyDataSetChanged();
            }

            @Override
            public void employeeIsLoaded(List<EmployeeModel> employees) {

            }
        });

        lvDepartment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), DetailDepartmentActivity.class);

                intent.putExtra("department", filteredDepartmentList.get(position));
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onSearchQuery (String query) {
        filteredDepartmentList.clear();
        if (query.isEmpty()) {
            filteredDepartmentList.addAll(departmentModelList);
        } else {
            for (DepartmentModel department : departmentModelList) {
                if (department.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredDepartmentList.add(department);
                }
            }
        }
        departmentAdapter.notifyDataSetChanged();
    }
}