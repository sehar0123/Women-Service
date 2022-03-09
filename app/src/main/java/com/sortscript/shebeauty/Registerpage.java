package com.sortscript.shebeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registerpage extends AppCompatActivity {
    TextView text1, text2, text4, text5, text6;
    LinearLayout linear1, linear2;
    Button but1, but2;
    TextInputLayout edit1, edit2, edit3, edit4, edit5;
    TextInputEditText gt1, gt2, gt3, gt4, gt5;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage);
        gt3 = findViewById(R.id.gt3Id);
        gt4 = findViewById(R.id.gt4Id);
        gt5 = findViewById(R.id.gt5Id);
        linear1 = findViewById(R.id.L1);
        linear2 = findViewById(R.id.L2);

        but2 = findViewById(R.id.b2);
        edit4 = findViewById(R.id.e4);
        edit5 = findViewById(R.id.e5);
        firebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("User");
        gt1 = findViewById(R.id.gt1Id);
        gt2 = findViewById(R.id.gt2Id);
        edit1 = findViewById(R.id.e1);
        edit2 = findViewById(R.id.e2);
        edit3 = findViewById(R.id.e3);
        but1 = findViewById(R.id.b1);
        but2 = findViewById(R.id.b2);


        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = gt1.getText().toString();
                String b = gt2.getText().toString();
                if (a.isEmpty() || b.isEmpty()) {

                    Toast.makeText(Registerpage.this, " Email and Password is empty ", Toast.LENGTH_LONG).show();
                } else {

                    firebaseAuth.signInWithEmailAndPassword(a, b).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                if (a.equals("shebeauty@gmail.com")) {

                                    Toast.makeText(Registerpage.this, "Going to admin ", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Registerpage.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Registerpage.this, "Going to user ", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Registerpage.this, Home.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } else {
                                Toast.makeText(Registerpage.this, task.toString(), Toast.LENGTH_SHORT).show();


                            }
                        }
                    });
                }
            }


        });


        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c = gt3.getText().toString();
                String a = gt4.getText().toString();
                String b = gt5.getText().toString();


                firebaseAuth.createUserWithEmailAndPassword(a, b).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registerpage.this, "SUCCESSFUL", Toast.LENGTH_SHORT).show();

                            Map<String, Object> map = new HashMap<>();
                            map.put("Email", a);


                            map.put("Username", c);


                            String id = firebaseAuth.getCurrentUser().getUid();
                            reference.child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Registerpage.this, "Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Registerpage.this, Home.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(Registerpage.this, "FAIL ", Toast.LENGTH_SHORT).show();


                                    }
                                }
                            });

                        }
                    }
                });
            }
        });

        text1 = findViewById(R.id.t1);

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear1.setVisibility(View.VISIBLE);
                linear2.setVisibility(View.GONE);

            }
        });


        text2 = findViewById(R.id.t2);
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear2.setVisibility(View.VISIBLE);
                linear1.setVisibility(View.GONE);


            }
        });
        text6 = findViewById(R.id.forgetpassword);
        text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registerpage.this, Forgetpassword.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseAuth.getCurrentUser() != null) {
            if (firebaseAuth.getCurrentUser().getEmail().equals("shebeauty@gmail.com")) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            } else {
                startActivity(new Intent(getApplicationContext(), Home.class));
                finish();
            }
        }

    }


}