package com.Draker.fitnessfury.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Draker.fitnessfury.DataClasses.fpDATA;
import com.Draker.fitnessfury.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class fpAdapter extends RecyclerView.Adapter<fpAdapter.MyviewHolder> {
private List<fpDATA> fpDATAList ;
private OnFpListener onFpListener;
    private Context context;

    public fpAdapter(List<fpDATA> fpDATAList, OnFpListener onFpListener, Context context) {
        this.fpDATAList = fpDATAList;
        this.onFpListener = onFpListener;
        this.context = context;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fp_recycler,viewGroup,false);
        MyviewHolder myviewHolder = new MyviewHolder(v,onFpListener);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i) {
        fpDATA fpDATA = fpDATAList.get(i);


        myviewHolder.imageView.setImageDrawable(context.getResources().getDrawable(fpDATA.getImage()));
        myviewHolder.textView.setText(fpDATA.getName());
    }

    @Override
    public int getItemCount() {
        return fpDATAList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        OnFpListener onFpListner;
        public MyviewHolder(@NonNull View itemView , OnFpListener onFpListner) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.FPImage);
            textView = (TextView)itemView.findViewById(R.id.FPname);
            this.onFpListner = onFpListner;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onFpListner.onFPclick(getAdapterPosition());

        }

    }  public interface OnFpListener{
        void onFPclick(int position);
    }
}
