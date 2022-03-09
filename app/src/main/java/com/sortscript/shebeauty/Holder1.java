package com.sortscript.shebeauty;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder1 extends RecyclerView.ViewHolder {
    public TextView text1, text2, text3;

    public Button b1, b2;
    View view;

    public Holder1(@NonNull View itemView) {
        super(itemView);

        view = itemView;

        text1 = itemView.findViewById(R.id.Text13);
        text2 = itemView.findViewById(R.id.Text14);
        text3 = itemView.findViewById(R.id.Text15);

        b1 = itemView.findViewById(R.id.buttton1);
        b2 = itemView.findViewById(R.id.buttton2);


    }

}
