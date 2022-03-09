package com.sortscript.shebeauty.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.sortscript.shebeauty.Jym;
import com.sortscript.shebeauty.Pack;
import com.sortscript.shebeauty.R;
import com.sortscript.shebeauty.Salon2;
import com.sortscript.shebeauty.Tailor1;

public class HomeFragment extends Fragment {

ViewFlipper viewFlipper;
    CardView cardView, cardView1, cardView2, cardView3;
//    RecyclerView r6;
//    DatabaseReference databaseReference3;
//    FirebaseRecyclerOptions<Model1> options1;
//    FirebaseRecyclerAdapter<Model1, Holder3> adapter1;






    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        cardView3 = root.findViewById(R.id.fitness1);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), Jym.class));
            }
        });

        cardView2 = root.findViewById(R.id.salon2);

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Salon2.class);

                startActivity(intent);


            }
        });
        cardView1 = root.findViewById(R.id.pack2);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), Pack.class);

                startActivity(intent);


            }
        });
        cardView = root.findViewById(R.id.swing1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Tailor1.class);

                startActivity(intent);


            }


        });


        int images[] = {R.drawable.talior1, R.drawable.gym3, R.drawable.salon2, R.drawable.tailor2};

        viewFlipper = root.findViewById(R.id.flipper);
        for (int image : images) {
            flipperImage(image);
        }


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
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        r6.setAdapter(adapter1);
//        adapter1.startListening();
//        r6.setLayoutManager(linearLayoutManager);
//        r6.setHasFixedSize(true);
//



        return root;


    }


    public void flipperImage(int image) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);


    }


}
