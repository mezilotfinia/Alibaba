package com.example.myapplication.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.R;

public class PassengersDialog extends DialogFragment {
    View view;
    TextView txtCountUsual,txtCountUsualB,txtCountUsualS;
    ImageView imgAdd,imgAddB,imgAddS,imgMinus,imgMinusB,imgMinusS;
    Button btnSubmit;
    int count=1;
    OnSubmitButtonClicked onSubmitButtonClicked;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_pick_passengers, null);
        setupViews();
        builder.setView(view);
        return builder.create();
    }

    private void setupViews() {
        txtCountUsual=view.findViewById(R.id.txt_dialogPassengers_usualCount);
        txtCountUsualB=view.findViewById(R.id.txt_dialogPassengers_usualCountB);
        txtCountUsualS=view.findViewById(R.id.txt_dialogPassengers_usualCountS);
        imgAdd=view.findViewById(R.id.img_dialog_passengers_add);
        imgAddB=view.findViewById(R.id.img_dialog_passengers_addB);
        imgAddS=view.findViewById(R.id.img_dialog_passengers_addS);
        imgMinus=view.findViewById(R.id.img_dialog_passengers_remove);
        imgMinusB=view.findViewById(R.id.img_dialog_passengers_removeB);
        imgMinusS=view.findViewById(R.id.img_dialog_passengers_removeS);
        btnSubmit=view.findViewById(R.id.btn_dialogPassengers_submit);

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                txtCountUsual.setText(count+"");
            }
        });
        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count!=1){
                    count--;
                }
                txtCountUsual.setText(count+"");
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitButtonClicked.onSubmitted(txtCountUsual.getText().toString());
                dismiss();
            }
        });
    }

   public void setOnSubmitButtonClicked(OnSubmitButtonClicked onSubmitButtonClicked){
        this.onSubmitButtonClicked=onSubmitButtonClicked;
   }

    public interface OnSubmitButtonClicked{
        void onSubmitted(String count);
    }
}
