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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Draker.fitnessfury.DataClasses.NutritionList;
import com.Draker.fitnessfury.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Map;

import static maes.tech.intentanim.CustomIntent.customType;

public class ExcersiceSetDescActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView name,weeks,desc;
    Button go;
    ImageView images;
    ConstraintLayout set, bmi, list, nutchart, home;
    DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excersice_set_desc);
        customType(ExcersiceSetDescActivity.this,"left-to-right");
        name = (TextView)findViewById(R.id.ESDname);
        weeks=(TextView)findViewById(R.id.ESDweeks);
        desc=(TextView)findViewById(R.id.ESDdesc);
        go = (Button)findViewById(R.id.ESDstart);
        images = (ImageView)findViewById(R.id.ESDimage);


        mdatabase = FirebaseDatabase.getInstance().getReference();
        Toolbar toolbar = findViewById(R.id.ESDtoolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.ESDdrawer);
        NavigationView navigationView = findViewById(R.id.ESDnav);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        prepareData();
    }

    private void prepareData() {
   final String name12 = getIntent().getStringExtra("name");
        final String sex = getIntent().getStringExtra("sex");
        Log.e("draker", name12 );
    DatabaseReference databaseReference = mdatabase.child("ExcersiceSets").child(sex).child(name12);
    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Map<String,String> postMap = (Map<String, String>)dataSnapshot.getValue();
                String name1 = postMap.get("who");
                 name.setText(name1);
                 String week = postMap.get("weeks");
                 weeks.setText(week);
                 String descr = postMap.get("desc");
                 desc.setText(descr);
                 String image = postMap.get("image");
            Picasso.get().load(image).into(images);


        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


        go.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ExcersiceSetDescActivity.this,daysActivity.class);
             intent.putExtra("name", name12);
            intent.putExtra("sex",sex);

            startActivity(intent);
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
            startActivity(new Intent(ExcersiceSetDescActivity.this,excersiceSetActivity.class));
        } else if (id == R.id.allw) {
            startActivity(new Intent(ExcersiceSetDescActivity.this,frontPageActivity.class));

        } else if (id == R.id.nutC) {
            startActivity(new Intent(ExcersiceSetDescActivity.this, NutritionList.class));
        } else if (id == R.id.bmiA) {
            startActivity(new Intent(ExcersiceSetDescActivity.this,BMIActivity.class));
        } else if (id == R.id.dietC) {
            startActivity(new Intent(ExcersiceSetDescActivity.this,DietCartListActivity.class));
        }
        DrawerLayout drawer = findViewById(R.id.ESDdrawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
