package com.example.bus.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.example.bus.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    //private static final int REQUEST_CODE = 1;

    private static final String[] STORAGE_PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final String[] CALL_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.b1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), activityb.class);
                startActivity(intent);
            }
        });
        verifyPermissions();}
        private void verifyPermissions(){
            Log.d(TAG, "verifyPermissions: Checking Permissions.");

            int permissionCallPhone = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

            int permissionExternalMemory = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permissionCallPhone != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        CALL_PERMISSIONS,
                        1
                );
            }

            if (permissionExternalMemory != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        STORAGE_PERMISSIONS,
                        1
                );
            }}}