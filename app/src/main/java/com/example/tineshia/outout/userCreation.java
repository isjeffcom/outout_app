package com.example.tineshia.outout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tapadoo.alerter.Alerter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class userCreation extends Activity {

    private ProgressDialog nDialog;

    private static final String token = "zDcUlI2Sbb9rN9Coq5La";
    private static final String api_uc_tags = "http://outout.isjeff.com/api/data_uc_tags.php?token=" + token;
    private static final String api_up = "http://outout.isjeff.com/api/up_uc_tags.php?token=" + token;


    public int from = 0;
    public int c_view = 1;

    List<String> l_list_tags = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.content_user_creation);

        //Initial first screen display
        request();

        //Identify 2 elements
        ImageButton buttonNext = (ImageButton) findViewById(R.id.buttonGo);
        ImageButton buttonLast = (ImageButton) findViewById(R.id.buttonPre);

        //Set button opacity
        buttonLast.getBackground().setAlpha(150);

        //Go next
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (c_view >= 3) {

                    if(l_list_tags.isEmpty()){
                        Alerter.create(userCreation.this)
                                .setTitle("Let AI Work")
                                .setText("Please select at least one tag\n" +
                                        "Our algorithm will pick up best event for you")
                                .setTitleAppearance(R.style.alertTextTitle)
                                .setTextAppearance(R.style.alertText)
                                .setIconColorFilter(0)
                                .setBackgroundColorRes(R.color.colorPrimary)
                                .showIcon(false)
                                .setDuration(3000)
                                .setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Alerter.hide();
                                    }
                                })
                                .show();
                    }else{
                        update();
                    }



                }else{
                    c_view = c_view + 1;
                    viewController(c_view);
                    progressControl();
                }

            }
        });

        //Click back
        buttonLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //ImageButton buttonLast = (ImageButton) findViewById(R.id.buttonPre);

                if(c_view > 1){
                    c_view = c_view - 1;
                    viewController(c_view);
                    progressControl();
                }else{
                    progressControl();
                }

            }
        });


    }

    public void request(){


        //Initial dynamic inflater layout.
        final LayoutInflater vi = (LayoutInflater) getLayoutInflater();

        //Layout container
        final ViewGroup container = (ViewGroup) findViewById(R.id.selection_container);

        //Remove all content before render
        container.removeAllViews();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request VENUE LIST
        JsonArrayRequest request_tags = new JsonArrayRequest
                (Request.Method.GET, api_uc_tags, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i = 0; i< response.length(); i++){

                            JSONObject object = (JSONObject) response.opt(i);

                            try{
                                //Layout single template
                                View single = vi.inflate(R.layout.usc_selections, null);


                                //Find elements under template
                                LinearLayoutCompat usc_all = (LinearLayoutCompat) single.findViewById(R.id.usc_all);
                                TextView usc_name = (TextView) single.findViewById(R.id.usc_t_name);
                                SimpleDraweeView usc_image = (SimpleDraweeView) single.findViewById(R.id.usc_t_img);

                                //Set Text
                                usc_name.setText(object.getString("name"));

                                //Set Tag
                                usc_all.setTag(object.getString("id"));
                                usc_name.setTag(object.getString("onpage"));


                                //Set image using fresco
                                Uri c_imageUrl = Uri.parse(object.getString("icon"));
                                usc_image.setImageURI(c_imageUrl);

                                //Bleach icon
                                usc_image.getDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP );

                                container.addView(single, i);

                                viewController(c_view);

                            }catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(userCreation.this, "Check your internet connection.", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(request_tags);
    }

    //No StatusBar
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

    //Control the displaying content for page
    public void viewController(int view_count){

        //Layout container
        ViewGroup container = (ViewGroup) findViewById(R.id.selection_container);

        //Get child count
        int childCount = container.getChildCount();

        for(int i=0;i<childCount;i++){

            //Get current child
            View view = container.getChildAt(i);

            //Get tag
            LinearLayoutCompat the_view = view.findViewById(R.id.usc_all);
            AppCompatTextView the_view_onpage_text = the_view.findViewById(R.id.usc_t_name);

            Object vtag_object = the_view_onpage_text.getTag();
            String vtag = vtag_object.toString();

            //Display or not
            if(vtag.equals(Integer.toString(view_count))){
                the_view.setVisibility(View.VISIBLE);
            }else{
                the_view.setVisibility(View.GONE);
            }
        }


    }

    //Progress bar and back button control
    public void progressControl(){
        ImageButton buttonNext = (ImageButton) findViewById(R.id.buttonGo);
        ImageButton buttonLast = (ImageButton) findViewById(R.id.buttonPre);
        ProgressBar progress = (ProgressBar) findViewById(R.id.determinateBar);
        switch (c_view) {
            case 1:
                progress.setProgress(33);
                buttonLast.getBackground().setAlpha(150);
                break;
            case 2:
                progress.setProgress(66);
                buttonLast.getBackground().setAlpha(255);
                buttonNext.getBackground().setAlpha(255);
                break;
            case 3:
                progress.setProgress(95);
                buttonLast.getBackground().setAlpha(255);
                //Make sure user has fill in

                if(l_list_tags.isEmpty()){
                    buttonNext.getBackground().setAlpha(150);
                }else{
                    buttonNext.getBackground().setAlpha(255);
                }

        }
    }

    //Go to Home acitivity
    public void toHome(){

        String fromWhere = getIntent().getStringExtra("from");

        if(fromWhere == null){
            Intent toMap = new Intent(userCreation.this, MapsActivity.class);
            startActivity(toMap);
        }

        else if(fromWhere.equals("1")){
            finish();
        }

    }

    public void update(){

        showLoading();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request VENUE LIST
        StringRequest up_tags = new StringRequest
                (Request.Method.POST, api_up, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        nDialog.dismiss();

                        if(response.contains("successful")){

                            //Save to local
                            SharedPreferences change_state = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor s_editor = change_state.edit();
                            s_editor.putString("tags", l_list_tags.toString());
                            s_editor.apply();

                            //Go to home page
                            toHome();
                        } else{
                            Toast.makeText(userCreation.this, "Check Internet connection.", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        nDialog.dismiss();
                        Toast.makeText(userCreation.this, "Check Internet connection.", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            //POST ITEM
            protected Map<String, String> getParams() {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("uid", getUserId());
                params.put("tags_a", l_list_tags.toString());
                return params;
            }
        };



        // Add the request to the RequestQueue.
        queue.add(up_tags);

    }

    //Add selected item to array
    public void addTag(View v){


        //Get tag
        Object tid_obj = v.getTag();
        String tid = tid_obj.toString();

        //Get two view
        SimpleDraweeView usc_t_img = v.findViewById(R.id.usc_t_img);
        AppCompatTextView usc_t_name = v.findViewById(R.id.usc_t_name);

        //Set view change
        if(tid.contains("selected")){
            String[] tid_with_s = tid.split(",");
            l_list_tags.remove(tid_with_s[1]);

            //Set new tag when remove
            v.setTag(tid_with_s[1]);

            //Set appearance
            v.setBackgroundResource(R.drawable.uc_border);
            usc_t_name.setTextColor(getResources().getColor(R.color.colorAccent));
            usc_t_img.getDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP );

        }else{
            l_list_tags.add(tid);

            //Set new tag when selected
            v.setTag("selected," + tid);

            //Set appearance
            v.setBackgroundResource(R.color.colorPrimary);
            usc_t_img.getDrawable().setColorFilter(0xff000000, PorterDuff.Mode.MULTIPLY );
            usc_t_name.setTextColor(getResources().getColor(R.color.outout_dark));
        }


    }

    public String getUserId(){
        SharedPreferences check_loginState = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String un = check_loginState.getString("uid", null);
        return un;
    }

    public void showLoading(){
        nDialog = new ProgressDialog(userCreation.this);
        nDialog.setMessage("Just wait for a sec.");
        nDialog.setTitle("Loading..");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(false);
        nDialog.show();
    }



}
