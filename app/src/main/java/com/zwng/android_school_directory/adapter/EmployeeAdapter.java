package com.zwng.android_school_directory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.zwng.android_school_directory.R;
import com.zwng.android_school_directory.model.EmployeeModel;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<EmployeeModel> {
    private Context context;
    private List<EmployeeModel> employeeModelList;

    public EmployeeAdapter(@NonNull Context context, List<EmployeeModel> employeeModelList) {
        super(context, R.layout.list_item_employee, employeeModelList);
        this.context = context;
        this.employeeModelList = employeeModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_employee, parent, false);
        }

        EmployeeModel employeeModel = employeeModelList.get(position);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvPhoneNumber = convertView.findViewById(R.id.tvPhoneNumber);
        ImageView imvAvatar = convertView.findViewById(R.id.imvAvatar);

        tvName.setText(employeeModel.getName());
        tvPhoneNumber.setText(employeeModel.getPhoneNumber());

        Glide.with(convertView)
                .load(employeeModel.getAvatar())
                .placeholder(R.drawable.ic_school)
                .override(200, 200)
                .into(imvAvatar);

        return convertView;
    }
}