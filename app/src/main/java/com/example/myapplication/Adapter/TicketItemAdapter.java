package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.InformationActivity;
import com.example.myapplication.Model.Ticket;
import com.example.myapplication.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TicketItemAdapter extends RecyclerView.Adapter<TicketItemAdapter.TicketViewHolder> {

    private List<Ticket> tickets;
    private Context context;

    public  TicketItemAdapter (List<Ticket> tickets){
        this.tickets=tickets;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item,parent,false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        final Ticket ticket=tickets.get(position);
        holder.id=ticket.getId();
        holder.txtPrice.setText(ticket.getPriceYoung());
        holder.txtCapacity.setText(ticket.getCapacity()+"نفر");
        holder.txtCompany.setText(ticket.getCompany());
        holder.txtTime.setText(ticket.getFlightTime());
        holder.txtKind1.setText(ticket.getKind1());
        holder.txtKind2.setText(ticket.getKind2());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, InformationActivity.class);
                intent.putExtra("ticket",ticket);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder{
        TextView txtPrice,txtTime,txtCompany,txtCapacity,txtKind1,txtKind2;
        RelativeLayout parent;
        String id;
        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPrice=itemView.findViewById(R.id.txt_detail_price);
            txtTime=itemView.findViewById(R.id.txt_detail_time);
            txtCompany=itemView.findViewById(R.id.txt_detail_company);
            txtCapacity=itemView.findViewById(R.id.txt_detail_capacity);
            txtKind1=itemView.findViewById(R.id.txt_detail_kind1);
            txtKind2=itemView.findViewById(R.id.txt_detail_kind2);
            parent=itemView.findViewById(R.id.rel_detailItem_parent);
        }
    }
}
