package com.Draker.fitnessfury.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Draker.fitnessfury.DataClasses.weekNo;
import com.Draker.fitnessfury.R;

import java.util.List;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.MyViewHolder>{

private List<weekNo> weekNos;
private onWeekListner onWeekListner;

    public WeekAdapter(List<weekNo> weekNos, WeekAdapter.onWeekListner onWeekListner) {
        this.weekNos = weekNos;
        this.onWeekListner = onWeekListner;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weekview_recycler,viewGroup,false);
        MyViewHolder myviewHolder = new MyViewHolder(v,onWeekListner);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
    weekNo weekNo = weekNos.get(i);
    String status = weekNo.getStatus();
    if (status == "True") {
        myViewHolder.checkImage.setImageResource(R.drawable.donetick);
    }
    else {
        myViewHolder.checkImage.setImageResource(R.drawable.go);
    }
    myViewHolder.week.setText(weekNo.getWeek());
    }

    @Override
    public int getItemCount() {
        return weekNos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView checkImage;
        TextView week;
        String Status;
        onWeekListner onWeekListner;
        public MyViewHolder(@NonNull View itemView , onWeekListner onWeekListner) {
            super(itemView);
            checkImage = (ImageView) itemView.findViewById(R.id.checkImage);
            week=(TextView)itemView.findViewById(R.id.week);
            this.onWeekListner = onWeekListner;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
onWeekListner.OnWeekClick(getAdapterPosition());
        }
    }
    public interface onWeekListner{
        void OnWeekClick(int position);
    }
}
