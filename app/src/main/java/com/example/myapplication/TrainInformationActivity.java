package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Model.TrainTicket;

public class TrainInformationActivity extends AppCompatActivity {
    TextView txtToolbarOD,txtToolbarDate,txtType,txtPrice,txtTrainId,txtCoupeCapacity;
    ImageView imgToolbarBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_information);
        setupViews();
        getTicket();

    }

    private void getTicket() {
        TrainTicket trainTicket=getIntent().getExtras().getParcelable("trainTicket");
        txtToolbarOD.setText(trainTicket.getOrigin()+" - "+trainTicket.getDestination());
        txtToolbarDate.setText(trainTicket.getDate());
        txtType.setText(trainTicket.getType());
        txtPrice.setText(trainTicket.getPrice());
        txtTrainId.setText(trainTicket.getTrainId());
        txtCoupeCapacity.setText(trainTicket.getCoupeCapacity());
    }

    private void setupViews() {
        txtToolbarOD=findViewById(R.id.txt_trainInformation_OD);
        txtToolbarDate=findViewById(R.id.txt_trainInformation_date);
        txtType=findViewById(R.id.txt_trainInformation_type);
        txtPrice=findViewById(R.id.txt_trainInformation_price);
        txtTrainId=findViewById(R.id.txt_trainInformation_trainId);
        txtCoupeCapacity=findViewById(R.id.txt_trainInformation_coupeCapacity);
        imgToolbarBack=findViewById(R.id.img_trainInformation_back);
        imgToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}