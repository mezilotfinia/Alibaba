package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.ScrollingTabContainerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Model.Profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {
    ImageView imgBack;
    EditText edtName,edtFamily,edtCode,edtMobile;
    TextView txtBirthday;
    AppCompatSpinner spinner;
    Button btnSave;
    String email;
    ArrayList<String> manOrWomanList;
    String manOrWomanSelected="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setupViews();
        email=getIntent().getExtras().getString("email");
    }

    private void setupViews() {

        imgBack=findViewById(R.id.img_editProfile_back);
        edtName=findViewById(R.id.edt_editProfile_name);
        edtFamily=findViewById(R.id.edt_editProfile_family);
        edtCode=findViewById(R.id.edt_editProfile_code);
        edtMobile=findViewById(R.id.edt_editProfile_mobile);
        txtBirthday=findViewById(R.id.edt_editProfile_birthday);
        spinner=findViewById(R.id.sp_editProfile_spinner);
        btnSave=findViewById(R.id.btn_editProfile_save);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileData(email,edtName.getText().toString(),edtFamily.getText().toString(),txtBirthday.getText().toString(),edtCode.getText().toString(),edtMobile.getText().toString(),manOrWomanSelected);
            }
        });
        manOrWomanList=new ArrayList<>();
        manOrWomanList.add("مرد");
        manOrWomanList.add("زن");
        ArrayAdapter arrayAdapter=new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_spinner_item,manOrWomanList);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                manOrWomanSelected=manOrWomanList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        txtBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


    }
    private void saveProfileData(final String email, final String name, final String family, final String birthday, final String code, final String mobile, final String manOrWoman){
        String url="https://ns20.ir/alibaba/updateuser.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("ok")){
                    Intent intent=new Intent();
                    Profile mobil=new Profile();
                    mobil.setTitle("شماره همراه:");
                    mobil.setValue(mobile);
                    intent.putExtra("mobile",mobil);

                    Profile mw=new Profile();
                    mw.setTitle("جنسیت:");
                    mw.setValue(manOrWoman);
                    intent.putExtra("mw",mw);

                    Profile codeP=new Profile();
                    codeP.setTitle("کدملی:");
                    codeP.setValue(code);
                    intent.putExtra("code",codeP);

                    Profile dateP=new Profile();
                    dateP.setTitle("تاریخ تولد:");
                    dateP.setValue(birthday);
                    intent.putExtra("date",dateP);
                    setResult(RESULT_OK,intent);
                    finish();

                }else {
                    Toast.makeText(EditProfileActivity.this,"مشکل در ثبت اطلاعات کاربری",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG", "onErrorResponse: "+error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("email",email);
                params.put("name",name);
                params.put("family",family);
                params.put("birthday",birthday);
                params.put("code",code);
                params.put("mobile",mobile);
                params.put("manorwoman",manOrWoman);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}