package com.sortscript.shebeauty;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    CardView cardView, cardView1, cardView2, cardView3,cardView4,cardView5,cardView6;
    TextView textView, textView1, textView2,textView3;
    LinearLayout linearLayout;
    FirebaseAuth firebaseAuth;
    Button btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.t2);
        textView1 = findViewById(R.id.t3);
        textView2 = findViewById(R.id.text);
        linearLayout=findViewById(R.id.Linear1);
        textView3 = findViewById(R.id.t5);
       cardView3 = findViewById(R.id.card6);
       cardView4=findViewById(R.id.card7);
        cardView5=findViewById(R.id.cardbook7);
        cardView6=findViewById(R.id.card9);
        btn9 = findViewById(R.id.button5);

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Recyle5.class));
            }
        });

        cardView = findViewById(R.id.fitness);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = textView2.getText().toString();
                Intent intent = new Intent(MainActivity.this, From.class);
                intent.putExtra("message", value);
                startActivity(intent);



            }
        });
        cardView1 = findViewById(R.id.salon);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = textView1.getText().toString();


                Intent intent = new Intent(MainActivity.this, From.class);
                intent.putExtra("message", value);
                startActivity(intent);


            }
        });
        cardView2 = findViewById(R.id.swing);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = textView.getText().toString();
                Intent intent = new Intent(MainActivity.this, From.class);
                intent.putExtra("message", value);
                startActivity(intent);




            }


        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,foam3.class);
                startActivity(intent);
            }


        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Bookingfrom.class);
                startActivity(intent);
            }


        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Appointment.class);
                startActivity(intent);
            }


        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, Registerpage.class);
                startActivity(intent);


            }
        });



    }

}