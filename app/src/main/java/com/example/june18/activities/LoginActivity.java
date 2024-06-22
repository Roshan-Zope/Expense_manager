package com.example.june18.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.june18.utils.MyPref;
import com.example.june18.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout tlEmail;
    private TextInputLayout tlPassword;
    private TextInputEditText etPassword;
    private TextInputEditText etEmail;
    private MaterialButton loginButton;
    private MaterialTextView registerLink;
    private MyPref myPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponent();

        loginButton.setOnClickListener(event -> {
            if (validateData()) {
                if (String.valueOf(etEmail.getText()).trim().equals(myPref.getEmail().trim()) && String.valueOf(etPassword.getText()).trim().equals(myPref.getPassword().trim())) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Credentials wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerLink.setOnClickListener(event -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            finish();
            startActivity(intent);
        });
    }

    private boolean validateData() {
        List<Boolean> check = new ArrayList<>();

        if (!String.valueOf(etEmail.getText()).trim().isEmpty()) check.add(true);
        else {
            check.add(false);
            tlEmail.setError("Invalid mail");
        }

        if (!String.valueOf(etPassword.getText()).trim().isEmpty() && String.valueOf(etPassword.getText()).trim().length() >= 6) check.add(true);
        else {
            check.add(false);
            tlPassword.setError("Invalid Password");
        }

        return !check.contains(false);
    }

    private void initComponent() {
        tlEmail = findViewById(R.id.tlEmail);
        tlPassword = findViewById(R.id.tlPassword);
        loginButton = findViewById(R.id.loginButton);
        registerLink = findViewById(R.id.registerLink);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        myPref = new MyPref(LoginActivity.this);
    }
}