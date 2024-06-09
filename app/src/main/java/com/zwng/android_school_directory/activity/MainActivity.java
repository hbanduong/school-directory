package com.zwng.android_school_directory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.zwng.android_school_directory.R;
import com.zwng.android_school_directory.fragment.DepartmentFragment;
import com.zwng.android_school_directory.fragment.EmployeeFragment;
import com.zwng.android_school_directory.model.DepartmentModel;
import com.zwng.android_school_directory.util.SearchHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        searchView = findViewById(R.id.searchView);
        frameLayout = findViewById(R.id.frameLayout);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.navItemDepartment) {
                    loadFragment(new DepartmentFragment());
                }
                else {
                    loadFragment(new EmployeeFragment());
                }

                return true;
            }
        });

        loadFragment(new DepartmentFragment());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout);
                if (currentFragment instanceof SearchHandler) {
                    ((SearchHandler) currentFragment).onSearchQuery(newText);
                }
                return true;
            }
        });

        searchView.clearFocus();
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }

    public void onClickFab(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        dialogView.findViewById(R.id.btnAddDepartment).setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), AddDepartmentActivity.class);
            startActivity(intent);
        });

        dialogView.findViewById(R.id.btnAddEmployee).setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), AddEmployeeActivity.class);
            startActivity(intent);
        });

        dialog.show();
    }
}