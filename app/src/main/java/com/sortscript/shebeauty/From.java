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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class From extends AppCompatActivity {
    EditText t1, t2, t3, t4, t5, t6;
    Button bt1, bt2;
    TextView text,text1,text2;
    DatabaseReference reference;
    StorageReference firebaseStorage;
    ImageView imageView;
    private static final int IMAGE_PICKER_CODE = 1;
    Uri uriImage;
    private static final int PERMISSION_CODE = 1001;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from);
        text= findViewById(R.id.text12);
        Intent myIntent = getIntent();
        String value = myIntent.getStringExtra("message");
        text.setText(value);

        progressDialog = new ProgressDialog(this);
        t1 = findViewById(R.id.text4);
        bt2 = findViewById(R.id.button);
        imageView = findViewById(R.id.IMAGE);
        t2 = findViewById(R.id.text5);
        t3 = findViewById(R.id.text6);
        t4 = findViewById(R.id.text7);
        t5 = findViewById(R.id.text8);
        t6 = findViewById(R.id.text9);

        firebaseStorage = FirebaseStorage.getInstance().getReference();
        bt1 = findViewById(R.id.btn1);
        reference = FirebaseDatabase.getInstance().getReference().child(value);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                uploadToFirebase(uriImage);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
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
            imageView.setImageURI(data.getData());

            uriImage = data.getData();


        }
    }

    private void uploadToFirebase(Uri uri) {

        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        StorageReference fileRef = firebaseStorage.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String imageLink = uri.toString();

                        String a = t1.getText().toString();
                        String b = t2.getText().toString();
                        String c = t3.getText().toString();
                        String d = t4.getText().toString();
                        String e = t5.getText().toString();
                        String f = t6.getText().toString();


                        Map<String, Object> map = new HashMap<>();
                        map.put("Name", a);
                        map.put("PhoneNumber", b);
                        map.put("Address", c);
                        map.put("image", imageLink);
                        map.put("Certificate", d);
                        map.put("Experience", e);
                        map.put("Description", f);

                        reference.push().setValue(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            progressDialog.dismiss();
                                            Toast.makeText(From.this, "Success", Toast.LENGTH_SHORT).show();
                                        } else {
                                            progressDialog.dismiss();
                                            Toast.makeText(From.this, task.toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                Toast.makeText(From.this, snapshot.toString(), Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(From.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }
}