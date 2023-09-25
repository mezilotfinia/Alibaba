package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.BusTicketAdapter;
import com.example.myapplication.Adapter.TicketItemAdapter;
import com.example.myapplication.Adapter.TrainTicketAdapter;
import com.example.myapplication.Model.BusTicket;
import com.example.myapplication.Model.Chair;
import com.example.myapplication.Model.Ticket;
import com.example.myapplication.Model.TrainTicket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private String origin,destination,date,type;
    private TextView txtOrigin,txtDestination,txtDate;
    private List<Ticket> tickets;
    private List<TrainTicket> trainTickets;
    private ImageView imgBack,imgIcon;
    private List<BusTicket> busTickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupView();
        getMyIntent();
    }

    private void setupView() {
        busTickets=new ArrayList<>();
        trainTickets=new ArrayList<>();
        tickets=new ArrayList<>();
        recyclerView=findViewById(R.id.rv_detail_tickets);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        txtDate=findViewById(R.id.txt_detail_date);
        txtDestination=findViewById(R.id.txt_detail_destination);
        txtOrigin=findViewById(R.id.txt_detail_origin);
        imgBack=findViewById(R.id.img_detail_back);
        imgIcon=findViewById(R.id.img_detail_icon);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getAllFlightTicket(String dateI,String originI,String destinationI) {
        String URL="https://ns20.ir/alibaba/flight.php?date="+dateI+"&origin="+originI+"&destination="+destinationI;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Ticket ticket=new Ticket();
                        ticket.setId(jsonObject.getString("id"));
                        ticket.setOrigin(jsonObject.getString("origin"));
                        ticket.setDestination(jsonObject.getString("destination"));
                        ticket.setOriginAirport(jsonObject.getString("origin_airport"));
                        ticket.setDestinationAirport(jsonObject.getString("destination_airport"));
                        ticket.setDate(jsonObject.getString("data"));
                        ticket.setType(jsonObject.getString("type"));

                        String serverKind=jsonObject.getString("kind");
                        String[] kinds=serverKind.split("/");
                        ticket.setKind1(kinds[0]);
                        ticket.setKind2(kinds[1]);

                        ticket.setCompany(jsonObject.getString("company"));
                        ticket.setFlightTime(jsonObject.getString("flight_time"));
                        ticket.setLandTime(jsonObject.getString("land_time"));
                        ticket.setCapacity(jsonObject.getString("capacity"));
                        ticket.setFlightId(jsonObject.getString("flight_id"));
                        ticket.setPriceYoung(jsonObject.getString("price_young"));
                        ticket.setPriceChild(jsonObject.getString("price_child"));
                        ticket.setPriceBaby(jsonObject.getString("price_baby"));

                        tickets.add(ticket);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                recyclerView.setAdapter(new TicketItemAdapter(tickets));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG","onError:"+error);
            }
        });
        //{//POST METHOD
//            Map<String,String> params=new HashMap<>();
//            public Map<String,String> getParams(){
//                params.put("date",date);
//                params.put("origin",origin);
//                params.put("destination",destination);
//                return params;
//            }
     //   };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getMyIntent() {
        type=getIntent().getExtras().getString("type");
        origin=getIntent().getExtras().getString("origin");
        destination=getIntent().getExtras().getString("destination");
        date=getIntent().getExtras().getString("date");
        txtOrigin.setText(origin);
        txtDestination.setText(destination);
        txtDate.setText(date);
        if(type.equals("flight")){
            getAllFlightTicket(date,origin,destination);
        }
        if(type.equals("train")){
            getAllTrainTickets(origin,destination,date);
            imgIcon.setImageResource(R.drawable.ic_baseline_train_white_24);
        }
        if(type.equals("bus")){
            getAllBusTickets(origin,destination,date);
            imgIcon.setImageResource(R.drawable.ic_baseline_directions_bus_white_24);
        }
    }
    private void getAllTrainTickets(String origin,String destination,String date){
        String url="https://ns20.ir/alibaba/train.php?date="+date+"&origin="+origin+"&destination="+destination;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i <response.length() ; i++) {
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        TrainTicket trainTicket=new TrainTicket();
                        trainTicket.setId(jsonObject.getString("id"));
                        trainTicket.setTrainId(jsonObject.getString("train_id"));
                        trainTicket.setOrigin(jsonObject.getString("origin"));
                        trainTicket.setDestination(jsonObject.getString("destination"));
                        trainTicket.setStartTime(jsonObject.getString("start_time"));
                        trainTicket.setEndTime(jsonObject.getString("end_time"));
                        trainTicket.setDate(jsonObject.getString("date"));
                        trainTicket.setType(jsonObject.getString("type"));
                        trainTicket.setCapacity(jsonObject.getString("capacity"));
                        trainTicket.setCoupeCapacity(jsonObject.getString("coupe_capacity"));
                        trainTicket.setPrice(jsonObject.getString("price"));
                        trainTickets.add(trainTicket);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    recyclerView.setAdapter(new TrainTicketAdapter(DetailActivity.this,trainTickets));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG", "onErrorResponse: "+error.toString());
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    private void getAllBusTickets(String origin,String destination,String date) {
        String url="https://ns20.ir/alibaba/bus.php?origin="+origin+"&destination="+destination+"&date="+date;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i <response.length() ; i++) {
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        BusTicket busTicket=new BusTicket();
                        busTicket.setId(jsonObject.getString("id"));
                        busTicket.setTicketId(jsonObject.getString("ticket_id"));
                        busTicket.setOrigin(jsonObject.getString("origin"));
                        busTicket.setDestination(jsonObject.getString("destination"));
                        busTicket.setOriginTerminal(jsonObject.getString("origin_terminal"));
                        busTicket.setDestinationTerminal(jsonObject.getString("destination_terminal"));
                        busTicket.setDate(jsonObject.getString("date"));
                        busTicket.setTime(jsonObject.getString("time"));
                        busTicket.setType(jsonObject.getString("type"));
                        busTicket.setDistance(jsonObject.getString("distance"));
                        busTicket.setCapacity(jsonObject.getString("capacity"));
                        busTicket.setPrice(jsonObject.getString("price"));


                        String chairs=jsonObject.getString("chairs");
                        JSONArray chairsArray=new JSONArray(chairs);
                        List<Chair> serverChair=new ArrayList<>();
                        for (int j = 0; j <chairsArray.length() ; j++) {
                            JSONObject chairsModel=chairsArray.getJSONObject(j);
                            Chair chair=new Chair();
                            chair.setLeft(chairsModel.getString("left"));
                            chair.setRight(chairsModel.getString("right"));
                            chair.setRightOne(chairsModel.getString("rightOne"));
                            chair.setSituationLeft(chairsModel.getString("situationLeft"));
                            chair.setSituationRight(chairsModel.getString("situationRight"));
                            chair.setSituationOne(chairsModel.getString("situationOne"));
                            serverChair.add(chair);
                        }

                        busTicket.setChairs(serverChair);
                        busTickets.add(busTicket);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setAdapter(new BusTicketAdapter(DetailActivity.this,busTickets));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG", "onErrorResponse: "+error.toString());
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(DetailActivity.this);
        requestQueue.add(jsonArrayRequest);
    }
}