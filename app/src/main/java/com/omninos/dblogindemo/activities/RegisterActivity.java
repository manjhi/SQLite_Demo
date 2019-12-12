package com.omninos.dblogindemo.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.omninos.dblogindemo.R;
import com.omninos.dblogindemo.modelClass.User;
import com.omninos.dblogindemo.helperClass.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button register;
    private EditText name, email, password;
    private String name_, email_, password_;
    private DatabaseHelper databaseHelper;
    private TextView moveTologin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        SetUp();
    }

    private void initView() {
        register = findViewById(R.id.register);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        moveTologin = findViewById(R.id.moveTologin);

        databaseHelper = new DatabaseHelper(RegisterActivity.this);
    }

    private void SetUp() {

        register.setOnClickListener(this);
        moveTologin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register:
                Validate();
                break;
            case R.id.moveTologin:
                onBackPressed();
                break;
        }
    }

    private void Validate() {
        name_ = name.getText().toString();
        email_ = email.getText().toString();
        password_ = password.getText().toString();
        if (name_.isEmpty()) {
            Toast.makeText(this, "enter name", Toast.LENGTH_SHORT).show();
        } else if (email_.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email_).matches()) {
            Toast.makeText(this, "enter valid email", Toast.LENGTH_SHORT).show();
        } else if (password_.isEmpty()) {
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
        } else {
            if (databaseHelper.checkUser(email_)) {
                Toast.makeText(this, "email already exist", Toast.LENGTH_SHORT).show();
            } else {
                User user = new User();
                user.setName(name_);
                user.setEmail(email_);
                user.setPassword(password_);
                databaseHelper.addUser(user);
                Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        }
    }
}
