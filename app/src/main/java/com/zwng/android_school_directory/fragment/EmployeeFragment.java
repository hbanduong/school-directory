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
import com.zwng.android_school_directory.activity.DetailEmployeeActivity;
import com.zwng.android_school_directory.adapter.EmployeeAdapter;
import com.zwng.android_school_directory.model.DepartmentModel;
import com.zwng.android_school_directory.model.EmployeeModel;
import com.zwng.android_school_directory.util.FirebaseDatabaseHelper;
import com.zwng.android_school_directory.util.SearchHandler;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFragment extends Fragment implements SearchHandler {
    public EmployeeFragment() {
        // Required empty public constructor
    }

    private ListView lvEmployee;
    private List<EmployeeModel> employeeModelList;
    private EmployeeAdapter employeeAdapter;
    private FirebaseDatabaseHelper firebaseDatabaseHelper;
    private List<EmployeeModel> filteredEmployeeList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_employee, container, false);
        lvEmployee = view.findViewById(R.id.lvEmployee);


        employeeModelList = new ArrayList<>();
        filteredEmployeeList = new ArrayList<>(employeeModelList);
        employeeAdapter = new EmployeeAdapter(view.getContext(), filteredEmployeeList);
        lvEmployee.setAdapter(employeeAdapter);

        firebaseDatabaseHelper = new FirebaseDatabaseHelper();
        firebaseDatabaseHelper.loadEmployee(employeeModelList, new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void departmentIsLoaded(List<DepartmentModel> departments) {

            }

            @Override
            public void employeeIsLoaded(List<EmployeeModel> employees) {
                filteredEmployeeList.clear();
                filteredEmployeeList.addAll(employees);
                employeeAdapter.notifyDataSetChanged();
            }
        });

        lvEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), DetailEmployeeActivity.class);

                intent.putExtra("employee", filteredEmployeeList.get(position));
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onSearchQuery(String query) {
        filteredEmployeeList.clear();
        if (query.isEmpty()) {
            filteredEmployeeList.addAll(employeeModelList);
        } else {
            for (EmployeeModel employee : employeeModelList) {
                if (employee.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredEmployeeList.add(employee);
                }
            }
        }
        employeeAdapter.notifyDataSetChanged();
    }
}