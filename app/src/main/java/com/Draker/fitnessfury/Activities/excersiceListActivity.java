package com.Draker.fitnessfury.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.Draker.fitnessfury.Adapters.excersiceAdapter;
import com.Draker.fitnessfury.DataClasses.excersice;
import com.Draker.fitnessfury.Fragments.descriptionexcersice_fragment;
import com.Draker.fitnessfury.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.Draker.fitnessfury.R.drawable.biceps;
import static maes.tech.intentanim.CustomIntent.customType;

public class excersiceListActivity extends AppCompatActivity implements excersiceAdapter.OnExcersiceListener {

    RecyclerView recyclerView;
    private List<excersice> excersices = new ArrayList<>();
    excersiceAdapter adapter;
    ProgressBar progressBar;
    private DatabaseReference mDatabase;
    public List<String> data = new ArrayList<>();

    CollapsingToolbarLayout toll;
    Toolbar t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excersice_list);
        customType(excersiceListActivity.this,"left-to-right");
        recyclerView = (RecyclerView)findViewById(R.id.excersiceRecycler);
        progressBar = (ProgressBar)findViewById(R.id.ELProgress);
        final String data1 = getIntent().getStringExtra("type");

        toll = findViewById(R.id.elname);
//        t = findViewById(R.id.eltoolbar);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new excersiceAdapter(excersices,this);
        recyclerView.setAdapter(adapter);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        excersicedata();


    }

    @SuppressLint("ResourceAsColor")
    private void excersicedata(){
        final String data1 = getIntent().getStringExtra("type");
        final String image = getIntent().getStringExtra("img");
DatabaseReference excersiceData = mDatabase.child("exercises").child(data1);
Log.d("database","starting");



toll.setTitle(data1);


    excersiceData.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Log.d("database","inside");
            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                data.add(snapshot.getKey());
                Log.e("database","inside1");
                Map<String , String> postMap = (Map<String, String>)snapshot.getValue();
                String name = postMap.get("WorkoutName");
                Log.d("database",name);
                String id = postMap.get("id");
                String image = postMap.get("WorkoutImage1");
//                Uri uri = Uri.parse(image);
                excersice excersice = new excersice(image,name,id);
                excersices.add(excersice);

recyclerView.setAdapter(adapter);
progressBar.setVisibility(View.INVISIBLE);

            };
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


    }

    @Override
    public void onExcersiceClick(int position) {
      String hihi =  excersices.get(position).toString();
        final excersice model = excersices.get(position);
        Intent intent = new Intent(this,CheckEexcersiceActivity.class);
        intent.putExtra("id",model.getId());
        final String data1 = getIntent().getStringExtra("type");
        Bundle bundle = new Bundle();
        bundle.putString("id",model.getId());
        bundle.putString("type",data1);
        descriptionexcersice_fragment fragobj = new descriptionexcersice_fragment();
        fragobj.setArguments(bundle);
        intent.putExtra("type",data1);
        intent.putExtra("name",model.getExcersiceName());
        startActivity(intent) ;
    }
}
