package com.sortscript.shebeauty;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder3 extends RecyclerView.ViewHolder {
    public TextView text11, text12, text13, text14;
    public ImageView image1;
    public ImageButton btnpkg;


    public Holder3(@NonNull View View) {
        super(View);


        image1 = View.findViewById(R.id.imagelogo1);
        text11 = View.findViewById(R.id.textpkg);
        btnpkg = View.findViewById(R.id.textpkg1);
        text13 = View.findViewById(R.id.textpkg2);
        text14 = View.findViewById(R.id.textpkg3);


    }

}
