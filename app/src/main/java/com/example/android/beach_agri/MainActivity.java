package com.example.android.beach_agri;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String TAG ="Mainactivity" ;
    private FirebaseUser user;

    private FirebaseAuth mAuth;
    private StorageReference storageReference;
    private Farmer farmer;
    private Product product;
    private FirebaseStorage firebaseStorage;
    private DatabaseReference mDatabase1;
    private DatabaseReference mDatabase2;
    private UploadListAdapter uploadListAdapter;
    private RecyclerView recyclerView;
    private List<String> filenameList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_main);
        filenameList=new ArrayList<>();

        //initialise firebase
        firebaseStorage = FirebaseStorage.getInstance();
        mAuth= FirebaseAuth.getInstance();
        storageReference = firebaseStorage.getReference();
        user=mAuth.getCurrentUser();


        mDatabase1 = FirebaseDatabase.getInstance().getReference("Products");

        FloatingActionButton fab = findViewById(R.id.fab);
        recyclerView=(RecyclerView)findViewById(R.id.Recyclerview);

        //initialise list adapter
        uploadListAdapter = new UploadListAdapter(filenameList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(uploadListAdapter);


        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    String name = userSnapshot.child("Productname").getValue().toString().trim();
                    filenameList.add(name);
                    uploadListAdapter.notifyDataSetChanged();
                    Log.d(TAG, "onChildAdded:" + name);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Addproduct.class));

            }
        });
    }
}