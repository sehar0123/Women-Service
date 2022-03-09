package com.sortscript.shebeauty.ui.Feedback;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sortscript.shebeauty.R;

import java.util.HashMap;
import java.util.Map;

public class FeedbackFragment extends Fragment {
    EditText et11, nt12, ft13;
    Button bt11fed;
    DatabaseReference reference, ref2;
    FirebaseAuth firebaseAuth;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_feedback, container, false);
        et11 = root.findViewById(R.id.feedname);

        nt12 = root.findViewById(R.id.feedemail);
        ft13 = root.findViewById(R.id.feedback);
        bt11fed = root.findViewById(R.id.feedbutton);
        firebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("User");
        ref2 = FirebaseDatabase.getInstance().getReference().child("Feedback");

        String id = firebaseAuth.getCurrentUser().getUid();


        reference.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    String a = snapshot.child("Username").getValue().toString();
                    String b = snapshot.child("Email").getValue().toString();


                    nt12.setText(a);
                    et11.setText(b);

                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        bt11fed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a = nt12.getText().toString();
                String b = et11.getText().toString();
                String c = ft13.getText().toString();

                Map<String, Object> map = new HashMap<>();
                map.put("UserName", a);
                map.put("Email", b);
                map.put("Feedback", c);

                ref2.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Feedback send", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), task.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });


















        return root;
    }



}