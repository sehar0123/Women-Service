package com.sortscript.shebeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Availablefoam extends AppCompatActivity {
    String category, key,title1;
    TextView text2;
    EditText t11, t12, t13;
    Button bt11;
    DatabaseReference reference, ref2,reference2;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availablefoam);
        text2 = findViewById(R.id.text17);
        category = getIntent().getStringExtra("name");
        key = getIntent().getStringExtra("refKey");
        title1=getIntent().getStringExtra("title");

        text2.setText(category);
        t11 = findViewById(R.id.text16);
        reference2 = FirebaseDatabase.getInstance().getReference().child(category).child("Booking");


        t12 = findViewById(R.id.text21);
        t13 = findViewById(R.id.text18);
        bt11 = findViewById(R.id.btn19);
        firebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("User");
        ref2 = FirebaseDatabase.getInstance().getReference().child("Appointment");

        String id = firebaseAuth.getCurrentUser().getUid();


        reference.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    String a = snapshot.child("Username").getValue().toString();
                    String b = snapshot.child("Email").getValue().toString();


                    t11.setText(a);
                    t12.setText(b);

                } catch (Exception e) {
                    Toast.makeText(Availablefoam.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addNotification();

                String a = t11.getText().toString();
                String b = t12.getText().toString();
                String c = t13.getText().toString();

                if (c.isEmpty()) {

                    Toast.makeText(getApplicationContext(), " Description Is Empty", Toast.LENGTH_LONG).show();
                } else {

                Map<String, Object> map = new HashMap<>();
                map.put("UserName", a);
                map.put("Email", b);
                map.put("Description", c);





                map.put("Reference", key);
                ref2.child(category).push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Availablefoam.this, "Book an Appointment", Toast.LENGTH_SHORT).show();

                            reference2.child(key).child("Title").setValue("Pending");
                        } else {
                            Toast.makeText(Availablefoam.this, task.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }}
        });

    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.icon)
                        .setContentTitle("Appointment")
                        .setContentText("Your  Appointment is book");

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

}
