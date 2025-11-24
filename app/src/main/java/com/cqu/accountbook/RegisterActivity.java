package com.cqu.accountbook;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cqu.accountbook.database.DatabaseAction;
import com.cqu.accountbook.database.User;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "账号密码不为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                int userCount = DatabaseAction.getInstance(RegisterActivity.this).userDao().countUsersByAccount(username);
                if (userCount == 0) {
                    DatabaseAction.getInstance(RegisterActivity.this).userDao().insertUser(new User(username, password));
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "账号重复", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
