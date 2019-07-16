package com.Draker.fitnessfury.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Draker.fitnessfury.DataClasses.NutritionList;
import com.Draker.fitnessfury.R;

import static maes.tech.intentanim.CustomIntent.customType;

public class BMIActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private  TextView weight ,height , setbmi , category ;
    Button btnBmi;
    ConstraintLayout set, bmi, list, nutchart, home;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    @Override
    public void onBackPressed() {
        startActivity(new Intent(BMIActivity.this,MainActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        weight = (TextView)findViewById(R.id.bmiWeight);
        setbmi = (TextView)findViewById(R.id.bmi);
        height= (TextView)findViewById(R.id.bmiHeight);
        btnBmi=(Button)findViewById(R.id.BMIbtn);

        category = (TextView)findViewById(R.id.weightBMI) ;
        Toolbar toolbar = findViewById(R.id.BMItoolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.BMIdrawer);
        NavigationView navigationView = findViewById(R.id.BMInav);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        customType(BMIActivity.this,"left-to-right");
        btnBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String W = weight.getText().toString();
                String H = height.getText().toString();
                height.onEditorAction(EditorInfo.IME_ACTION_DONE);

                if (W.isEmpty() || H.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter the details",Toast.LENGTH_SHORT).show();
                }
                else {
                    Float w = Float.parseFloat(W);
                    Float h = Float.parseFloat(H);
                    Float a = (w / (h * h)) * 10000;
                    setbmi.setText(a.toString());
                    if(a<18.5){
                        category.setText("Underweight");
                    }else if (a<24.9)
                        category.setText("Normal");
                    else if(a<29.9)
                        category.setText("Overweight");
                    else
                        category.setText("Obesity");

                }
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.e("123", "onNavigationItemSelected: " );
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.wplan) {
            startActivity(new Intent(BMIActivity.this,excersiceSetActivity.class));
        } else if (id == R.id.allw) {
            startActivity(new Intent(BMIActivity.this,frontPageActivity.class));

        } else if (id == R.id.nutC) {
            startActivity(new Intent(BMIActivity.this, NutritionList.class));
        } else if (id == R.id.bmiA) {
            startActivity(new Intent(BMIActivity.this,BMIActivity.class));
        } else if (id == R.id.dietC) {
            startActivity(new Intent(BMIActivity.this,DietCartListActivity.class));
        }
        DrawerLayout drawer = findViewById(R.id.BMIdrawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

