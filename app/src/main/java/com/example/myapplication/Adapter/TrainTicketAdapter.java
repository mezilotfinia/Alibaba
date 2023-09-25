package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.Model.TrainTicket;
import com.example.myapplication.TrainInformationActivity;
import com.example.myapplication.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrainTicketAdapter extends RecyclerView.Adapter<TrainTicketAdapter.TrainTicketViewHolder> {
    private Context context;
    private List<TrainTicket> trainTickets;
    public TrainTicketAdapter(Context context, List<TrainTicket> trainTickets){
        this.context=context;
        this.trainTickets=trainTickets;
    }

    @NonNull
    @Override
    public TrainTicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.train_item,parent,false);
        return new TrainTicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainTicketViewHolder holder, int position) {
        final TrainTicket trainTicket=trainTickets.get(position);
        holder.id=trainTicket.getId();
        holder.txtType.setText("::"+trainTicket.getType());
        holder.txtStartTime.setText(trainTicket.getStartTime());
        holder.txtEndTime.setText(trainTicket.getEndTime());
        holder.txtCapacity.setText(trainTicket.getCapacity()+" نفر ");
        holder.txtPrice.setText(trainTicket.getPrice());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,TrainInformationActivity.class);
                intent.putExtra("trainTicket",trainTicket);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trainTickets.size();
    }

    public class TrainTicketViewHolder extends RecyclerView.ViewHolder{
        String id;
        RelativeLayout parent;
        TextView txtType,txtStartTime,txtEndTime,txtCapacity,txtPrice;
        public TrainTicketViewHolder(@NonNull View itemView) {
            super(itemView);
            txtType=itemView.findViewById(R.id.txt_trainItem_type);
            txtStartTime=itemView.findViewById(R.id.txt_trainItem_startTime);
            txtEndTime=itemView.findViewById(R.id.txt_trainItem_endTime);
            txtCapacity=itemView.findViewById(R.id.txt_trainItem_capacity);
            txtPrice=itemView.findViewById(R.id.txt_trainItem_price);
            parent=itemView.findViewById(R.id.rel_trainItem_parent);

        }
    }
}
