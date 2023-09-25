package com.example.myapplication.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class LoginDialog extends DialogFragment {
    View view;
    EditText edtEmail, edtPass;
    Button btnSignUp, btnLogin;
    OnSignUpClicked onSignUpClicked;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        view = LayoutInflater.from(getContext()).inflate(R.layout.login_dialog, null);
        setupViews();
        builder.setView(view);
        return builder.create();
    }

    private void setupViews() {
        btnLogin = view.findViewById(R.id.btn_loginDialog_login);
        btnSignUp = view.findViewById(R.id.btn_loginDialog_signup);
        edtEmail = view.findViewById(R.id.edt_loginDialog_email);
        edtPass = view.findViewById(R.id.edt_loginDialog_pass);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();
                userSignUp(email, pass);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(edtEmail.getText().toString(), edtPass.getText().toString());
            }
        });
    }

    private void login(final String email, final String pass) {
        String url = "https://ns20.ir/alibaba/login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("not found")) {
                    Toast.makeText(getContext(), "پست الکترونیک یا رمزعبور اشتباه است", Toast.LENGTH_SHORT).show();
                } else {
                    onSignUpClicked.onClicked(response);
                    dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG", "onErrorResponse: " + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("pass", pass);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    public void setOnSignUpClicked(OnSignUpClicked onSignUpClicked) {
        this.onSignUpClicked = onSignUpClicked;
    }

    private void userSignUp(final String email, final String pass) {

        String url = "https://ns20.ir/alibaba/signup.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onSignUpClicked.onClicked(response);
                dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG", "onErrorResponse: " + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("email", email);
                param.put("pass", pass);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    public interface OnSignUpClicked {
        void onClicked(String email);
    }
}
