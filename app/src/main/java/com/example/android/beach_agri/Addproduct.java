package com.example.android.beach_agri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class Addproduct extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 14;
    private EditText eProductname;
    private EditText eStock;
    private EditText ePrice;
    private Button bchoose;
    private String uniqueID = UUID.randomUUID().toString();
    private Button baddprod;
    private boolean containsimage=false;
    private Uri imageuri;
    private ProgressDialog progressDialog;
    private FirebaseUser user;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private Product product;
    private String TAG="addproduct";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_addproduct);
        eProductname=(EditText)findViewById(R.id.Productname);
        eStock=(EditText)findViewById(R.id.Stock);
        ePrice=(EditText)findViewById(R.id.EPrice);
        bchoose=(Button)findViewById(R.id.choosepicture);
        baddprod=(Button)findViewById(R.id.AddProd);
        progressDialog = new ProgressDialog(this);


        //initialise irebase
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        storageReference = firebaseStorage.getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        user = firebaseAuth.getCurrentUser();

        bchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        baddprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (containsimage) {
                    product = new Product(user.getUid(), eProductname.getText().toString().trim(), Integer.parseInt(eStock.getText().toString().trim()), Integer.parseInt(ePrice.getText().toString().trim()), imageuri.toString());
                    mDatabase.child("Products").child(uniqueID).setValue(product);
                    mDatabase.child("Users").child(user.getUid()).child("ProductId").child(uniqueID).setValue("");
                    finish();
                }
                else{
                    product = new Product(user.getUid(), eProductname.getText().toString().trim(), Integer.parseInt(eStock.getText().toString().trim()), Integer.parseInt(ePrice.getText().toString().trim()), null);
                    mDatabase.child("Products").child(uniqueID).setValue(product);
                    mDatabase.child("Users").child(user.getUid()).child("ProductId").child(uniqueID).setValue("");
                    finish();
                }

            }
        });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data.getData() != null) {

                containsimage = true;
                uploadImage(data.getData());




            }
        } else {
            containsimage = false;
        }
    }

    //upload
    private void uploadImage(Uri fileuri) {
        progressDialog.setMessage("Uploading image please wait");
        progressDialog.show();
        String filename = getFileName(fileuri);
        if (fileuri != null) {

            final StorageReference fileupload = storageReference.child("images").child(filename);


            fileupload.putFile(fileuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileupload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            progressDialog.dismiss();
                            imageuri= uri;
                            //Do what you want with the url
                        }

                    });

                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.e(TAG, "yeeeeeee" + exception.toString());
                    // Handle unsuccessful uploads
                }
            });
        }

    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }


}
