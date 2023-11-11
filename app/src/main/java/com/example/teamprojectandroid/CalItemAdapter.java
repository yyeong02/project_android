//package com.example.teamprojectandroid;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class CalItemAdapter extends RecyclerView.Adapter<CalItemAdapter.ViewHolder> {
//
//    private ArrayList<CalItem> items = new ArrayList<>();
//
//    public static class ViewHolder extends RecyclerView.ViewHolder{
//
//        TextView tvItemMedicine, tvItemDate, tvItemDetail1, tvItemDetail2, tvItemDetail3, tvItemDetail4, tvItemDetail5, tvItemMemo;
//        public ViewHolder(View itemView){
//            super(itemView);
//
//            tvItemMedicine = itemView.findViewById(R.id.tvItemMedicine);
//            tvItemDate = itemView.findViewById(R.id.tvItemDate);
//            tvItemDetail1 = itemView.findViewById(R.id.tvItemDetail1);
//            tvItemDetail2 = itemView.findViewById(R.id.tvItemDetail2);
//            tvItemDetail3 = itemView.findViewById(R.id.tvItemDetail3);
//            tvItemDetail4 = itemView.findViewById(R.id.tvItemDetail4);
//            tvItemDetail5 = itemView.findViewById(R.id.tvItemDetail5);
//            tvItemMemo = itemView.findViewById(R.id.tvItemMemo);
//        }
//
//        public void setItem(CalItem item){
//            tvItemMedicine.setText(item.getMedicine());
//            tvItemDate.setText(item.getStartdate()+" ~ "+item.getFinishdate());
//        }
//    }
//
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View itemView = inflater.inflate(R.layout.cal_item,parent,false);
//        return ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CalItemAdapter.ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//}
