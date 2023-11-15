package com.example.teamprojectandroid;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalItemAdapter extends RecyclerView.Adapter<CalItemAdapter.ViewHolder> {

    ArrayList<CalItem> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.cal_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CalItem item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItemMedicine, tvItemDate, tvItemDetail1, tvItemDetail2, tvItemDetail3, tvItemDetail4, tvItemDetail5, tvItemMemo;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            tvItemMedicine = itemView.findViewById(R.id.tvItemMedicine);
            tvItemDate = itemView.findViewById(R.id.tvItemDate);
            tvItemDetail1 = itemView.findViewById(R.id.tvItemDetail1);
            tvItemDetail2 = itemView.findViewById(R.id.tvItemDetail2);
            tvItemDetail3 = itemView.findViewById(R.id.tvItemDetail3);
            tvItemDetail4 = itemView.findViewById(R.id.tvItemDetail4);
            tvItemDetail5 = itemView.findViewById(R.id.tvItemDetail5);
            tvItemMemo = itemView.findViewById(R.id.tvItemMemo);
        }

        public void setItem(CalItem item){
            tvItemMedicine.setText(item.getMedicine());
            tvItemDate.setText(item.getStartdate()+" ~ "+item.getFinishdate());
            tvItemMemo.setText(item.getMemo());

            if(item.getDetail1().equals("1")){
                tvItemDetail1.setTextColor(Color.parseColor("#6F00FF"));
                tvItemDetail1.setPaintFlags(tvItemDetail1.getPaintFlags()| Paint.FAKE_BOLD_TEXT_FLAG);
            }
            if(item.getDetail2().equals("1")){
                tvItemDetail2.setTextColor(Color.parseColor("#6F00FF"));
                tvItemDetail2.setPaintFlags(tvItemDetail2.getPaintFlags()| Paint.FAKE_BOLD_TEXT_FLAG);
            }
            if(item.getDetail3().equals("1")){
                tvItemDetail3.setTextColor(Color.parseColor("#6F00FF"));
                tvItemDetail3.setPaintFlags(tvItemDetail3.getPaintFlags()| Paint.FAKE_BOLD_TEXT_FLAG);
            }
            if(item.getDetail4().equals("1")){
                tvItemDetail4.setTextColor(Color.parseColor("#6F00FF"));
                tvItemDetail4.setPaintFlags(tvItemDetail4.getPaintFlags()| Paint.FAKE_BOLD_TEXT_FLAG);
            }
            if(item.getDetail5().equals("1")){
                tvItemDetail5.setTextColor(Color.parseColor("#6F00FF"));
                tvItemDetail5.setPaintFlags(tvItemDetail5.getPaintFlags()| Paint.FAKE_BOLD_TEXT_FLAG);
            }
        }
    }

    public void addItem(CalItem item) {
        items.add(item);
    }

    public void addItem(int position, CalItem item) {
        items.add(position, item);
    }

    public void removeItem(int position){
        items.remove(position);
    }

    public void removeAllItem(){
        items.clear();
    }
}
