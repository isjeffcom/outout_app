package com.example.tineshia.outout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class profileActivity extends AppCompatActivity {

    private ProgressDialog nDialog;
    private final String token = "zDcUlI2Sbb9rN9Coq5La";
    private final String api_data_u = "http://outout.isjeff.com/api/data_u.php?token=" + token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences check_loginState = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String email = check_loginState.getString("email", null);
        String username = check_loginState.getString("username", null);

        AppCompatTextView emailText = (AppCompatTextView) findViewById(R.id.profile_email);
        AppCompatTextView usernameText = (AppCompatTextView) findViewById(R.id.profile_username);
        emailText.setText(email);
        usernameText.setText(username);


    }



    public void toHome(View v){
        finish();
    }

    public void toEditProfile(View v){
        Intent toMap = new Intent(this, editProfileActivity.class);
        startActivity(toMap);
    }

    public void showLoading(){
        nDialog = new ProgressDialog(profileActivity.this);
        nDialog.setMessage("Getting or Updating Your Data......");
        nDialog.setTitle("Loading..");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(false);
        nDialog.show();
    }
}
