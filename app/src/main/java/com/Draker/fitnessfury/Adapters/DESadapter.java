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

public class DESadapter extends RecyclerView.Adapter<DESadapter.MyViewHolder>{

    private List<excersice> excersices;
    private OnDESListener onDESListener;

    public DESadapter(List<excersice> excersices, OnDESListener onDESListener) {
        this.excersices = excersices;
        this.onDESListener = onDESListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.des_recycler,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(v,onDESListener);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        excersice excersice = excersices.get(i);
        Picasso.get().load(excersice.getExcersiceImage()).into(myViewHolder.DESimage);
        myViewHolder.DESname.setText(excersice.getExcersiceName());
    }

    @Override
    public int getItemCount() {
        return excersices.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView DESimage;
        TextView DESname;
        OnDESListener onDESListener;
        public MyViewHolder(@NonNull View itemView, OnDESListener onDESListener1) {
            super(itemView);
            DESimage = (ImageView) itemView.findViewById(R.id.DESimage);
            DESname = (TextView) itemView.findViewById(R.id.DESname);
            this.onDESListener = onDESListener1;
        itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
        onDESListener.onDESClick(getAdapterPosition());
        }
    }
    public interface OnDESListener{
        void onDESClick(int position);
    }
}
