package com.example.june18.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.june18.database.DatabaseHelper;
import com.example.june18.fragments.AccountFragment;
import com.example.june18.fragments.AddExpenseFragment;
import com.example.june18.fragments.AnalyticsFragment;
import com.example.june18.fragments.HomeFragment;
import com.example.june18.fragments.MoreFragment;
import com.example.june18.utils.MyPref;
import com.example.june18.R;
import com.example.june18.fragments.OptionsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private MyPref myPref;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();

        if (myPref.getEmail().isEmpty() && myPref.getPassword().isEmpty()) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }
//        db.onCreate(db.getWritableDatabase());
        loadFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home_option) {
                    loadFragment(new HomeFragment());
                }
                if (item.getItemId() == R.id.analytics_option) {
                    loadFragment(new AnalyticsFragment());
                }
                if (item.getItemId() == R.id.options_option) {
                    loadFragment(new OptionsFragment());
                }
                if (item.getItemId() == R.id.account_option) {
                    loadFragment(new AccountFragment());
                }
                if (item.getItemId() == R.id.more_option) {
                    loadFragment(new MoreFragment());
                }

                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, fragment);
        fragmentTransaction.commit();
    }

    private void initComponent() {
        myPref = new MyPref(this);
        bottomNavigationView = findViewById(R.id.navigation_layout);
    }
}