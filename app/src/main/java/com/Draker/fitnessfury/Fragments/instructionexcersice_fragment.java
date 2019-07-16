package com.Draker.fitnessfury.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.Draker.fitnessfury.Activities.CheckEexcersiceActivity;
import com.Draker.fitnessfury.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class instructionexcersice_fragment extends Fragment {
    View v;
    DatabaseReference databaseReference;
     String inst10;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.instructionexcersice_fragment,container,false);
        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        View v = getView();
        if (v != null){
        final AutoCompleteTextView Instruction = (AutoCompleteTextView) v.findViewById(R.id.Instruction101);
            CheckEexcersiceActivity checkEexcersiceActivity = (CheckEexcersiceActivity) getActivity();
            final String id1 = checkEexcersiceActivity.getid();
            final String type1 = checkEexcersiceActivity.gettype();
            Log.e("draker",type1);
            databaseReference = FirebaseDatabase.getInstance().getReference();
            final DatabaseReference excersise = databaseReference.child("exercises").child(type1).child(id1);
            excersise.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Map<String,String> post = (Map<String, String>)dataSnapshot.getValue();
                    inst10 = post.get("WorkoutDesc");
                    Log.e("ladua", String.valueOf(inst10.length()));
                    Instruction.setText(inst10);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


    }}
}
