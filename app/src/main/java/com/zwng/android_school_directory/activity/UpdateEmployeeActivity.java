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
import com.zwng.android_school_directory.model.EmployeeModel;
import com.zwng.android_school_directory.util.FirebaseDatabaseHelper;

public class UpdateEmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_employee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseDatabaseHelper firebaseDatabaseHelper = new FirebaseDatabaseHelper();

        EditText edtId = findViewById(R.id.edtId);
        EditText edtName = findViewById(R.id.edtName);
        EditText edtRole = findViewById(R.id.edtRole);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        EditText edtAvatar = findViewById(R.id.edtAvatar);
        EditText edtDepartmentId = findViewById(R.id.edtDepartmentId);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        EmployeeModel employee = (EmployeeModel) intent.getParcelableExtra("employee");

        edtId.setText(employee.getId());
        edtName.setText(employee.getName());
        edtRole.setText(employee.getRole());
        edtEmail.setText(employee.getEmail());
        edtPhoneNumber.setText(employee.getPhoneNumber());
        edtAvatar.setText(employee.getAvatar());
        edtDepartmentId.setText(employee.getDepartmentId());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmployeeModel updatedEmployee = new EmployeeModel(
                        edtId.getText().toString(),
                        edtName.getText().toString(),
                        edtRole.getText().toString(),
                        edtEmail.getText().toString(),
                        edtPhoneNumber.getText().toString(),
                        edtAvatar.getText().toString(),
                        edtDepartmentId.getText().toString()
                );

                firebaseDatabaseHelper.deleteEmployee(employee);
                firebaseDatabaseHelper.updateEmployee(employee, updatedEmployee, v);
                Intent intent = new Intent(UpdateEmployeeActivity.this, MainActivity.class);
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