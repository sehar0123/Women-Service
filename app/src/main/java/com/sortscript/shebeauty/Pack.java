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

public class Pack extends AppCompatActivity {
    RecyclerView r6;
    DatabaseReference databaseReference3;
    FirebaseRecyclerOptions<Model1> options1;
    FirebaseRecyclerAdapter<Model1, Holder3> adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack);
        r6=findViewById(R.id.recyle1);

        databaseReference3 = FirebaseDatabase.getInstance().getReference().child("Packages");
        options1 = new FirebaseRecyclerOptions.Builder<Model1>().setQuery(databaseReference3, Model1.class)
                .build();
        adapter1 = new FirebaseRecyclerAdapter<Model1, Holder3>(options1) {
            @Override
            protected void onBindViewHolder(@NonNull Holder3 holder3, int position, @NonNull Model1 model1) {
                String PhoneNumber2 = model1.getPhoneNumber();
                String Name2 = model1.getName();
                String Image2 = model1.getImage();
                String Address2 = model1.getAddress();
                String Description2 = model1.getDescription();

                holder3.text11.setText(Name2);

                holder3.text13.setText(Address2);
                holder3.text14.setText(Description2);

                Glide.with(getApplicationContext()).load(model1.getImage()).into(holder3.image1);
                holder3.btnpkg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String num = model1.getPhoneNumber();

                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", num, null)));

                    }
                });


            }

            @NonNull
            @Override
            public Holder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card7, parent, false);
                return new Holder3(view);
            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        r6.setAdapter(adapter1);
        adapter1.startListening();
        r6.setLayoutManager(linearLayoutManager);
        r6.setHasFixedSize(true);
//        databaseReference3 = FirebaseDatabase.getInstance().getReference().child("Packages");
//        options1 = new FirebaseRecyclerOptions.Builder<Model1>().setQuery(databaseReference3, Model1.class)
//                .build();
//        adapter1 = new FirebaseRecyclerAdapter<Model1, Holder3>(options1) {
//            @Override
//            protected void onBindViewHolder(@NonNull Holder3 holder3, int position, @NonNull Model1 model1) {
//                String PhoneNumber2 = model1.getPhoneNumber();
//                String Name2 = model1.getName();
//                String Image2 = model1.getImage();
//                String Address2 = model1.getAddress();
//                String Description2 = model1.getDescription();
//
//                holder3.text11.setText(Name2);
//
//                holder3.text13.setText(Address2);
//                holder3.text14.setText(Description2);
//
//                Glide.with(getApplicationContext()).load(model1.getImage()).into(holder3.image1);
//                holder3.btnpkg.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String num = model1.getPhoneNumber();
//
//                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", num, null)));
//
//                    }
//                });
//
//
//            }
//
//            @NonNull
//            @Override
//            public Holder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card7, parent, false);
//                return new Holder3(view);
//            }
//        };
//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext());
//        linearLayoutManager1.setOrientation(RecyclerView.VERTICAL);
//        r6.setAdapter(adapter1);
//        adapter1.startListening();
//        r6.setLayoutManager(linearLayoutManager);
//        r6.setHasFixedSize(true);

}
}