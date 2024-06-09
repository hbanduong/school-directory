package com.zwng.android_school_directory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.zwng.android_school_directory.R;
import com.zwng.android_school_directory.model.DepartmentModel;
import com.zwng.android_school_directory.util.FirebaseDatabaseHelper;

public class DetailDepartmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_department);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseDatabaseHelper firebaseDatabaseHelper= new FirebaseDatabaseHelper();

        TextView tvId = findViewById(R.id.tvId);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvWebsite = findViewById(R.id.tvWebsite);
        TextView tvLogo = findViewById(R.id.tvLogo);
        TextView tvAddress = findViewById(R.id.tvAddress);
        TextView tvPhoneNumber = findViewById(R.id.tvPhoneNumber);

        ImageView imvLogo = findViewById(R.id.imvLogo);

        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnEdit = findViewById(R.id.btnEdit);

        Intent intent = getIntent();
        DepartmentModel department = (DepartmentModel) intent.getParcelableExtra("department");

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

        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailDepartmentActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_delete, null);
                builder.setView(dialogView);

                AlertDialog dialog = builder.create();

                dialogView.findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firebaseDatabaseHelper.deleteDepartment(department);
                        Intent intentBack = new Intent(DetailDepartmentActivity.this, MainActivity.class);
                        startActivity(intentBack);
                    }
                });

                dialogView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEdit = new Intent(DetailDepartmentActivity.this, UpdateDepartmentActivity.class);

                intentEdit.putExtra("department", department);
                startActivity(intentEdit);
            }
        });
    }
}