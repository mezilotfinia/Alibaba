package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Model.BusTicket;
import com.example.myapplication.Model.Chair;

import java.util.ArrayList;
import java.util.List;

public class BusInformationActivity extends AppCompatActivity {
    BusTicket busTicket;
    TextView txtOD,txtToolbarDate,txtOrigin,txtPrice,txtType,txtTime,txtOriginTerminal,txtDestinationTerminal,txtDistance;
    ImageView imgToolbarBack;
    RecyclerView recyclerView;
    Button btnChooseChair;
    ArrayList<Chair> chairList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_information);
        setupViews();
        busTicket=getIntent().getExtras().getParcelable("busTicket");
        txtOD.setText(busTicket.getOrigin()+" - "+busTicket.getDestination());
        txtToolbarDate.setText(busTicket.getDate());
        txtOrigin.setText(busTicket.getOrigin()+" - "+busTicket.getOriginTerminal());
        txtTime.setText(busTicket.getTime());
        txtType.setText(busTicket.getType());
        txtPrice.setText(busTicket.getPrice());
        txtOriginTerminal.setText(busTicket.getOriginTerminal());
        txtDestinationTerminal.setText(busTicket.getDestinationTerminal());
        txtDistance.setText(busTicket.getDistance()+" کیلومتر");
        chairList=getIntent().getExtras().getParcelableArrayList("chairs");
        imgToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupViews() {
        txtOD=findViewById(R.id.txt_busInformation_OD);
        txtToolbarDate=findViewById(R.id.txt_busInformation_date);
        txtOrigin=findViewById(R.id.txt_busInformation_origin);
        txtPrice=findViewById(R.id.txt_busInformation_price);
        txtType=findViewById(R.id.txt_busInformation_type);
        txtTime=findViewById(R.id.txt_busInformation_time);
        txtOriginTerminal=findViewById(R.id.txt_busInformation_originTerminal);
        txtDestinationTerminal=findViewById(R.id.txt_busInformation_destinationTerminal);
        txtDistance=findViewById(R.id.txt_busInformation_distance);
        imgToolbarBack=findViewById(R.id.img_busInformation_back);
        recyclerView=findViewById(R.id.rv_busInformation_penaltyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        btnChooseChair=findViewById(R.id.btn_busInformation_chooseChair);
        btnChooseChair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), com.example.myapplication.ChairActivity.class);
                startActivity(intent);
            }
        });
    }
}