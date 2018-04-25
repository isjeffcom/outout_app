package com.example.tineshia.outout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
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
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class profileActivity extends AppCompatActivity {

    private ProgressDialog nDialog;
    private final String token = "zDcUlI2Sbb9rN9Coq5La";
    private final String api_tags = "http://outout.isjeff.com/api/data_tags.php?token=" + token;
    private final String api_plan = "http://outout.isjeff.com/api/data_plan_list.php?token=" + token;

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

        final ViewGroup pa_container = (ViewGroup) findViewById(R.id.previousActivityContainer);


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





        // Request VENUE LIST
        JsonArrayRequest get_pa = new JsonArrayRequest
                (Request.Method.POST, api_plan+"&uid="+getUid(), null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        pa_container.removeAllViews();

                        for(int i=0;i<response.length();i++){
                            JSONObject object = (JSONObject) response.opt(i);

                            try{

                                //Layout single template
                                View s = vi.inflate(R.layout.activity_plan_card_profile, null);

                                //Restore margin left attribute
                                LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                                layoutParams.setMargins(50, 30,50, 30);
                                s.setLayoutParams(layoutParams);

                                AppCompatTextView s_name = (AppCompatTextView) s.findViewById(R.id.p_c_title);
                                s_name.setText(object.getString("date"));

                                SimpleDraweeView c_img = (SimpleDraweeView) s.findViewById(R.id.p_c_img);


                                //Set image using fresco
                                Uri c_imageUrl = Uri.parse(object.getString("img"));
                                c_img.setImageURI(c_imageUrl);

                                pa_container.addView(s, i);

                            }catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(profileActivity.this, "We can't find anything here.", Toast.LENGTH_SHORT).show();
                            }

                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(profileActivity.this, "We can't find anything here.", Toast.LENGTH_SHORT).show();
                    }
                });



        // Add the request to the RequestQueue.
        queue.add(get_tags);
        queue.add(get_pa);

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
