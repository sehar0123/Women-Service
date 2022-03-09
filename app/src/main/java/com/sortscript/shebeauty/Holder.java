package com.sortscript.shebeauty;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {
    public TextView textView1, textView3;
    public ImageView imageView;
    public Button phoneBtn,bookingbutton;


    public Holder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image3);
        textView1 = itemView.findViewById(R.id.T1);
        phoneBtn = itemView.findViewById(R.id.phoneBtnId);
        textView3 = itemView.findViewById(R.id.T3);
        bookingbutton=itemView.findViewById(R.id.bookingbtn);
    }

}
