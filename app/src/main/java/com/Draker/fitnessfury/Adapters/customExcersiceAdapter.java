package com.Draker.fitnessfury.Adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Draker.fitnessfury.DataClasses.excersicesCustom;
import com.Draker.fitnessfury.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class customExcersiceAdapter extends RecyclerView.Adapter<customExcersiceAdapter.MyviewHolder> {
private List<excersicesCustom> excersicesCustomList;
private onCustomExcersiceListner onCustomExcersiceListner;

    public customExcersiceAdapter(List<excersicesCustom> excersicesCustomList, customExcersiceAdapter.onCustomExcersiceListner onCustomExcersiceListner) {
        this.excersicesCustomList = excersicesCustomList;
        this.onCustomExcersiceListner = onCustomExcersiceListner;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_excersice_recycler,viewGroup,false);
        MyviewHolder myviewHolder = new MyviewHolder(v,onCustomExcersiceListner);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i) {
excersicesCustom excersicesCustom = excersicesCustomList.get(i);
myviewHolder.name.setText(excersicesCustom.getName() );
        Picasso.get().load(excersicesCustom.getImage()).into(myviewHolder.setImage);
    }

    @Override
    public int getItemCount() {
        return excersicesCustomList.size() ;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ConstraintLayout layout;
        TextView name;
        ImageView setImage;
        onCustomExcersiceListner onCustomExcersiceListner;

        public MyviewHolder(@NonNull View itemView, onCustomExcersiceListner onCustomExcersiceListner) {
            super(itemView);
            setImage = (ImageView) itemView.findViewById(R.id.CERImage);
            name = (TextView) itemView.findViewById(R.id.WorkoutSetName);
            this.onCustomExcersiceListner = onCustomExcersiceListner;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCustomExcersiceListner.OnCustomExcersiceClick(getAdapterPosition());

        }
    }
        public interface onCustomExcersiceListner{
            void OnCustomExcersiceClick(int position);
        }

}

