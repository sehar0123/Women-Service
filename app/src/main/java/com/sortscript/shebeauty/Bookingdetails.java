package com.sortscript.shebeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Bookingdetails extends AppCompatActivity {
    RecyclerView r11;
    DatabaseReference databaseReference7;
    FirebaseRecyclerOptions<Model4> options2;
    FirebaseRecyclerAdapter<Model4, Holder5> adapter;
    String cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingdetails);
        r11 = findViewById(R.id.recyle12);

        cat = getIntent().getStringExtra("name");
        databaseReference7 = FirebaseDatabase.getInstance().getReference().child(cat).child("Booking");
        options2 = new FirebaseRecyclerOptions.Builder<Model4>().setQuery(databaseReference7, Model4.class).build();
        adapter = new FirebaseRecyclerAdapter<Model4, Holder5>(options2) {
            @Override
            protected void onBindViewHolder(@NonNull Holder5 holder5, int position, @NonNull Model4 model4) {

                String Date = model4.getDate();
                String Time = model4.getTime();
                String Title=model4.getTitle();

                if (model4.getTitle().equals("Booked")) {
                    holder5.L1.setBackgroundColor(getResources().getColor(R.color.WhiteSmoke));

                }
                 else if(model4.getTitle().equals("Pending")) {
                    holder5.L1.setBackgroundColor(getResources().getColor(R.color.teal_200));
                }
                holder5.textb2.setText(Time);
                holder5.textb.setText(Date);
                holder5.btn.setText(Title);


              holder5.btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (model4.getTitle().equals("Booked")){
                            Toast.makeText(Bookingdetails.this, "Already Book", Toast.LENGTH_SHORT).show();
                        }
                       else if (model4.getTitle().equals("Pending")){
                            Toast.makeText(Bookingdetails.this, " Appointment is pending", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent intent = new Intent(Bookingdetails.this, Availablefoam.class);
                            intent.putExtra("name",cat);

                            intent.putExtra("title",Title);

                            intent.putExtra("refKey", getRef(position).getKey());

                            startActivity(intent);

                        }

                    }
                });

            }

            @NonNull
            @Override
            public Holder5 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card10, parent, false);
                return new Holder5(view);
            }
        };
       GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
//        gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        r11.setAdapter(adapter);
        adapter.startListening();
        r11.setLayoutManager(gridLayoutManager);



    }
}
