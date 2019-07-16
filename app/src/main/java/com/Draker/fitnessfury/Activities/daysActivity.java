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

import com.Draker.fitnessfury.DataClasses.NutritionList;
import com.Draker.fitnessfury.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import static maes.tech.intentanim.CustomIntent.customType;

public class daysActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ConstraintLayout sun,mon,tue,wed,thurs,fri,sat;
    DatabaseReference mDatabase;
    String TAG = "Draker";
    String id1 = "false";

    ConstraintLayout set, bmi, list, nutchart, home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);
        customType(daysActivity.this,"left-to-right");
        sun=(ConstraintLayout)findViewById(R.id.Sunday);
        mon=(ConstraintLayout)findViewById(R.id.Monday);
        tue=(ConstraintLayout)findViewById(R.id.Tuesday);
        wed=(ConstraintLayout)findViewById(R.id.Wednesday);
        thurs=(ConstraintLayout)findViewById(R.id.Thursday);
        fri = (ConstraintLayout)findViewById(R.id.Friday);
        sat=(ConstraintLayout)findViewById(R.id.Saturday);
        Toolbar toolbar = findViewById(R.id.Dtoolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.Ddrawer);
        NavigationView navigationView = findViewById(R.id.Dnav);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        final String name = getIntent().getStringExtra("name");
        final String sex = getIntent().getStringExtra("sex");
        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference db = mDatabase.child("ExcersiceSets").child(sex).child(name).child("Sunday");
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Map<String,String> post = (Map<String, String>)dataSnapshot.getValue();
                        String id = post.get("id");
                        Log.e(TAG, "onDataChange:"+id);

                        if (id .equals(id1)){
                            Log.e(TAG, "onDataChange: onPoint");
                            startActivity(new Intent(daysActivity.this, dayoffActivity.class));
                        }else{
                            Intent intent = new Intent(daysActivity.this, dayExcersiceSet.class);
                            intent.putExtra("sex",sex);
                            intent.putExtra("name",name);
                            intent.putExtra("day","Monday");
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference db = mDatabase.child("ExcersiceSets").child(sex).child(name).child("Monday");
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Map<String,String> post = (Map<String, String>)dataSnapshot.getValue();
                        String id = post.get("id");
                        Log.e(TAG, "onDataChange:"+id);

                        if (id .equals(id1)){
                            Log.e(TAG, "onDataChange: onPoint");
                            startActivity(new Intent(daysActivity.this, dayoffActivity.class));
                        }else{
                            Intent intent = new Intent(daysActivity.this, dayExcersiceSet.class);
                            intent.putExtra("sex",sex);
                            intent.putExtra("name",name);
                            intent.putExtra("day","Monday");
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference db = mDatabase.child("ExcersiceSets").child(sex).child(name).child("Tuesday");
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Map<String,String> post = (Map<String, String>)dataSnapshot.getValue();
                        String id = post.get("id");
                        Log.e(TAG, "onDataChange:"+id);

                        if (id .equals(id1)){
                            Log.e(TAG, "onDataChange: onPoint");
                            startActivity(new Intent(daysActivity.this, dayoffActivity.class));
                        }else{
                            Intent intent = new Intent(daysActivity.this, dayExcersiceSet.class);
                            intent.putExtra("sex",sex);
                            intent.putExtra("name",name);
                            intent.putExtra("day","Tuesday");
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference db = mDatabase.child("ExcersiceSets").child(sex).child(name).child("Wednesday");
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Map<String,String> post = (Map<String, String>)dataSnapshot.getValue();
                        String id = post.get("id");
                        Log.e(TAG, "onDataChange:"+id);

                        if (id .equals(id1)){
                            Log.e(TAG, "onDataChange: onPoint");
                            startActivity(new Intent(daysActivity.this, dayoffActivity.class));
                        }else{
                            Intent intent = new Intent(daysActivity.this, dayExcersiceSet.class);
                            intent.putExtra("sex",sex);
                            intent.putExtra("name",name);
                            intent.putExtra("day","Wednesday");
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        thurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference db = mDatabase.child("ExcersiceSets").child(sex).child(name).child("Thursday");
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Map<String,String> post = (Map<String, String>)dataSnapshot.getValue();
                        String id = post.get("id");
                        Log.e(TAG, "onDataChange:"+id);
                        if (id .equals(id1)){
                            Log.e(TAG, "onDataChange: onPoint");
                            startActivity(new Intent(daysActivity.this, dayoffActivity.class));
                        }else{
                            Intent intent = new Intent(daysActivity.this, dayExcersiceSet.class);
                            intent.putExtra("sex",sex);
                            intent.putExtra("name",name);
                            intent.putExtra("day","Thursday");
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference db = mDatabase.child("ExcersiceSets").child(sex).child(name).child("Friday");
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Map<String,String> post = (Map<String, String>)dataSnapshot.getValue();
                        String id = post.get("id");
                        Log.e(TAG, "onDataChange:"+id);

                        if (id .equals(id1)){
                            Log.e(TAG, "onDataChange: onPoint");
                            startActivity(new Intent(daysActivity.this, dayoffActivity.class));
                        }else{
                            Intent intent = new Intent(daysActivity.this, dayExcersiceSet.class);
                            intent.putExtra("sex",sex);
                            intent.putExtra("name",name);
                            intent.putExtra("day","Friday");
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference db = mDatabase.child("ExcersiceSets").child(sex).child(name).child("Saturday");
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Map<String,String> post = (Map<String, String>)dataSnapshot.getValue();
                        String id = post.get("id");
                        Log.e(TAG, "onDataChange:"+id);

                        if (id.equals(id1)){
                            Log.e(TAG, "onDataChange: onPoint");
                            startActivity(new Intent(daysActivity.this, dayoffActivity.class));
                        }else{
                            Intent intent = new Intent(daysActivity.this, dayExcersiceSet.class);
                            intent.putExtra("sex",sex);
                            intent.putExtra("name",name);
                            intent.putExtra("day","Saturday");
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
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
            startActivity(new Intent(daysActivity.this,excersiceSetActivity.class));
        } else if (id == R.id.allw) {
            startActivity(new Intent(daysActivity.this,frontPageActivity.class));

        } else if (id == R.id.nutC) {
            startActivity(new Intent(daysActivity.this, NutritionList.class));
        } else if (id == R.id.bmiA) {
            startActivity(new Intent(daysActivity.this,BMIActivity.class));
        } else if (id == R.id.dietC) {
            startActivity(new Intent(daysActivity.this,DietCartListActivity.class));
        }
        DrawerLayout drawer = findViewById(R.id.Ddrawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
