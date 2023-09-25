package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.CitiesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CitiesActivity extends AppCompatActivity {

    ImageView imgBack;
    EditText edtSearch;
    RecyclerView recyclerView;
    private static final String url="https://ns20.ir/alibaba/getcity.php";
    List<String> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        setupViews();
        getAllCity();
    }

    private void getAllCity() {
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i <response.length() ; i++) {
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        String city=jsonObject.getString("title");
                        cities.add(city);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setAdapter(new CitiesAdapter(cities, new CitiesAdapter.OnCitySelected() {
                    @Override
                    public void onSelected(String city) {
                        Intent intent=new Intent();
                        intent.putExtra("city",city);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                }));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void setupViews() {
        cities=new ArrayList<>();
        imgBack=findViewById(R.id.img_navigationitem_icon);
        edtSearch=findViewById(R.id.edt_cities_search);
        recyclerView=findViewById(R.id.rv_cities_city);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}