package com.nipuni.smartdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    private Button btn_back;

    RecyclerView rv_data;
    UserAdapter userAdapter;
    ArrayList<UserDataBase> nameList = new ArrayList<>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        rv_data = findViewById(R.id.rv_data);
        btn_back = findViewById(R.id.btn_back);

        firebaseDatabase = FirebaseDatabase.getInstance();

        //view data
        databaseReference = firebaseDatabase.getReference("UserInfo");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("TAG", "onDataChange: " + snapshot);
                for (DataSnapshot data : snapshot.getChildren()){

                    UserDataBase userDataBase =  data.getValue(UserDataBase.class);
                    nameList.add(userDataBase);

                }

                Log.d("TAG", "onDataChange: " + nameList);

                userAdapter = new UserAdapter(ViewActivity.this, nameList, new UserAdapter.itemClickListner() {
                    @Override
                    public void onItemClick(int position) {
                        UserDataBase person = nameList.get(position);
                        Toast.makeText(getApplicationContext(), "Name: " + person.getName() +" - Telephone No: " +person.getTp(), Toast.LENGTH_SHORT).show();
                    }
                });
                rv_data.setAdapter(userAdapter);
                rv_data.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //back button click
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDashboard = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentDashboard);
            }
        });

    }
}