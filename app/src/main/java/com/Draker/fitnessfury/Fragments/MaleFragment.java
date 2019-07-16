package com.Draker.fitnessfury.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.Draker.fitnessfury.Activities.ExcersiceSetDescActivity;
import com.Draker.fitnessfury.Adapters.customExcersiceAdapter;
import com.Draker.fitnessfury.DataClasses.excersicesCustom;
import com.Draker.fitnessfury.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MaleFragment extends Fragment implements customExcersiceAdapter.onCustomExcersiceListner {
    RecyclerView recyclerView;
    private List<excersicesCustom> excersicesCustomList = new ArrayList<>();
    customExcersiceAdapter adapter;
    private DatabaseReference mDatabase;
    ProgressBar progressBar;
    View v;
    public MaleFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.male_fragment,container,false);
        recyclerView = (RecyclerView)v.findViewById(R.id.maleRecycler);
      final  RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        adapter = new customExcersiceAdapter(excersicesCustomList,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference setData = mDatabase.child("ExcersiceSets").child("Men");
        setData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Map<String,String> postMap = (Map<String, String>)snapshot.getValue();
                    String name = postMap.get("name");
                    String image = postMap.get("image");
                    excersicesCustom excersicesCustom = new excersicesCustom(name,image);
                    excersicesCustomList.add(excersicesCustom);
                    recyclerView.setAdapter(adapter);
//                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return v;
    }

    @Override
    public void OnCustomExcersiceClick(int position) {
        final excersicesCustom model = excersicesCustomList.get(position);
        Intent intent = new Intent(getActivity(), ExcersiceSetDescActivity.class);
        intent.putExtra("name",model.getName());
                intent.putExtra("sex","Men");

        startActivity(intent);
    }
}
