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
import com.zwng.android_school_directory.model.EmployeeModel;
import com.zwng.android_school_directory.util.FirebaseDatabaseHelper;

public class DetailEmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_employee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseDatabaseHelper firebaseDatabaseHelper= new FirebaseDatabaseHelper();

        TextView tvId = findViewById(R.id.tvId);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvRole = findViewById(R.id.tvRole);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        TextView tvAvatar = findViewById(R.id.tvAvatar);
        TextView tvDepartmentId = findViewById(R.id.tvDepartmentId);

        ImageView imvAvatar = findViewById(R.id.imvAvatar);

        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnEdit = findViewById(R.id.btnEdit);

        Intent intent = getIntent();
        EmployeeModel employee = (EmployeeModel) intent.getParcelableExtra("employee");

        tvId.setText(employee.getId());
        tvName.setText(employee.getName());
        tvRole.setText(employee.getRole());
        tvEmail.setText(employee.getEmail());
        tvPhoneNumber.setText(employee.getPhoneNumber());
        tvAvatar.setText(employee.getAvatar());
        tvDepartmentId.setText(employee.getDepartmentId());

        Glide.with(this)
                .load(employee.getAvatar())
                .placeholder(R.drawable.ic_person)
                .override(200, 200)
                .into(imvAvatar);

        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailEmployeeActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_delete, null);
                builder.setView(dialogView);

                AlertDialog dialog = builder.create();

                dialogView.findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firebaseDatabaseHelper.deleteEmployee(employee);
                        Intent intentBack = new Intent(DetailEmployeeActivity.this, MainActivity.class);
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
                Intent intentEdit = new Intent(DetailEmployeeActivity.this, UpdateEmployeeActivity.class);

                intentEdit.putExtra("employee", employee);
                startActivity(intentEdit);
            }
        });
    }
}