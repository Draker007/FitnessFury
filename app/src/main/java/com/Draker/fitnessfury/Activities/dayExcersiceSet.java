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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.Draker.fitnessfury.Adapters.DESadapter;
import com.Draker.fitnessfury.DataClasses.NutritionList;
import com.Draker.fitnessfury.DataClasses.excersice;
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

public class dayExcersiceSet extends AppCompatActivity implements DESadapter.OnDESListener, NavigationView.OnNavigationItemSelectedListener {
    String TAG = "draker";
    RecyclerView recyclerView;
    private List<excersice> excersices = new ArrayList<>();
    DESadapter adapter ;
    TextView details,days;
    private DatabaseReference mdatabase;
    ConstraintLayout set, bmi, list, nutchart, home;
    ProgressBar progressBar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_excersice_set);
        recyclerView = (RecyclerView)findViewById(R.id.DESrecycler);
        progressBar = findViewById(R.id.DESProgress);



         toolbar = findViewById(R.id.DEStoolbar);

        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.DESdrawer);
        NavigationView navigationView = findViewById(R.id.DESnav);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        customType(dayExcersiceSet.this,"left-to-right");


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DESadapter(excersices,this);
        recyclerView.setAdapter(adapter);
        mdatabase = FirebaseDatabase.getInstance().getReference();
        setData();
    }
    private void setData(){
        final String sex = getIntent().getStringExtra("sex");
        final String name = getIntent().getStringExtra("name");
        final String day = getIntent().getStringExtra("day");
        final DatabaseReference databaseReference = mdatabase.child("ExcersiceSets").child(sex).child(name).child(day);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, String> map = (Map<String, String>) dataSnapshot.getValue();
                String d = map.get("detail");

                toolbar.setTitle(d);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren() ) {
                    String id = snapshot.getKey();
                    String id1 = "id";
                    String detail1 = "detail";
                    Log.e(TAG, "onDataChange: "+id);
                    if (id.equals(id1) || id.equals(detail1)) {

                        continue;
                    }else {

                        Map<String, String> map = (Map<String, String>) snapshot.getValue();
                        String name1 = map.get("name");
                        String image = map.get("Image");
                        //String id = map.get("id");
                        excersice excersice = new excersice(image, name1, id);
                        excersices.add(excersice);
                        recyclerView.setAdapter(adapter);


                    }
                }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }

    @Override
    public void onDESClick(int position) {
    final excersice model = excersices.get(position);
        Intent intent = new Intent(this,ExcersiceMain.class);
        String sex = getIntent().getStringExtra("sex");
        String name = getIntent().getStringExtra("name");
        String id = model.getId();
        intent.putExtra("sex",sex);
        intent.putExtra("name",name);
        intent.putExtra("id",id);
        String day = getIntent().getStringExtra("day");
        Log.e("draker", "onClick: "+day);
        intent.putExtra("day",day);
        Log.e(TAG, "onDESClick: "+sex+id+name);
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
            startActivity(new Intent(dayExcersiceSet.this,excersiceSetActivity.class));
        } else if (id == R.id.allw) {
            startActivity(new Intent(dayExcersiceSet.this,frontPageActivity.class));

        } else if (id == R.id.nutC) {
            startActivity(new Intent(dayExcersiceSet.this, NutritionList.class));
        } else if (id == R.id.bmiA) {
            startActivity(new Intent(dayExcersiceSet.this,BMIActivity.class));
        } else if (id == R.id.dietC) {
            startActivity(new Intent(dayExcersiceSet.this,DietCartListActivity.class));
        }
        DrawerLayout drawer = findViewById(R.id.DESdrawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
