package com.example.bus.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bus.R;
import com.example.bus.ui.driver.auth.Logindriver;

public class activityb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityb);
    }

    public void driver(View view) {
        Intent SC = new Intent(this, Logindriver.class);
        startActivity(SC);
    }

    public void user(View view) {
        Intent SC = new Intent(this,Login.class);
        startActivity(SC);
    }
}