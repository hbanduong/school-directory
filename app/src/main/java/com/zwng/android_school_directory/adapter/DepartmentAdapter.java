package com.zwng.android_school_directory.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zwng.android_school_directory.R;
import com.zwng.schooldirectory.model.DepartmentModel;

import java.util.List;

public class DepartmentAdapter extends ArrayAdapter<DepartmentModel> {
    private Context context;
    private List<DepartmentModel> departmentModelList;

    public DepartmentAdapter(@NonNull Context context, List<DepartmentModel> departmentModelList) {
        super(context, R.layout.list_item_department, departmentModelList);
        this.context = context;
        this.departmentModelList = departmentModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_department, parent, false);
        }

        DepartmentModel departmentModel = departmentModelList.get(position);
        TextView tvName = convertView.findViewById(R.id.tvName);
        return convertView;
    }
}