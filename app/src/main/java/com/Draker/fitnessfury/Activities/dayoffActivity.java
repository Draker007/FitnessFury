package com.Draker.fitnessfury.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.Draker.fitnessfury.R;

import static maes.tech.intentanim.CustomIntent.customType;


public class dayoffActivity extends AppCompatActivity {
    ConstraintLayout set, bmi, list, nutchart, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dayoff);
        set = (ConstraintLayout)findViewById(R.id.DOSets);
        list = (ConstraintLayout)findViewById(R.id.DOList);
        nutchart = (ConstraintLayout)findViewById(R.id.DOCharts);
        bmi = (ConstraintLayout)findViewById(R.id.DObmi);
        home = (ConstraintLayout)findViewById(R.id.DOhome);
        customType(dayoffActivity.this,"left-to-right");

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dayoffActivity.this,excersiceSetActivity.class));
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dayoffActivity.this,frontPageActivity.class));
            }
        });
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dayoffActivity.this, BMIActivity.class));
            }
        });
        nutchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dayoffActivity.this,nutritionActivity.class));
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dayoffActivity.this,MainActivity.class));
            }
        });
    }
}
