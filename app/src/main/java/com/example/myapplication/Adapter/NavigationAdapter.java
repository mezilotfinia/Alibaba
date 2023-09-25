package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Fragment.LoginDialog;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.NavigationModel;
import com.example.myapplication.ProfileActivity;
import com.example.myapplication.R;

import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigationViewHolder> {
    private Context context;
    private List<NavigationModel> models;
    OnDialogDismissed onDialogDismissed;
    SharedPreferences sharedPreferences;

    public NavigationAdapter(Context context, List<NavigationModel> models){
        this.models=models;
        this.context=context;
        sharedPreferences=context.getSharedPreferences("login",Context.MODE_PRIVATE);
    }
    @NonNull
    @Override
    public NavigationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_navigation_menu,parent,false);
        return new NavigationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NavigationViewHolder holder, int position) {
        NavigationModel model= models.get(position);
        holder.txtTitle.setText(model.getTitle());
        holder.imgIcon.setImageResource(model.getDrawable());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.txtTitle.getText().equals("پروفایل کاربری")){
                    String email=sharedPreferences.getString("email","");
                    if (email.equals("")){
                        LoginDialog loginDialog=new LoginDialog();
                        loginDialog.show(MainActivity.fragmentManager,null);
                        loginDialog.setOnSignUpClicked(new LoginDialog.OnSignUpClicked() {
                            @Override
                            public void onClicked(String email) {
                                onDialogDismissed.onDismissed(email);
                            }
                        });
                    }else {
                        Intent intent=new Intent(context, ProfileActivity.class);
                        intent.putExtra("email",email);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setOnDialogDismissed(OnDialogDismissed onDialogDismissed){
        this.onDialogDismissed=onDialogDismissed;
    }

    public class NavigationViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle;
        ImageView imgIcon;
        ConstraintLayout parent;
        public NavigationViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txt_navigationItem_title);
            imgIcon=itemView.findViewById(R.id.img_navigationitem_icon);
            parent=itemView.findViewById(R.id.cl_navigationItem_parent);
        }
    }
    public interface OnDialogDismissed{
        void onDismissed(String email);
    }
}
