package com.zwng.android_school_directory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zwng.android_school_directory.R;
import com.zwng.android_school_directory.model.DepartmentModel;

public class DepartmentDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_department_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvId = findViewById(R.id.tvId);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvWebsite = findViewById(R.id.tvWebsite);
        TextView tvLogo = findViewById(R.id.tvLogo);
        TextView tvAddress = findViewById(R.id.tvAddress);
        TextView tvPhoneNumber = findViewById(R.id.tvPhoneNumber);

        ImageView imvLogo = findViewById(R.id.imvLogo);

        Intent intent = getIntent();
        DepartmentModel department = (DepartmentModel) intent.getParcelableExtra("department");

        if (department != null) {
            tvId.setText(department.getId());
            tvName.setText(department.getName());
            tvEmail.setText(department.getEmail());
            tvWebsite.setText(department.getWebsite());
            tvLogo.setText(department.getLogo());
            tvAddress.setText(department.getAddress());
            tvPhoneNumber.setText(department.getPhoneNumber());

            Glide.with(this)
                    .load(department.getLogo())
                    .placeholder(R.drawable.ic_school)
                    .override(200, 200)
                    .into(imvLogo);
        }
    }
}