package com.Draker.fitnessfury.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Draker.fitnessfury.DataClasses.nom;
import com.Draker.fitnessfury.R;

import java.util.List;

public class nomAdapter extends RecyclerView.Adapter<nomAdapter.MyviewHolder>{

    private List<nom> nomList;
    private onNOMmealListner onNOMmealListner;

    public nomAdapter(List<nom> nomList, nomAdapter.onNOMmealListner onNOMmealListner) {
        this.nomList = nomList;
        this.onNOMmealListner = onNOMmealListner;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nom_recycler,viewGroup,false);
        MyviewHolder myviewHolder = new MyviewHolder(v,onNOMmealListner);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i) {
    nom nom = nomList.get(i);
    myviewHolder.name.setText(nom.getName());
    }

    @Override
    public int getItemCount() {
        return nomList.size();
    }


    public class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        onNOMmealListner onNOMmealListner;
        public MyviewHolder(@NonNull View itemView , onNOMmealListner onNOMmealListner) {
            super(itemView);
        name = (TextView)itemView.findViewById(R.id.nommeal);
        this.onNOMmealListner = onNOMmealListner;
        itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNOMmealListner.OnNOMmealClick(getAdapterPosition());
        }
    }
    public interface onNOMmealListner{
        void OnNOMmealClick(int position);
    }
}
