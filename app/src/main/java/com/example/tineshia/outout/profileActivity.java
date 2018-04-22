package com.example.tineshia.outout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class profileActivity extends AppCompatActivity {

    private ProgressDialog nDialog;
    private final String token = "zDcUlI2Sbb9rN9Coq5La";
    private final String api_tags = "http://outout.isjeff.com/api/data_tags.php?token=" + token;

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

    public void onStart(){
        super.onStart();
        getTag();
    }

    public void getTag(){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        final LayoutInflater vi = (LayoutInflater) getLayoutInflater();

        final ViewGroup container = (ViewGroup) findViewById(R.id.tag_container);


        container.removeAllViews();

        // Request VENUE LIST
        JsonArrayRequest get_tags = new JsonArrayRequest
                (Request.Method.POST, api_tags+"&uid="+getUid(), null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i=0;i<response.length();i++){
                            JSONObject object = (JSONObject) response.opt(i);

                            try{

                                //Layout single template
                                View single = vi.inflate(R.layout.activity_profile_tag, null);
                                AppCompatTextView single_text = (AppCompatTextView) single.findViewById(R.id.tag_text);
                                single_text.setText(object.getString("name"));

                                container.addView(single, i);

                            }catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(profileActivity.this, "Check your internet connection.", Toast.LENGTH_SHORT).show();
                            }

                        }

                        AppCompatImageButton editBtn = new AppCompatImageButton(profileActivity.this);
                        editBtn.setBackgroundResource(R.drawable.edit);

                        //Set user profile icon listener
                        editBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent toUc = new Intent(profileActivity.this, userCreation.class);
                                toUc.putExtra("from", "1");
                                startActivity(toUc);

                            }
                        });

                        container.addView(editBtn);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(profileActivity.this, "Check your internet connection.", Toast.LENGTH_SHORT).show();
                    }
                });



        // Add the request to the RequestQueue.
        queue.add(get_tags);

    }


    public void toHome(View v){
        finish();
    }

    public void toEditProfile(View v){
        Intent toMap = new Intent(this, editProfileActivity.class);
        startActivity(toMap);
    }

    public String getUid(){
        SharedPreferences check_loginState = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String uid = check_loginState.getString("uid", null);
        return uid;
    }

    public void toUc(View v){
        Intent toUc = new Intent(this, userCreation.class);
        toUc.putExtra("from", "1");
        startActivity(toUc);
    }


    public void showLoading(){
        nDialog = new ProgressDialog(profileActivity.this);
        nDialog.setMessage("Wait a sec...");
        nDialog.setTitle("Loading..");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(false);
        nDialog.show();
    }
}
