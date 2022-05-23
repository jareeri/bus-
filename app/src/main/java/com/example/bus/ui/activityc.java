package com.example.bus.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bus.R;

public class activityc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityc);
    }

    public void Bus(View view) {
        Intent SC = new Intent(this,listviewuser.class);
        startActivity(SC);
    }

    public void service(View view) {
        Intent SC = new Intent(this,listviewuser.class);
        startActivity(SC);
    }

    public void van(View view) {
        Intent SC = new Intent(this,listviewuser.class);
        startActivity(SC);
    }
}