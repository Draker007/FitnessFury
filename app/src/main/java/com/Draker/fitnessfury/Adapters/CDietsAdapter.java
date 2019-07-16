package com.Draker.fitnessfury.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Draker.fitnessfury.DataClasses.diets;
import com.Draker.fitnessfury.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CDietsAdapter extends RecyclerView.Adapter<CDietsAdapter.MyViewHolder> {
private List<diets> dietsList;
private OnDietsListener onDietsListener;

    public CDietsAdapter(List<diets> dietsList, CDietsAdapter.OnDietsListener onDietsListener) {
        this.dietsList = dietsList;
        this.onDietsListener = onDietsListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cdrecycler_layout,viewGroup,false);
        MyViewHolder viewHolder = new MyViewHolder(v,onDietsListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        diets diets = dietsList.get(i);
        Picasso.get().load(diets.getImage()).into(myViewHolder.cdImage);
        myViewHolder.cdname.setText(diets.getName());
        myViewHolder.cdDur.setText(diets.getDuration());
        myViewHolder.cdMeal.setText(diets.getMeals());

    }

    @Override
    public int getItemCount() {
        return dietsList.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView cdImage;
    TextView cdMeal , cdDur ,cdname;
    OnDietsListener onDietsListener;
        public MyViewHolder(@NonNull View itemView , OnDietsListener onDietsListener) {
            super(itemView);
            cdImage = itemView.findViewById(R.id.cdImage);
            cdMeal = itemView.findViewById(R.id.cdNoOfMeals);
            cdDur = itemView.findViewById(R.id.cdDuration);
            cdname = itemView.findViewById(R.id.cdName);
            this.onDietsListener = onDietsListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        onDietsListener.onDietsClick(getAdapterPosition());
        }
    }
    public interface OnDietsListener{
        void onDietsClick(int position);
    }
}
