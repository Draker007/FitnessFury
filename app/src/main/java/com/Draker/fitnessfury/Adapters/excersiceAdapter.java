package com.Draker.fitnessfury.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Draker.fitnessfury.DataClasses.excersice;
import com.Draker.fitnessfury.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class excersiceAdapter extends RecyclerView.Adapter<excersiceAdapter.MyViewHolder> {


        private List<excersice> excersices;
        private OnExcersiceListener onExcersiceListener;



    public excersiceAdapter( List<excersice> excersices , OnExcersiceListener onExcersiceListener) {

        this.excersices = excersices;
        this.onExcersiceListener = onExcersiceListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.exercise_recycler,viewGroup,false);
        MyViewHolder viewHolder = new MyViewHolder(v,onExcersiceListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        excersice excersice = excersices.get(i);
        Picasso.get().load(excersice.getExcersiceImage()).into(myViewHolder.excersiceImage);
    myViewHolder.excersiceName.setText(excersice.getExcersiceName());
    //myViewHolder.excersiceImage.setImageURI(excersice.getExcersiceImage());

    }

    @Override
    public int getItemCount() {
        return excersices.size();
    }

    public static class  MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView excersiceImage;
    TextView excersiceName;
    OnExcersiceListener onExcersiceListener;
        public MyViewHolder(@NonNull View itemView,OnExcersiceListener onExcersiceListener) {
            super(itemView);

            excersiceImage = itemView.findViewById(R.id.exerciseImage);
            excersiceName = itemView.findViewById(R.id.exerciseName);
            this.onExcersiceListener = onExcersiceListener;
            itemView.setOnClickListener(this);
       }



        @Override
        public void onClick(View v) {
            onExcersiceListener.onExcersiceClick(getAdapterPosition());
        }
    }
    public interface OnExcersiceListener{
        void onExcersiceClick(int position);
    }
}

