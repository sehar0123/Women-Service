package com.sortscript.shebeauty.ui.saloon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sortscript.shebeauty.Activitydetails1;
import com.sortscript.shebeauty.BookingView;
import com.sortscript.shebeauty.Bookingdetails;
import com.sortscript.shebeauty.Holder;
import com.sortscript.shebeauty.Model;
import com.sortscript.shebeauty.R;

public class SaloonFragment extends Fragment {
    RecyclerView r1;
    DatabaseReference databaseReference1;
    FirebaseRecyclerOptions<Model> options;
    FirebaseRecyclerAdapter<Model, Holder> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        r1 = root.findViewById(R.id.recyle3);

        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Salon");
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
                        Intent intent = new Intent(getActivity(), Bookingdetails.class);
                        intent.putExtra("name", "Salon");
                        startActivity(intent);
                    }
                });


                Glide.with(getActivity()).load(Image).into(holder.imageView);

                holder.itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(getActivity(), Activitydetails1.class);
                    intent.putExtra("category", "Salon");
                    intent.putExtra("refKey", getRef(position).getKey());
                    getActivity().startActivity(intent);
                });


            }

            @NonNull
            @Override
            public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card1, parent, false);
                return new Holder(view);
            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        r1.setAdapter(adapter);
        adapter.startListening();
        r1.setLayoutManager(linearLayoutManager);
        r1.setHasFixedSize(true);
        return root;
    }
}