package com.sortscript.shebeauty.ui.Package;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sortscript.shebeauty.Activitydetails1;
import com.sortscript.shebeauty.Bookingdetails;
import com.sortscript.shebeauty.Holder;
import com.sortscript.shebeauty.Holder3;
import com.sortscript.shebeauty.Model;
import com.sortscript.shebeauty.Model1;
import com.sortscript.shebeauty.R;

public class PackageFragment extends Fragment {
    RecyclerView r6;
    DatabaseReference databaseReference3;
    FirebaseRecyclerOptions<Model1> options1;
    FirebaseRecyclerAdapter<Model1, Holder3> adapter1;

    private PackageViewModel packageViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        packageViewModel =
                new ViewModelProvider(this).get(PackageViewModel.class);


        View root = inflater.inflate(R.layout.fragment_packages, container, false);
        r6=root.findViewById(R.id.recyle7);

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

                Glide.with(getActivity()).load(model1.getImage()).into(holder3.image1);
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
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
//                Glide.with(getActivity()).load(model1.getImage()).into(holder3.image1);
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
//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
//        linearLayoutManager1.setOrientation(RecyclerView.VERTICAL);
//        r6.setAdapter(adapter1);
//        adapter1.startListening();
//        r6.setLayoutManager(linearLayoutManager);
//        r6.setHasFixedSize(true);


        return root;
    }
}