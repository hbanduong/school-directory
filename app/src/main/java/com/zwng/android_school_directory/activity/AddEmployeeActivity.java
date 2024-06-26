package com.zwng.android_school_directory.activity;

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
import com.zwng.android_school_directory.model.EmployeeModel;
import com.zwng.android_school_directory.util.FirebaseDatabaseHelper;

public class AddEmployeeActivity extends AppCompatActivity {

    private FirebaseDatabaseHelper firebaseDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_employee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        firebaseDatabaseHelper = new FirebaseDatabaseHelper();

        EditText edtId = findViewById(R.id.edtId);
        EditText edtName = findViewById(R.id.edtName);
        EditText edtRole = findViewById(R.id.edtRole);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        EditText edtAvatar = findViewById(R.id.edtAvatar);
        EditText edtDepartmentId = findViewById(R.id.edtDepartmentId);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmployeeModel employeeModel = new EmployeeModel(
                        edtId.getText().toString(),
                        edtName.getText().toString(),
                        edtRole.getText().toString(),
                        edtEmail.getText().toString(),
                        edtPhoneNumber.getText().toString(),
                        edtAvatar.getText().toString(),
                        edtDepartmentId.getText().toString()
                );
                firebaseDatabaseHelper.addEmployee(employeeModel, v);
            }
        });
    }
}