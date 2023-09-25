package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.PenaltyAdapter;
import com.example.myapplication.Model.Penalty;
import com.example.myapplication.Model.Ticket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InformationActivity extends AppCompatActivity {

    TextView txtDate,txtTime,txtOrigin,txtDestination,txtFlightId,txtKind1,txtKind2,txtYoungPrice,txtChildPrice,txtBabyPrice;
    RecyclerView recyclerView;
    List<Penalty> penalties;
    String id="";
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        setupViews();
        getTicket();
        getPenaltyFromServer(id);
    }

    private void getPenaltyFromServer(String id) {
        String url="https://ns20.ir/alibaba/penalty.php?id="+id;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i <response.length() ; i++) {
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        Penalty penalty=new Penalty();
                        penalty.setRuleTitle(jsonObject.getString("rule_title"));
                        penalty.setPenaltyPercentage(jsonObject.getString("penalty_percentage"));
                        penalties.add(penalty);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    recyclerView.setAdapter(new PenaltyAdapter(penalties));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG", "onErrorResponse: "+error.toString());
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void setupViews() {
        penalties=new ArrayList<>();
        txtDate=findViewById(R.id.txt_information_toolbarInfoTicket);
        txtTime=findViewById(R.id.txt_information_time);
        txtOrigin=findViewById(R.id.txt_information_origin);
        txtDestination=findViewById(R.id.txt_information_destination);
        txtFlightId=findViewById(R.id.txt_information_flightId);
        txtKind1=findViewById(R.id.txt_information_class);
        txtKind2=findViewById(R.id.txt_information_kind);
        txtYoungPrice=findViewById(R.id.txt_information_youngPrice);
        txtChildPrice=findViewById(R.id.txt_information_childPrice);
        txtBabyPrice=findViewById(R.id.txt_information_babyPrice);
        recyclerView=findViewById(R.id.rv_information_penalty);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        imgBack=findViewById(R.id.img_information_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getTicket() {
        Ticket ticket=getIntent().getParcelableExtra("ticket");
        txtDate.setText(ticket.getDate());
        txtTime.setText(ticket.getFlightTime());
        txtOrigin.setText(ticket.getOrigin()+"-"+ticket.getOriginAirport());
        txtDestination.setText(ticket.getDestination()+"-"+ticket.getDestinationAirport());
        txtFlightId.setText(ticket.getFlightId());
        txtKind1.setText(ticket.getKind1());
        txtKind2.setText(ticket.getKind2());
        txtYoungPrice.setText(ticket.getPriceYoung());
        txtChildPrice.setText(ticket.getPriceChild());
        txtBabyPrice.setText(ticket.getPriceBaby());
        id=ticket.getId();


    }
}