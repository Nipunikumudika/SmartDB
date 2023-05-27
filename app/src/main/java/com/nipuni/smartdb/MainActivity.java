package com.nipuni.smartdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_tp;
    private Button btn_add;
    private Button btn_view;
    private Button btn_about;

    private DrawerLayout drawer_layout;
    private NavigationView nav_view;
    private Toolbar toolbar;

    String name;
    String tp;

    FirebaseAuth firebaseAuth;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name=findViewById(R.id.et_name);
        et_tp=findViewById(R.id.et_tp);
        btn_add=findViewById(R.id.btn_add);
        btn_view=findViewById(R.id.btn_view);
        btn_about=findViewById(R.id.btn_about);
        drawer_layout = findViewById(R.id.drawer_layout);
        nav_view = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserInfo");

        firebaseAuth = FirebaseAuth.getInstance();

        btn_add.setOnClickListener(this);
        btn_view.setOnClickListener(this);
        btn_about.setOnClickListener(this);

        nav_view.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        //side navigation
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                switch (id){
                    case R.id.nav_view:

                        Intent intentView = new Intent(getApplicationContext(), ViewActivity.class);
                        startActivity(intentView);
                        drawer_layout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_about:
                        Intent intentAbout = new Intent(getApplicationContext(), AboutActivity.class);
                        startActivity(intentAbout);
                        drawer_layout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.nav_logout:

                        firebaseAuth.signOut();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        drawer_layout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
    }

    //button clicks
    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id) {
            case R.id.btn_add:
                name = et_name.getText().toString().trim();
                tp = et_tp.getText().toString().trim();

                //Firebase Add Data
                UserDataBase userDataBase = new UserDataBase(name, tp);

                databaseReference.child(userDataBase.name).setValue(userDataBase).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Successfully added...", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed...", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case R.id.btn_view:
                Intent intentView = new Intent(getApplicationContext(), ViewActivity.class);
                startActivity(intentView);
                break;

            case R.id.btn_about:
                Intent intentAbout = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intentAbout);
                break;
            }
    }
}