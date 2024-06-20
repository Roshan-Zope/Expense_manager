package com.example.june18.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.june18.database.DatabaseManager;
import com.example.june18.utils.MyPref;
import com.example.june18.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout tlName;
    private TextInputLayout tlEmail;
    private TextInputLayout tlPassword;
    private TextInputLayout tlConfirmPassword;
    private TextInputEditText etName;
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private TextInputEditText etConfirmPassword;
    private MaterialButton registerButton;
    private MaterialTextView loginLink;
    private MyPref myPref;
    public static DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponent();

        registerButton.setOnClickListener(event -> {
            if (validateData()) {
                myPref.setName(etName.getText().toString().trim());
                myPref.setEmail(etEmail.getText().toString().trim());
                myPref.setPassword(etPassword.getText().toString().trim());
                databaseManager = new DatabaseManager(this);
                finish();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        });

        loginLink.setOnClickListener(event -> {
            Intent intent = new Intent(this, LoginActivity.class);
            finish();
            startActivity(intent);
        });
    }

    private boolean validateData() {
        List<Boolean> check = new ArrayList<>();

        if (!etName.getText().toString().trim().equals("")) check.add(true);
        else {
            check.add(false);
            tlName.setError("Invalid Name");
        }

        if (!etEmail.getText().toString().trim().equals("")) check.add(true);
        else {
            check.add(false);
            tlEmail.setError("Invalid Email");
        }

        if (!etPassword.getText().toString().trim().equals("") && etPassword.getText().toString().trim().length() >= 6) check.add(true);
        else {
            check.add(false);
            tlPassword.setError("Password is Invalid/ too short");
        }

        if (!etConfirmPassword.getText().toString().trim().equals("") && etConfirmPassword.getText().toString().trim().equals(etPassword.getText().toString().trim())) check.add(true);
        else {
            check.add(false);
            tlConfirmPassword.setError("Password mismatch");
        }

        return !check.contains(false);
    }

    private void initComponent() {
        tlName = findViewById(R.id.tlName);
        tlEmail = findViewById(R.id.tlEmail);
        tlPassword = findViewById(R.id.tlPassword);
        tlConfirmPassword = findViewById(R.id.tlConfirmPassword);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        registerButton = findViewById(R.id.registerButton);
        loginLink = findViewById(R.id.loginLink);
        myPref = new MyPref(RegisterActivity.this);
    }
}