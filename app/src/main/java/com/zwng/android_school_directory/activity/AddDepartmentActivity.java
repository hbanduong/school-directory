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

import com.google.android.material.snackbar.Snackbar;
import com.zwng.android_school_directory.R;
import com.zwng.schooldirectory.model.DepartmentModel;
import com.zwng.android_school_directory.util.FirebaseDatabaseHelper;

public class AddDepartmentActivity extends AppCompatActivity {

    private FirebaseDatabaseHelper firebaseDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_department);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        firebaseDatabaseHelper = new FirebaseDatabaseHelper();

        EditText edtId = findViewById(R.id.edtId);
        EditText edtName = findViewById(R.id.edtName);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText editWebsite = findViewById(R.id.edtWebsite);
        EditText edtLogo = findViewById(R.id.edtLogo);
        EditText edtAddress = findViewById(R.id.edtAddress);
        EditText edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DepartmentModel departmentModel = new DepartmentModel(
                        edtId.getText().toString(),
                        edtName.getText().toString(),
                        edtEmail.getText().toString(),
                        editWebsite.getText().toString(),
                        edtLogo.getText().toString(),
                        edtAddress.getText().toString(),
                        edtPhoneNumber.getText().toString()
                );
                firebaseDatabaseHelper.addDepartment(departmentModel, v);
            }
        });
    }
}