package com.example.bus.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bus.R;

public class Login extends AppCompatActivity {

    EditText et_username, et_password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login();
    }

    void Login() {
        et_username = (EditText) findViewById(R.id.email);
        et_password = (EditText) findViewById(R.id.email);
        btn_login = (Button) findViewById(R.id.email_sign_in_button);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_username.getText().toString().equals("mmm") && et_password.getText().toString().equals("000")) {
                    Toast.makeText(Login.this, "Username and Password is correct", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, activityc.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}