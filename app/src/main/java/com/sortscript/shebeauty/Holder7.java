package com.sortscript.shebeauty;

import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder7 extends RecyclerView.ViewHolder {
    public TextView text1b, textb12, textb13;
    public Button A1;


    public Holder7(@NonNull View itemView) {
        super(itemView);


        text1b = itemView.findViewById(R.id.Text23);
        textb12 = itemView.findViewById(R.id.Text24);
        textb13 = itemView.findViewById(R.id.Text25);
        A1 = itemView.findViewById(R.id.buttton26);


    }
}
