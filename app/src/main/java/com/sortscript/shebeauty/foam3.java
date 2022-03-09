package com.sortscript.shebeauty;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class foam3 extends AppCompatActivity {
    EditText t1, t2, t3,t4;
    Button bt1, btn3;

    DatabaseReference reference;
    StorageReference firebaseStorage1;
    ImageView imageView1;
    private static final int IMAGE_PICKER_CODE = 1;
    Uri uriImage1;
    private static final int PERMISSION_CODE = 1001;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foam3);
        progressDialog = new ProgressDialog(this);
        t1 = findViewById(R.id.textname1);
        btn3 = findViewById(R.id.buttonup1);
        imageView1 = findViewById(R.id.IMAGE1);
        t2 = findViewById(R.id.textpn1);
        t3 = findViewById(R.id.textad1);
        t4 = findViewById(R.id.textd2);

        firebaseStorage1 = FirebaseStorage.getInstance().getReference();
        bt1 = findViewById(R.id.btnsub);
        reference = FirebaseDatabase.getInstance().getReference().child("Packages");
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                uploadToFirebase(uriImage1);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        pickImageFromGallery();


                    }
                } else {
                    pickImageFromGallery();
                }
            }
        });


    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICKER_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICKER_CODE) {
            imageView1.setImageURI(data.getData());

            uriImage1= data.getData();


        }
    }

    private void uploadToFirebase(Uri uri1) {

        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        StorageReference fileRef = firebaseStorage1.child(System.currentTimeMillis() + "." + getFileExtension(uri1));
        fileRef.putFile(uri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri1) {
                        String imageLink = uri1.toString();

                        String h = t1.getText().toString();
                        String i = t2.getText().toString();
                        String g= t3.getText().toString();
                        String k = t4.getText().toString();



                        Map<String, Object> map = new HashMap<>();
                        map.put("Name", h);
                        map.put("PhoneNumber", i);
                        map.put("Address", g);
                        map.put("image", imageLink);

                        map.put("Description",k);

                        reference.push().setValue(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            progressDialog.dismiss();
                                            Toast.makeText(foam3.this, "Success", Toast.LENGTH_SHORT).show();
                                        } else {
                                            progressDialog.dismiss();
                                            Toast.makeText(foam3.this, task.toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                Toast.makeText(foam3.this, snapshot.toString(), Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(foam3.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri1) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri1));
    }
}
