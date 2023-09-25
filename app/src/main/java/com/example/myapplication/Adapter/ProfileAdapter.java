package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.Model.Profile;
import com.example.myapplication.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    private List<Profile> profiles;
    private Context context;

    public  ProfileAdapter(List<Profile> profiles){
    this.profiles=profiles;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item,parent,false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        int row=position+1;
        Profile profile=profiles.get(position);
        //dar recyclerview age sharti mizari htmn else ham bzar k satr haye dig taqir nkone
        if(row%2==0){
            holder.parent.setBackgroundColor(ContextCompat.getColor(context,R.color.color_gray200));
        }else {
            holder.parent.setBackgroundColor(ContextCompat.getColor(context,R.color.color_default));
        }


        holder.txtTitle.setText(profile.getTitle());
        holder.txtValue.setText(profile.getValue());
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle,txtValue;
        ConstraintLayout parent;
        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txt_profileItem_title);
            txtValue=itemView.findViewById(R.id.txt_profileItem_value);
            parent=itemView.findViewById(R.id.cl_profile_parent);
        }
    }
}
