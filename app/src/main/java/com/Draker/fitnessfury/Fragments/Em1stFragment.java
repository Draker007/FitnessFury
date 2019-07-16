package com.Draker.fitnessfury.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Draker.fitnessfury.Activities.ExcersiceMain;
import com.Draker.fitnessfury.R;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Em1stFragment extends Fragment {
    View v;
    ImageView imageView;
    TextView type,rep,set,main,other,equip;
    DatabaseReference mDatabase;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.em1stfragment, container, false);
        imageView = rootView.findViewById(R.id.emImage);

        type = (TextView) rootView.findViewById(R.id.emType);
        rep = (TextView)rootView.findViewById(R.id.emReps);
        set = (TextView)rootView.findViewById(R.id.emSets);
        main = (TextView)rootView.findViewById(R.id.emMainmus);
        other = (TextView)rootView.findViewById(R.id.emOthermus);
        equip=(TextView)rootView.findViewById(R.id.emEquip);

        mDatabase = FirebaseDatabase.getInstance().getReference();



        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        ExcersiceMain excersiceMain =(ExcersiceMain)getActivity();
       String sex =  excersiceMain.getSex();
        Log.e("Draker", "onStart: "+sex );
        final String name =  excersiceMain.getname();
        final String id =  excersiceMain.getid();
        final String day = excersiceMain.getday();


        DatabaseReference databaseReference = mDatabase.child("ExcersiceSets").child(sex).child(name).child(day).child(id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String ,String > map = (Map<String, String>)dataSnapshot.getValue();
                String getvideo = map.get("video");
                String getEquip = map.get("Equipment");
                String getSet = map.get("sets");
                String getRep = map.get("reps");
                String getType = map.get("Type");
                String mainM = map.get("Main Muscle Worked");
                String otherM = map.get("Other Muscle");


                type.setText(getType);
                equip.setText(getEquip);
                set.setText(getSet);
                rep.setText(getRep);
                main.setText(mainM);
                other.setText(otherM);
                setgif(getvideo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

   }
   public void setgif(String url){

       Glide.with(this).load(url).into(imageView);

   }
}
