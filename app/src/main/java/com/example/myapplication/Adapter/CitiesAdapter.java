package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> {

    private List<String> cities;
    private OnCitySelected onCitySelected;

    public CitiesAdapter(List<String> cities,OnCitySelected onCitySelected) {
        this.cities=cities;
        this.onCitySelected=onCitySelected;
    }

    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item,parent,false);
        return new CitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesViewHolder holder, int position) {
        final String city=cities.get(position);
        holder.txtTitle.setText(city);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCitySelected.onSelected(city);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class CitiesViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        RelativeLayout parent;

        public CitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_cityItem_title);
            parent = itemView.findViewById(R.id.rel_cityItem_parent);
        }
    }
    public  interface OnCitySelected{
        void onSelected(String city);
    }
}
