package com.Draker.fitnessfury.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.Draker.fitnessfury.Adapters.WeekAdapter;
import com.Draker.fitnessfury.DataClasses.weekNo;
import com.Draker.fitnessfury.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static maes.tech.intentanim.CustomIntent.customType;

public class myWorkout extends AppCompatActivity implements WeekAdapter.onWeekListner {
    RecyclerView recyclerView;
    private List<weekNo> weekNos = new ArrayList<>();
    WeekAdapter adapter;
    private DatabaseReference mDatabase;
    ProgressBar progressBar;
    ConstraintLayout set, bmi, list, nutchart, home,search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workout);
        customType(myWorkout.this,"left-to-right");
        recyclerView = (RecyclerView)findViewById(R.id.weekRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new WeekAdapter(weekNos,this);
        recyclerView.setAdapter(adapter);
        progressBar = findViewById(R.id.EMProgress);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        progressBar.setVisibility(View.INVISIBLE);
        set = (ConstraintLayout)findViewById(R.id.mWSets);
        list = (ConstraintLayout)findViewById(R.id.mWList);
        nutchart = (ConstraintLayout)findViewById(R.id.mWCharts);
        bmi = (ConstraintLayout)findViewById(R.id.mWbmi);
        home = (ConstraintLayout)findViewById(R.id.mWhome);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(myWorkout.this,excersiceSetActivity.class));
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(myWorkout.this,frontPageActivity.class));
            }
        });
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(myWorkout.this, BMIActivity.class));
            }
        });
        nutchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(myWorkout.this,nutritionActivity.class));
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(myWorkout.this,MainActivity.class));
            }
        });

        weekData();


    }

    private void weekData(){}

    @Override
    public void OnWeekClick(int position) {


    }
}
