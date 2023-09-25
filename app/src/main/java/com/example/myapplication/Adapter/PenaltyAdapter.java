package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.Model.Penalty;
import com.example.myapplication.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PenaltyAdapter extends RecyclerView.Adapter<PenaltyAdapter.PenaltyViewHolder> {
    private List<Penalty> penalties;
    public PenaltyAdapter(List<Penalty> penalties){
        this.penalties=penalties;
    }

    @NonNull
    @Override
    public PenaltyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.penalty_item,parent,false);
        return new PenaltyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenaltyViewHolder holder, int position) {
        Penalty penalty=penalties.get(position);
        holder.txtRuleTitle.setText(penalty.getRuleTitle());
        holder.txtPercentage.setText(penalty.getPenaltyPercentage()+"%");
    }

    @Override
    public int getItemCount() {
        return penalties.size();
    }

    public class PenaltyViewHolder extends RecyclerView.ViewHolder{
        TextView txtRuleTitle,txtPercentage;
        public PenaltyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRuleTitle=itemView.findViewById(R.id.txt_penaltyItem_rule);
            txtPercentage=itemView.findViewById(R.id.txt_penaltyItem_percentage);
        }
    }
}
