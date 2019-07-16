package com.Draker.fitnessfury.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.Draker.fitnessfury.DataClasses.NutritionList;
import com.Draker.fitnessfury.R;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

import static maes.tech.intentanim.CustomIntent.customType;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth mAuth;
    private ConstraintLayout allExcersice , myExcersice , nutrition , bmil;
  //  FirebaseAuth.AuthStateListener mAuthListner;
    ConstraintLayout set, bmi, list, nutchart, home;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.MAnav);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        bmil = (ConstraintLayout)findViewById(R.id.bmilay);

        customType(MainActivity.this,"left-to-right");

        mAuth = FirebaseAuth.getInstance();
        allExcersice = (ConstraintLayout) findViewById(R.id.AllWorkout);
        myExcersice = (ConstraintLayout) findViewById(R.id.CustomWokout);
        nutrition = (ConstraintLayout) findViewById(R.id.Nutrition);

        allExcersice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,frontPageActivity.class));
            }
        });
        bmil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,BMIActivity.class));
            }
        });
        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,nutritionActivity.class));
            }
        });

        myExcersice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,excersiceSetActivity.class);

                startActivity(intent);
            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finishAffinity();
            System.exit(0);
        }



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
            startActivity(new Intent(MainActivity.this,excersiceSetActivity.class));
        } else if (id == R.id.allw) {
            startActivity(new Intent(MainActivity.this,frontPageActivity.class));

        } else if (id == R.id.nutC) {
            startActivity(new Intent(MainActivity.this, NutritionList.class));
        } else if (id == R.id.bmiA) {
            startActivity(new Intent(MainActivity.this,BMIActivity.class));
        } else if (id == R.id.dietC) {
            startActivity(new Intent(MainActivity.this,DietCartListActivity.class));
        }
        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    }

