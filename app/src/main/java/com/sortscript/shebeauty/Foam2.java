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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class Foam2 extends AppCompatActivity {
    EditText t1, t2, t3, t4, t5, t6;
    Button bt1;
    TextView text, text1, text2;
    DatabaseReference reference;
    StorageReference firebaseStorage;
    String key, category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foam2);

        key = getIntent().getStringExtra("key");
        category = getIntent().getStringExtra("category");

        text = findViewById(R.id.text12);
        Intent myIntent = getIntent();
        String value = myIntent.getStringExtra("message");
        text.setText(value);


        t1 = findViewById(R.id.text4);

        t2 = findViewById(R.id.text5);
        t3 = findViewById(R.id.text6);
        t4 = findViewById(R.id.text7);
        t5 = findViewById(R.id.text8);
        t6 = findViewById(R.id.text9);
        firebaseStorage = FirebaseStorage.getInstance().getReference();
        reference = FirebaseDatabase.getInstance().getReference().child(category);

        reference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String a = snapshot.child("Name").getValue().toString();
                String b = snapshot.child("PhoneNumber").getValue().toString();
                String c = snapshot.child("Address").getValue().toString();
                String d = snapshot.child("Experience").getValue().toString();
                String e = snapshot.child("Certificate").getValue().toString();
                String f = snapshot.child("Description").getValue().toString();

                t1.setText(a);
                t2.setText(b);
                t3.setText(c);
                t4.setText(d);
                t5.setText(e);
                t6.setText(f);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        bt1 = findViewById(R.id.btn1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                map.put("Certificate", d);
                map.put("Experience", e);
                map.put("Description", f);

                reference.child(key).updateChildren(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Foam2.this, "Updated", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Foam2.this, task.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }
}