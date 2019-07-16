package com.Draker.fitnessfury.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.Draker.fitnessfury.Adapters.fpAdapter;
import com.Draker.fitnessfury.DataClasses.NutritionList;
import com.Draker.fitnessfury.DataClasses.fpDATA;
import com.Draker.fitnessfury.R;

import java.util.ArrayList;
import java.util.List;

import static maes.tech.intentanim.CustomIntent.customType;

public class frontPageActivity extends AppCompatActivity implements fpAdapter.OnFpListener, NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    List<fpDATA> fpDATAList = new ArrayList<>();
    fpAdapter adapter;
    fpDATA f1;
    ConstraintLayout set, bmi, list, nutchart, home,search;
    @Override
    public void onBackPressed() {
startActivity(new Intent(frontPageActivity.this,MainActivity.class));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
        customType(frontPageActivity.this,"left-to-right");
        initialize();
        loadData loadData = new loadData();
        loadData.execute();

    }
private class loadData extends AsyncTask{

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        runOnUiThread(()->{
            f1 = new fpDATA(R.drawable.biceps,"Biceps");
            fpDATAList.add(f1);
            f1 = new fpDATA(R.drawable.soulders,"Shoulders");
            fpDATAList.add(f1);
            f1 = new fpDATA(R.drawable.abs1,"Abs");
            fpDATAList.add(f1);
            f1 = new fpDATA(R.drawable.chest1,"Chest");
            fpDATAList.add(f1);
            f1 = new fpDATA(R.drawable.forearms,"Forearm");
            fpDATAList.add(f1);
            f1 = new fpDATA(R.drawable.triceps,"Tricep");
            fpDATAList.add(f1);
            f1 = new fpDATA(R.drawable.back,"Back");
            fpDATAList.add(f1);
            f1 = new fpDATA(R.drawable.legs,"Legs");
            fpDATAList.add(f1);
            f1 = new fpDATA(R.drawable.calves,"calves");
            fpDATAList.add(f1);

        });
        return null;
    }

}
    private void initialize(){


        recyclerView = (RecyclerView)findViewById(R.id.fpRecycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new fpAdapter(fpDATAList,this,this);
        recyclerView.setAdapter(adapter);
        Toolbar toolbar = findViewById(R.id.FPtoolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.FPdrawer);
        NavigationView navigationView = findViewById(R.id.FPnav);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }




    @Override
    public void onFPclick(int position) {
        final fpDATA model = fpDATAList.get(position);
        Intent intent =new Intent(frontPageActivity.this,excersiceListActivity.class);
        String id = model.getName();

        intent.putExtra("type",id);
        startActivity(intent);
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
            startActivity(new Intent(frontPageActivity.this,excersiceSetActivity.class));
        } else if (id == R.id.allw) {
            startActivity(new Intent(frontPageActivity.this,frontPageActivity.class));

        } else if (id == R.id.nutC) {
            startActivity(new Intent(frontPageActivity.this, NutritionList.class));
        } else if (id == R.id.bmiA) {
            startActivity(new Intent(frontPageActivity.this,BMIActivity.class));
        } else if (id == R.id.dietC) {
            startActivity(new Intent(frontPageActivity.this,DietCartListActivity.class));
        }
        DrawerLayout drawer = findViewById(R.id.FPdrawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
