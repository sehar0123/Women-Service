 package com.sortscript.shebeauty;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgetpassword extends AppCompatActivity {
    Toolbar toolbar;
    ProgressBar progressBar;
    EditText text;
    Button button;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        text=findViewById(R.id.t9);
        button=findViewById(R.id.b3);
      



        firebaseAuth=FirebaseAuth.getInstance();



        progressBar=findViewById(R.id.PBAR1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.sendPasswordResetEmail(text.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(Forgetpassword.this, "password sent to your email", Toast.LENGTH_SHORT).show();

                                }else {
                                    Toast.makeText(Forgetpassword.this, task.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}