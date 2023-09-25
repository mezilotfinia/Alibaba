package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.BusInformationActivity;
import com.example.myapplication.Model.BusTicket;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BusTicketAdapter extends RecyclerView.Adapter<BusTicketAdapter.BusTicketViewHolder> {
    private Context context;
    private List<BusTicket> busTickets;
    public BusTicketAdapter (Context context, List<BusTicket> busTickets){
        this.context=context;
        this.busTickets=busTickets;
    }

    @NonNull
    @Override
    public BusTicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.bus_item,parent,false);
        return new BusTicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusTicketViewHolder holder, int position) {
        final BusTicket busTicket=busTickets.get(position);
        holder.txtOrigin.setText(busTicket.getOrigin());
        holder.txtOriginTerminal.setText(busTicket.getOriginTerminal());
        holder.txtDestinationTerminal.setText(busTicket.getDestinationTerminal());
        holder.txtPrice.setText(busTicket.getPrice());
        holder.txtType.setText(busTicket.getType());
        holder.txtCapacity.setText("ظرفیت موجود : "+busTicket.getCapacity()+" نفر ");
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, BusInformationActivity.class);
                intent.putExtra("busTicket",busTicket);
                intent.putParcelableArrayListExtra("chairs", (ArrayList<? extends Parcelable>) busTicket.getChairs());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return busTickets.size();
    }

    public class BusTicketViewHolder extends RecyclerView.ViewHolder{
        TextView txtOriginTerminal,txtDestinationTerminal,txtPrice,txtType,txtOrigin,txtCapacity;
        RelativeLayout parent;
        public BusTicketViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDestinationTerminal=itemView.findViewById(R.id.txt_busItem_destinationTerminal);
            txtOriginTerminal=itemView.findViewById(R.id.txt_busItem_originTerminal);
            txtPrice=itemView.findViewById(R.id.txt_busItem_price);
            txtType=itemView.findViewById(R.id.txt_busItem_type);
            txtOrigin=itemView.findViewById(R.id.txt_busItem_origin);
            txtCapacity=itemView.findViewById(R.id.txt_busItem_capacity);
            parent=itemView.findViewById(R.id.rel_busItem_parent);
        }
    }
}
