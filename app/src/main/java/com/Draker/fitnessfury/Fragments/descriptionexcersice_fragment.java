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

import com.Draker.fitnessfury.Activities.CheckEexcersiceActivity;
import com.Draker.fitnessfury.R;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class descriptionexcersice_fragment extends Fragment {
View v;
DatabaseReference databaseReference;
String type101,muscles,equip;
     ImageView videoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       v=inflater.inflate(R.layout.descriptionexcersice_fragment,container,false);
       return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            final TextView type = (TextView) view.findViewById(R.id.Type);
           final TextView Muscle = (TextView) view.findViewById(R.id.Muscle);
            final TextView equipment = (TextView) view.findViewById(R.id.Equipment);
            videoView = (ImageView) view.findViewById(R.id.videoView);
            // type.setText("hey no brown cow");
            CheckEexcersiceActivity checkEexcersiceActivity = (CheckEexcersiceActivity) getActivity();
            final String id1 = checkEexcersiceActivity.getid();
            final String type1 = checkEexcersiceActivity.gettype();
            Log.e("draker",type1);
            databaseReference = FirebaseDatabase.getInstance().getReference();
            final DatabaseReference excersise = databaseReference.child("exercises").child(type1).child(id1);
            excersise.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    for (DataSnapshot snap :dataSnapshot.getChildren()){
                        Map<String,String> post = (Map<String, String>) dataSnapshot.getValue();
                        Log.e("draker","hello");
                        String id = post.get("id");
//                        if (id1 == id){
                            type101 = post.get("Type");
                            muscles = post.get("Muscle");
                            equip = post.get("Equipments");
                            Log.e("Draker",type101);
                            String u= post.get("WorkoutVideo");
                   setImage(u);
                        type.setText(type101);
                        Muscle.setText(muscles);
                        equipment.setText(equip);
//                        }
                    }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });


        }




    }
    void setImage(String img){
        Glide.with(this).load(img).into(videoView);
    }


}
