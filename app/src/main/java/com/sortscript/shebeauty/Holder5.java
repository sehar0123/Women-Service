package com.sortscript.shebeauty;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder5 extends RecyclerView.ViewHolder {
    public TextView textb, textb2;
    public LinearLayout L1;
    public Button btn;


    public Holder5(@NonNull View View) {
        super(View);


        textb = View.findViewById(R.id.textbooking1);
        textb2 = View.findViewById(R.id.textbooking2);
        L1 = View.findViewById(R.id.book3);
        btn=View.findViewById(R.id.book7);



    }

}

