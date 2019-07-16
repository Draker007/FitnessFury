package com.Draker.fitnessfury.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;

import com.Draker.fitnessfury.Adapters.checkExcersiceAdapter;
import com.Draker.fitnessfury.DataClasses.NutritionList;
import com.Draker.fitnessfury.Fragments.FemaleFragment;
import com.Draker.fitnessfury.Fragments.MaleFragment;
import com.Draker.fitnessfury.R;

import static maes.tech.intentanim.CustomIntent.customType;

public class excersiceSetActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout estab;
    private ViewPager esview;
    private checkExcersiceAdapter adapter;
    ConstraintLayout set, bmi, list, nutchart, home;
    @Override
    public void onBackPressed() {
        startActivity(new Intent(excersiceSetActivity.this,MainActivity.class));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excersice_set);

        customType(excersiceSetActivity.this,"left-to-right");
        estab = findViewById(R.id.EStab);
        esview=findViewById(R.id.ESview);
        adapter=new checkExcersiceAdapter(getSupportFragmentManager());
        adapter.AddFragment(new MaleFragment(),"MALE");
        adapter.AddFragment(new FemaleFragment(),"Female");

        esview.setAdapter(adapter);
        estab.setupWithViewPager(esview);
        Toolbar toolbar = findViewById(R.id.EStoolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.ESdrawer);
        NavigationView navigationView = findViewById(R.id.ESnav);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

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
            startActivity(new Intent(excersiceSetActivity.this,excersiceSetActivity.class));
        } else if (id == R.id.allw) {
            startActivity(new Intent(excersiceSetActivity.this,frontPageActivity.class));

        } else if (id == R.id.nutC) {
            startActivity(new Intent(excersiceSetActivity.this, NutritionList.class));
        } else if (id == R.id.bmiA) {
            startActivity(new Intent(excersiceSetActivity.this,BMIActivity.class));
        } else if (id == R.id.dietC) {
            startActivity(new Intent(excersiceSetActivity.this,DietCartListActivity.class));
        }
        DrawerLayout drawer = findViewById(R.id.ESdrawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    }

