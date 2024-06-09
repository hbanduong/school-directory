package com.zwng.android_school_directory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.zwng.android_school_directory.R;
import com.zwng.android_school_directory.model.DepartmentModel;
import com.zwng.android_school_directory.util.FirebaseDatabaseHelper;

public class UpdateDepartmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_department);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseDatabaseHelper firebaseDatabaseHelper = new FirebaseDatabaseHelper();

        EditText edtId = findViewById(R.id.edtId);
        EditText edtName = findViewById(R.id.edtName);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtWebsite = findViewById(R.id.edtWebsite);
        EditText edtLogo = findViewById(R.id.edtLogo);
        EditText edtAddress = findViewById(R.id.edtAddress);
        EditText edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        DepartmentModel department = (DepartmentModel) intent.getParcelableExtra("department");

        edtId.setText(department.getId());
        edtName.setText(department.getName());
        edtEmail.setText(department.getEmail());
        edtWebsite.setText(department.getWebsite());
        edtLogo.setText(department.getLogo());
        edtAddress.setText(department.getAddress());
        edtPhoneNumber.setText(department.getPhoneNumber());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DepartmentModel updatedDepartment = new DepartmentModel(
                        edtId.getText().toString(),
                        edtName.getText().toString(),
                        edtEmail.getText().toString(),
                        edtWebsite.getText().toString(),
                        edtLogo.getText().toString(),
                        edtAddress.getText().toString(),
                        edtPhoneNumber.getText().toString()
                );

                firebaseDatabaseHelper.deleteDepartment(department);
                firebaseDatabaseHelper.updateDepartment(department, updatedDepartment, v);
                Intent intent = new Intent(UpdateDepartmentActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}