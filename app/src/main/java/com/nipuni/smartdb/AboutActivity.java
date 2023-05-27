package com.nipuni.smartdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class AboutActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap gMap;
    SupportMapFragment mapView;
    Marker mCurrentLocationMarker;
    FusedLocationProviderClient client;
    private ConstraintLayout cl_location;
    private TextView tv_location;
    //private ConstraintLayout map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        cl_location = findViewById(R.id.cl_location);
        tv_location = findViewById(R.id.tv_location);


        //map
        mapView = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapView.getMapAsync(this);

        client = LocationServices.getFusedLocationProviderClient(this);

        //location button pressed
        cl_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cl_location.setVisibility(View.INVISIBLE);
                tv_location.setVisibility(View.INVISIBLE);
                getCurrentLocation();
            }
        });


    }



    //get my location
    private void getCurrentLocation() {
                        mapView.getMapAsync(new OnMapReadyCallback() {
                            public void onMapReady(GoogleMap googleMap) {
                                LatLng latLng = new LatLng(6.4186456380316415, 80.01452898320811);
                                MarkerOptions options = new MarkerOptions().position(latLng)
                                        .title("I'm Nipuni..");
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                                googleMap.addMarker(options);
                            }
                        });
                }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}