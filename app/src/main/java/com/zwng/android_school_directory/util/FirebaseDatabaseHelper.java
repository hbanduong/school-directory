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
import com.zwng.android_school_directory.model.DepartmentModel;

import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dRefDepartment;

    public FirebaseDatabaseHelper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        dRefDepartment = firebaseDatabase.getReference("department");
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
                            Snackbar.make(view, "Thêm thành công", Snackbar.LENGTH_SHORT).show();
                        }
                        else {
                            Snackbar.make(view, "Thêm thất bại", Snackbar.LENGTH_SHORT).show();
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

    public void deleteDepartment(String id, View view) {
        dRefDepartment.child(id).removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Snackbar.make(view, "Xóa thành công", Snackbar.LENGTH_SHORT).show();
            }
            else {
                Snackbar.make(view, "Xóa thất bại", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
