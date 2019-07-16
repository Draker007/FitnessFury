package com.Draker.fitnessfury.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.Draker.fitnessfury.Adapters.NutritionAdapter;
import com.Draker.fitnessfury.DataClasses.NutritionList;
import com.Draker.fitnessfury.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static maes.tech.intentanim.CustomIntent.customType;

public class nutritionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NutritionAdapter adapter;
    ConstraintLayout set, bmi, list, nutchart, home;
    private List<NutritionList> nutritionLists = new ArrayList<>();
    private DatabaseReference mDatabase;
    @Override
    public void onBackPressed() {
        startActivity(new Intent(nutritionActivity.this,MainActivity.class));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        customType(nutritionActivity.this,"left-to-right");
        set = (ConstraintLayout)findViewById(R.id.NSets);
        list = (ConstraintLayout)findViewById(R.id.NList);
        nutchart = (ConstraintLayout)findViewById(R.id.NCharts);
        bmi = (ConstraintLayout)findViewById(R.id.Nbmi);
        home = (ConstraintLayout)findViewById(R.id.Nhome);

        recyclerView = (RecyclerView)findViewById(R.id.nutritionRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NutritionAdapter(nutritionLists);
        recyclerView.setAdapter(adapter);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        NutritionData();

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(nutritionActivity.this,excersiceSetActivity.class));
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(nutritionActivity.this,frontPageActivity.class));
            }
        });
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(nutritionActivity.this, BMIActivity.class));
            }
        });
        nutchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(nutritionActivity.this,nutritionActivity.class));
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(nutritionActivity.this,MainActivity.class));
            }
        });

    }
    private void NutritionData(){
        final DatabaseReference databaseReference = mDatabase.child("Nutrition");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot db : dataSnapshot.getChildren()){
                    Map<String,String> map = (Map<String, String>) db.getValue();
                    String name = db.getKey();
                    String cal = map.get("Calories");
                    String protien = map.get("Protein");
                    String carbs = map.get("carbs");
                    String fat = map.get("fat");
                    NutritionList nutritionList = new NutritionList(name,protien,fat,carbs);
                    nutritionLists.add(nutritionList);
                    recyclerView.setAdapter(adapter);
                }


        }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }});


    }
}
