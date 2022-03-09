package com.sortscript.shebeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activitydetails1 extends AppCompatActivity {
    TextView textView, textView2, textView3, textView4;
    ImageView imageView;
    DatabaseReference r2;
    String category, key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitydetails1);

        category = getIntent().getStringExtra("category");
        key = getIntent().getStringExtra("refKey");

        r2 = FirebaseDatabase.getInstance().getReference().child(category);

        textView = findViewById(R.id.textg1);
        textView2 = findViewById(R.id.textg2);
        textView3 = findViewById(R.id.textg3);
        textView4 = findViewById(R.id.textg4);
        imageView = findViewById(R.id.image1);

        r2.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String a = snapshot.child("Name").getValue().toString();
                String b= snapshot.child("Experience").getValue().toString();
                String c= snapshot.child("Certificate").getValue().toString();
                String d= snapshot.child("Description").getValue().toString();
                String e= snapshot.child("image").getValue().toString();
               textView.setText(a);
                textView2.setText(b);
                textView3.setText(c);
                textView4.setText(d);
                Glide.with(getApplicationContext()).load(e).into(imageView);







            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}