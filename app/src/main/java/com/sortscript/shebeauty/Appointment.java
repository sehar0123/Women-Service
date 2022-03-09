package com.sortscript.shebeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Appointment extends AppCompatActivity {

    RecyclerView r11;
    Spinner spinner1;
    String category;
    DatabaseReference r12, reference2;
    FirebaseRecyclerOptions<Model7> options6;
    FirebaseRecyclerAdapter<Model7, Holder7> adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        r11 = findViewById(R.id.recyle23);
        spinner1 = findViewById(R.id.spinnerIdA1);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(Appointment.this,
                R.array.category, android.R.layout.simple_list_item_activated_1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner1.setAdapter(arrayAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString();
                myAdapter(category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void myAdapter(String categoryLink) {

        r12 = FirebaseDatabase.getInstance().getReference().child("Appointment").child(categoryLink);
        reference2 = FirebaseDatabase.getInstance().getReference().child(categoryLink).child("Booking");

        options6 = new FirebaseRecyclerOptions.Builder<Model7>().setQuery(r12, Model7.class)
                .build();
        adapter1 = new FirebaseRecyclerAdapter<Model7, Holder7>(options6) {
            @Override
            protected void onBindViewHolder(@NonNull Holder7 holder7, int position, @NonNull Model7 model7) {

                String UserName = model7.getUserName();
                String Email = model7.getEmail();
                String Description = model7.getDescription();


                holder7.text1b.setText(UserName);
                holder7.textb12.setText(Email);
                holder7.textb13.setText(Description);

                holder7.A1.setOnClickListener(v -> {
                    reference2.child(model7.getReference()).child("Title").setValue("Booked");
                });
            }

            @NonNull
            @Override
            public Holder7 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card11, parent, false);
                return new Holder7(view);
            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        r11.setAdapter(adapter1);
        adapter1.startListening();
        r11.setLayoutManager(linearLayoutManager);
        r11.setHasFixedSize(true);

    }
}