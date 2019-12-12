package com.omninos.dblogindemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.omninos.dblogindemo.R;
import com.omninos.dblogindemo.helperClass.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email, password;
    String email_, password_;
    private Button loginButton;
    DatabaseHelper databaseHelper;
    private TextView moveToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        Setup();
    }

    private void initView() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        moveToRegister = findViewById(R.id.moveToRegister);
        databaseHelper = new DatabaseHelper(LoginActivity.this);
    }

    private void Setup() {
        loginButton.setOnClickListener(this);
        moveToRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginButton:
                Validate();
                break;
            case R.id.moveToRegister:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    private void Validate() {
        email_ = email.getText().toString();
        password_ = password.getText().toString();
        if (email_.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email_).matches()) {
            Toast.makeText(this, "enter valid email", Toast.LENGTH_SHORT).show();
        } else if (password_.isEmpty()) {
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
        } else {
            if (databaseHelper.checkUser(email_, password_)) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else {
                Toast.makeText(this, "Invalid login detail", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
