package com.Draker.fitnessfury.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.Draker.fitnessfury.Activities.ExcersiceMain;
import com.Draker.fitnessfury.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Em2ndFragment extends Fragment {
    View v;
    AutoCompleteTextView inst,caution;
    DatabaseReference mdatabase;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.em2ndfragment,container,false);
        inst = (AutoCompleteTextView)rootview.findViewById(R.id.emInst);
        caution = (AutoCompleteTextView)rootview.findViewById(R.id.emCaution);
        return rootview;
    }

    @Override
    public void onStart() {
        super.onStart();
        mdatabase = FirebaseDatabase.getInstance().getReference();
        ExcersiceMain excersiceMain =(ExcersiceMain)getActivity();
        String sex =  excersiceMain.getSex();
        Log.e("Draker", "onStart: "+sex );
        final String name =  excersiceMain.getname();
        final String id =  excersiceMain.getid();
        final String day = excersiceMain.getday();

        DatabaseReference databaseReference = mdatabase.child("ExcersiceSets").child(sex).child(name).child(day).child(id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String,String> map = (Map<String, String>)dataSnapshot.getValue();
                String instr = map.get("Instruction");
                String caut = map.get("Caution");
                inst.setText(instr);
                caution.setText(caut);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
