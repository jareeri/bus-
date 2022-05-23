package com.example.bus.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.bus.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class van extends AppCompatActivity {

    double distance;
    int dist;
    private GoogleMap mMap;


    SupportMapFragment smf;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        client = LocationServices.getFusedLocationProviderClient(this);

        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        getmylocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.r1:
                if ( checked )
                    mMap.setMapType(1);
                break;
            case R.id.r2:
                if ( checked )
                    mMap.setMapType(3);
                break;
            case R.id.r3:
                if ( checked )
                    mMap.setMapType(4);
                break;
        }
    }


    public void getmylocation() {
        if ( ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions

            return;
        }

        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                smf.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(final GoogleMap googleMap) {
                        final LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("You are here...!!").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                        googleMap.addMarker(markerOptions);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));


                        Button b = (Button) findViewById(R.id.btn);
                        TextView tv = (TextView) findViewById(R.id.distance);


                        mMap = googleMap;

                        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                            public void onMapClick(LatLng point) {

                                Location startPoint = new Location("locationA");
                                startPoint.setLatitude(location.getLatitude());
                                startPoint.setLongitude(location.getLongitude());

                                Location endPoint = new Location("locationB");
                                endPoint.setLatitude(point.latitude);
                                endPoint.setLongitude(point.longitude);

                                distance = startPoint.distanceTo(endPoint);
                                dist = (int) distance;

                                if ( dist > 0 && dist <= 10 )
                                    b.setBackgroundColor(Color.rgb(255, 0, 0));
                                else if ( dist > 10 && dist <= 150 )
                                    b.setBackgroundColor(Color.rgb(255, 51, 51));
                                else if ( dist > 150 && dist <= 300 )
                                    b.setBackgroundColor(Color.rgb(255, 102, 102));
                                else if ( dist > 300 && dist <= 500 )
                                    b.setBackgroundColor(Color.rgb(255, 153, 51));
                                else if ( dist > 500 && dist <= 650 )
                                    b.setBackgroundColor(Color.rgb(51, 255, 255));
                                else if ( dist > 650 && dist <= 800 )
                                    b.setBackgroundColor(Color.rgb(51, 153, 255));
                                else
                                    b.setBackgroundColor(Color.rgb(51, 51, 255));

                                tv.setText("you are " + dist + " meter away");
                                tv.setBackgroundColor(Color.YELLOW);


                            }
                        });


                        Circle circle = mMap.addCircle(new CircleOptions()
                                .center(new LatLng(location.getLatitude(), location.getLongitude()))
                                .radius(10)
                                .strokeColor(Color.BLUE)
                                .fillColor(Color.argb(99, 135, 206, 235)));


                        Button cam = (Button) findViewById(R.id.camBtn);
                        cam.setBackgroundColor(Color.argb(100, 255, 255, 255));
                        cam.setText("Center");
                        cam.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20.0f));

                            }
                        });
                        cam.setBackgroundColor(Color.argb(80, 255, 255, 255));
                        cam.setTextColor(Color.GRAY);


                    }
                });
            }
        });
    }


}