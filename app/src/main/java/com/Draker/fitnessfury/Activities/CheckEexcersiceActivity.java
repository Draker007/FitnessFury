package com.Draker.fitnessfury.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.TextView;

import com.Draker.fitnessfury.Adapters.checkExcersiceAdapter;
import com.Draker.fitnessfury.DataClasses.NutritionList;
import com.Draker.fitnessfury.Fragments.descriptionexcersice_fragment;
import com.Draker.fitnessfury.Fragments.instructionexcersice_fragment;
import com.Draker.fitnessfury.R;
import com.google.firebase.database.DatabaseReference;

public class CheckEexcersiceActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private checkExcersiceAdapter adapter;
    TextView excersiceName,type;
    DatabaseReference databaseReference;
    ConstraintLayout set, bmi, list, nutchart, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_eexcersice);
        Toolbar toolbar = findViewById(R.id.CEEtoolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.CEEdrawer);
        NavigationView navigationView = findViewById(R.id.CEEnav);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        Initialize();

        fragments();
        fragmentdata();
    }
    public String getid(){
        String id = getIntent().getStringExtra("id");
        return id;
    }
    public String gettype(){
        String type = getIntent().getStringExtra("type");
        return type;
    }

    public void Initialize(){
         tabLayout = (TabLayout)findViewById(R.id.checkExcersiceTabLayout);
         viewPager=(ViewPager)findViewById(R.id.checkExcersiceViewer);
         excersiceName=(TextView)findViewById(R.id.excersiceName101);
         adapter = new checkExcersiceAdapter(getSupportFragmentManager());
       //  type = (TextView)findViewById(R.id.Type);
        //    type.setText("yoyoyo");

        String name = getIntent().getStringExtra("name");
        excersiceName.setText(name);
    }





    public void fragments(){
    adapter.AddFragment(new descriptionexcersice_fragment(),"Description");
    adapter.AddFragment(new instructionexcersice_fragment(),"Instructions");
    viewPager.setAdapter(adapter);
    tabLayout.setupWithViewPager(viewPager);

    }

    private void fragmentdata(){

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
            startActivity(new Intent(CheckEexcersiceActivity.this,excersiceSetActivity.class));
        } else if (id == R.id.allw) {
            startActivity(new Intent(CheckEexcersiceActivity.this,frontPageActivity.class));

        } else if (id == R.id.nutC) {
            startActivity(new Intent(CheckEexcersiceActivity.this, NutritionList.class));
        } else if (id == R.id.bmiA) {
            startActivity(new Intent(CheckEexcersiceActivity.this,BMIActivity.class));
        } else if (id == R.id.dietC) {
            startActivity(new Intent(CheckEexcersiceActivity.this,DietCartListActivity.class));
        }
        DrawerLayout drawer = findViewById(R.id.CEEdrawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
