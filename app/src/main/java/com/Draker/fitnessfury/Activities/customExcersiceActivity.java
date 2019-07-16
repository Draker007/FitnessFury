package com.Draker.fitnessfury.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

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

import static maes.tech.intentanim.CustomIntent.customType;

public class customExcersiceActivity extends AppCompatActivity implements customExcersiceAdapter.onCustomExcersiceListner{

    RecyclerView recyclerView;
    private List<excersicesCustom> excersicesCustomList = new ArrayList<>();
    customExcersiceAdapter adapter;
    private DatabaseReference mDatabase;
    ProgressBar progressBar;

    ConstraintLayout set, bmi, list, nutchart, home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_excersice);
        customType(customExcersiceActivity.this,"left-to-right");
        progressBar=findViewById(R.id.CEProgress);
        set = (ConstraintLayout)findViewById(R.id.CuESets);
        list = (ConstraintLayout)findViewById(R.id.CuEList);
        nutchart = (ConstraintLayout)findViewById(R.id.CuECharts);
        bmi = (ConstraintLayout)findViewById(R.id.CuEbmi);
        home = (ConstraintLayout)findViewById(R.id.CuEhome);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(customExcersiceActivity.this,excersiceSetActivity.class));
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(customExcersiceActivity.this,frontPageActivity.class));
            }
        });
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(customExcersiceActivity.this, BMIActivity.class));
            }
        });
        nutchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(customExcersiceActivity.this,nutritionActivity.class));
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(customExcersiceActivity.this,MainActivity.class));
            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.customExcersiceRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new customExcersiceAdapter(excersicesCustomList,this);
        recyclerView.setAdapter(adapter);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        customExcersiceData();
    }
    private  void customExcersiceData(){
    final String sex = getIntent().getStringExtra("sex");
    DatabaseReference setData = mDatabase.child("ExcersiceSets").child(sex);
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
                progressBar.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    }

    @Override
    public void OnCustomExcersiceClick(int position) {
        final excersicesCustom model = excersicesCustomList.get(position);
        Intent intent = new Intent(this,ExcersiceSetDescActivity.class);
        intent.putExtra("name",model.getName());
        String sex = getIntent().getStringExtra("sex");
        intent.putExtra("sex",sex);

        startActivity(intent);
    }
}
