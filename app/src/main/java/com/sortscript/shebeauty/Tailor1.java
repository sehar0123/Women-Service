package com.sortscript.shebeauty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tailor1 extends AppCompatActivity {
    RecyclerView r1;
    DatabaseReference databaseReference1;
    FirebaseRecyclerOptions<Model> options;
    FirebaseRecyclerAdapter<Model, Holder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tailor1);
        r1 = findViewById(R.id.recyle4);

        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Tailor");
        options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(databaseReference1, Model.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<Model, Holder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull Model model) {
                String PhoneNumber = model.getPhoneNumber();
                String Name = model.getName();
                String Image = model.getImage();
                String Address = model.getAddress();

                holder.textView1.setText(Name);
                holder.textView3.setText(Address);
                holder.phoneBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String num = model.getPhoneNumber();

                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", num, null)));

                    }
                });
                holder.bookingbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Bookingdetails.class);
                        intent.putExtra("name", "Tailor");
                        startActivity(intent);
                    }
                });


                Glide.with(getApplicationContext()).load(Image).into(holder.imageView);

                holder.itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(getApplicationContext(), Activitydetails1.class);
                    intent.putExtra("category", "Tailor");
                    intent.putExtra("refKey", getRef(position).getKey());
                    getApplicationContext().startActivity(intent);
                });


            }

            @NonNull
            @Override
            public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card1, parent, false);
                return new Holder(view);
            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        r1.setAdapter(adapter);
        adapter.startListening();
        r1.setLayoutManager(linearLayoutManager);
        r1.setHasFixedSize(true);

    }
}