package com.sortscript.shebeauty;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Bookingfrom extends AppCompatActivity {
    TimePicker simpleAnalogClock;
    DatePicker calendarView;
    Button btbk,btk2;
    DatabaseReference reference3;
    Spinner spinner1;
    String category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingfrom);
        calendarView = findViewById(R.id.simpleCalendarView);

        simpleAnalogClock = findViewById(R.id.simpleAnalogClock);
//        btbk = findViewById(R.id.btbook1);
        btk2 = findViewById(R.id.btbook2);
        spinner1 = findViewById(R.id.spinnerbookId);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(Bookingfrom.this,
                R.array.category, android.R.layout.simple_list_item_activated_1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner1.setAdapter(arrayAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString();
                formSlots(category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }


    private void formSlots(String category) {

        reference3 = FirebaseDatabase.getInstance().getReference().child(category).child("Booking");
//        btbk.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View v) {
//                int day = calendarView.getDayOfMonth();
//                int month = calendarView.getMonth();
//                int year = calendarView.getYear();
//                btbk.setText("Book/'" +
//                        "");
//
//                String u = day + "-" + month + "-" + year;
//                String w = simpleAnalogClock.getHour() + ":" + simpleAnalogClock.getMinute() + " PM";
//
//                Map<String, Object> map = new HashMap<>();
//                map.put("Date", u);
//                map.put("Time", w);
//                map.put("Title","Book");
//
//
//
//                reference3.push().setValue(map)
//                        .addOnCompleteListener(task -> {
//                            if (task.isSuccessful()) {
//
//                                Toast.makeText(Bookingfrom.this, "Success", Toast.LENGTH_SHORT).show();
//                            } else {
//
//                                Toast.makeText(Bookingfrom.this, task.toString(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//
//            }
//        });

        btk2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                int day = calendarView.getDayOfMonth();
                int month = calendarView.getMonth();
                int year = calendarView.getYear();


                String u = day + "-" + month + "-" + year;
                String w = simpleAnalogClock.getHour() + ":" + simpleAnalogClock.getMinute() + " PM";

                Map<String, Object> map = new HashMap<>();
                map.put("Date", u);
                map.put("Time", w);
                map.put("Title","Available");


                reference3.push().setValue(map)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {

                                Toast.makeText(Bookingfrom.this, "Success", Toast.LENGTH_SHORT).show();
                            } else {

                                Toast.makeText(Bookingfrom.this, task.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });
    }
}