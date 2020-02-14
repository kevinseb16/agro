package com.example.android.beach_agri;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class showdetails extends AppCompatActivity {

    private TextView mName;
    private TextView mlocation;
    private TextView mAmount;
    private TextView mRate;
    private Button mPay;
    private ImageView mImage;

    //initialise firebase
    private FirebaseUser user;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_viewproduct);
        //initialise layout
        mName=(TextView)findViewById(R.id.name);
        mlocation=(TextView)findViewById(R.id.location);
        mAmount=(TextView)findViewById(R.id.amount);
        mRate=(TextView)findViewById(R.id.Rate);

        firebaseStorage = FirebaseStorage.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        storageReference = firebaseStorage.getReference();
        user=firebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("Products");

    }


}
