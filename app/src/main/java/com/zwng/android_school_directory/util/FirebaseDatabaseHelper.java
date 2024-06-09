package com.zwng.android_school_directory.util;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zwng.android_school_directory.adapter.DepartmentAdapter;
import com.zwng.android_school_directory.adapter.EmployeeAdapter;
import com.zwng.android_school_directory.model.DepartmentModel;
import com.zwng.android_school_directory.model.EmployeeModel;

import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dRefDepartment;
    private DatabaseReference dRefEmployee;

    public FirebaseDatabaseHelper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        dRefDepartment = firebaseDatabase.getReference("department");
        dRefEmployee = firebaseDatabase.getReference("employee");
    }

    public void loadDepartment(List<DepartmentModel> departmentModelList, DepartmentAdapter departmentAdapter) {
        dRefDepartment.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                departmentModelList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DepartmentModel departmentModel = dataSnapshot.getValue(DepartmentModel.class);
                    departmentModelList.add(departmentModel);
                }
                departmentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addDepartment(DepartmentModel departmentModel, View view) {
        dRefDepartment.child(departmentModel.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    dRefDepartment.child(departmentModel.getId()).setValue(departmentModel).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Snackbar.make(view, "Lưu thành công", Snackbar.LENGTH_SHORT).show();
                        }
                        else {
                            Snackbar.make(view, "Lưu thất bại", Snackbar.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Snackbar.make(view, "Mã đơn vị đã tồn tại", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void deleteDepartment(DepartmentModel department) {
        dRefDepartment.child(department.getId()).removeValue();
    }

    public void updateDepartment(DepartmentModel department, DepartmentModel updatedDepartment, View view) {
        deleteDepartment(department);
        addDepartment(updatedDepartment, view);
    }

    public void loadEmployee(List<EmployeeModel> employeeModelList, EmployeeAdapter employeeAdapter) {
        dRefEmployee.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                employeeModelList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    EmployeeModel employeeModel = dataSnapshot.getValue(EmployeeModel.class);
                    employeeModelList.add(employeeModel);
                }
                employeeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addEmployee(EmployeeModel employeeModel, View view) {
        dRefEmployee.child(employeeModel.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    dRefEmployee.child(employeeModel.getId()).setValue(employeeModel).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Snackbar.make(view, "Lưu thành công", Snackbar.LENGTH_SHORT).show();
                        }
                        else {
                            Snackbar.make(view, "Lưu thất bại", Snackbar.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    };

    public void deleteEmployee(EmployeeModel employee) {
        dRefEmployee.child(employee.getId()).removeValue();
    }

    public void updateEmployee(EmployeeModel employee, EmployeeModel updatedEmployee, View view) {
        deleteEmployee(employee);
        addEmployee(updatedEmployee, view);
    }
}
