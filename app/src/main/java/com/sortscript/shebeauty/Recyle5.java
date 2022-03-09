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

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Recyle5 extends AppCompatActivity {

    RecyclerView r3;
    Spinner spinner;
    String category;
    DatabaseReference databaseReference2;
    FirebaseRecyclerOptions<Model> options;
    FirebaseRecyclerAdapter<Model, Holder1> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyle5);
        r3 = findViewById(R.id.recyle5);
        spinner = findViewById(R.id.spinnerId);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(Recyle5.this,
                R.array.category, android.R.layout.simple_list_item_activated_1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        databaseReference2 = FirebaseDatabase.getInstance().getReference().child(categoryLink);


        options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(databaseReference2, Model.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<Model, Holder1>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Holder1 holder1, int position, @NonNull Model model) {
                String PhoneNumber = model.getPhoneNumber();
                String Name = model.getName();

                String Address = model.getAddress();

                holder1.text1.setText(Name);
                holder1.text2.setText(PhoneNumber);
                holder1.text3.setText(Address);


                holder1.b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        databaseReference2.child(getRef(position).getKey()).removeValue()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Recyle5.this, "Deleted", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(Recyle5.this, task.toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });

                holder1.b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Foam2.class);
                        intent.putExtra("category", categoryLink);
                        intent.putExtra("key", getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }


            @NonNull
            @Override
            public Holder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card4, parent, false);
                return new Holder1(view);
            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        r3.setAdapter(adapter);
        adapter.startListening();
        r3.setLayoutManager(linearLayoutManager);
        r3.setHasFixedSize(true);

    }
}