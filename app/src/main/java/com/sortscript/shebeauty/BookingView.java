package com.sortscript.shebeauty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BookingView extends AppCompatActivity {
    CardView bookcard,appcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_view);
        bookcard=findViewById(R.id.BOOK1);
        appcard=findViewById(R.id.App1);
        bookcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Bookingdetails.class);

                startActivity(intent);
            }
        });
       appcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Bookingfrom.class);

                startActivity(intent);
            }
        });
    }
}