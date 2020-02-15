package com.example.android.beach_agri;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.concurrent.ExecutionException;

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
    private int pos;
    private String image;
    private Bitmap bm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_viewproduct);
        //initialise layout
        mImage=(ImageView) findViewById((R.id.productimage));
        mName=(TextView)findViewById(R.id.name);
        mlocation=(TextView)findViewById(R.id.location);
        mAmount=(TextView)findViewById(R.id.amount);
        mRate=(TextView)findViewById(R.id.Rate);
        mPay=(Button)findViewById(R.id.payonline);
        pos=getIntent().getIntExtra("pos",0);
        image=getIntent().getStringExtra("image");

        mPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Payment.class));
            }
        });
        try {
            bm = new Retrievebitmap().execute(image).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(pos==0) {
            mImage.setImageBitmap(bm);
            mName.setText("kevin");
            mRate.setText("65");
            mlocation.setText("Sector 13 House no.127,Vasundhara,Ghaziabad,201012");
            mAmount.setText("321");
        }
        else if(pos==1){
           mImage.setImageBitmap(bm);
            mName.setText("Raj");
            mRate.setText("23");
            mlocation.setText("Kallara apartments,Koovapally,Kerala,686518");
            mAmount.setText("134");
        }
        else if(pos==2){
            mImage.setImageBitmap(bm);
            mName.setText("Francis");
            mRate.setText("54");
            mlocation.setText("Kullarikal apartments,Koovapally,Kerala,686518");
            mAmount.setText("132");
        }
        else if(pos==3){
            mImage.setImageBitmap(bm);
            mName.setText("Antony");
            mRate.setText("24");
            mlocation.setText("Thazhakalayil apartments,Ponkunnam,Kerala,686512");
            mAmount.setText("100");
        }
        else if(pos==4){
            mImage.setImageBitmap(bm);
            mName.setText("Thomas");
            mRate.setText("20");
            mlocation.setText("Thamarasseri,Koothatukulam,Kerala,686603");
            mAmount.setText("200");
        }
        else if(pos==5){
            mImage.setImageBitmap(bm);
            mName.setText("Aniruth");
            mRate.setText("12");
            mlocation.setText("Mullasseri,Kumali,Kerala,686509");
            mAmount.setText("231");
        }
        else if(pos==6){
            mImage.setImageBitmap(bm);
            mName.setText("Ajumon");
            mRate.setText("56");
            mlocation.setText("Kulathunkal,Kooroppada,Kerala,686502");
            mAmount.setText("185");
        }
        else{
            mImage.setImageBitmap(bm);
            mName.setText("Venu");
            mRate.setText("200");
            mlocation.setText("Thirukudumbam,Pala,Kerala,686511");
            mAmount.setText("300");
        }
       // firebaseStorage = FirebaseStorage.getInstance();
        //firebaseAuth= FirebaseAuth.getInstance();
        //storageReference = firebaseStorage.getReference();
        //user=firebaseAuth.getCurrentUser();
      //  mDatabase = FirebaseDatabase.getInstance().getReference("Products");

    }


}
