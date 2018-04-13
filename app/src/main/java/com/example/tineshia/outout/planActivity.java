package com.example.tineshia.outout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tineshia.outout.model.Plan;
import com.example.tineshia.outout.sql.DatabaseHelper;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tapadoo.alerter.Alerter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class planActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private String token = "zDcUlI2Sbb9rN9Coq5La";
    private String api_venue = "http://outout.isjeff.com/api/data_venue.php?token=" + token;
    private String api_event = "http://outout.isjeff.com/api/data_event.php?token=" + token;


    //Save data from Json
    public final ArrayList<String> d_venue_id = new ArrayList<String>();
    public final ArrayList<String> d_venue_name = new ArrayList<String>();
    ArrayList<String> d_venue_add = new ArrayList<String>();
    ArrayList<String> d_event_name = new ArrayList<String>();
    ArrayList<String> d_event_id = new ArrayList<String>();
    ArrayList<String> d_event_time = new ArrayList<String>();
    ArrayList<String> d_event_date = new ArrayList<String>();
    ArrayList<String> d_event_img = new ArrayList<String>();
    ArrayList<String> d_event_des = new ArrayList<String>();
    ArrayList<String> d_event_toVenueId = new ArrayList<String>();

    List<String>  l_list_event = new ArrayList<String>();

    private Integer requestState = 0;

    private void initObjects() {
        databaseHelper = new DatabaseHelper(planActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);



        //Display alert when first time
        onFirstTimeAlert();

        //Set back button
        TextView backBtn = (TextView) findViewById(R.id.myPlanList_BackBtn);
        backBtn.bringToFront();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToMap(view);
            }
        });

        //Init helper activity
        initObjects();


    }


    public void onStart(){

        super.onStart();

        //Request and render
        request();

        //Get screenshot from the Map activity and set as background
        if(getIntent().hasExtra("forwardBackground")) {
            ConstraintLayout ap_overall = (ConstraintLayout) findViewById(R.id.ap_overall);

            Bitmap ap_overall_bg = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("forwardBackground"),0,getIntent().getByteArrayExtra("forwardBackground").length);
            BitmapDrawable ap_overall_bg_c = new BitmapDrawable(getResources(), ap_overall_bg);
            ap_overall.setBackground(ap_overall_bg_c);
            Drawable background = ap_overall.getBackground();
            background.setColorFilter(0xE6000000, PorterDuff.Mode.XOR );
        }
    }

    public void request(){
        //Init Plan list
        initPlanlist();
        //Initial dynamic inflater layout.
        final LayoutInflater vi = (LayoutInflater) getLayoutInflater();

        //Layout container
        final ViewGroup container = (ViewGroup) findViewById(R.id.myPlanList_Container);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request VENUE LIST
        JsonArrayRequest request_venues = new JsonArrayRequest
                (Request.Method.GET, api_venue, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i = 0; i< response.length(); i++){
                            JSONObject object = (JSONObject) response.opt(i);
                            try {
                                d_venue_id.add(object.getString("id"));
                                d_venue_name.add(object.getString("name"));
                                d_venue_add.add(object.getString("address"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mTextView.setText("That didn't work!" + error);
                    }
                });


        //Event list to String
        String id_where = "&eid=";
        for(int i=0;i < l_list_event.size();i++){
            if(i == l_list_event.size() - 1){
                id_where = id_where + l_list_event.get(i);
            }else{
                id_where = id_where + l_list_event.get(i) + "@";
            }

        }

        Log.v("A", id_where);

        // Request EVENT LIST
        JsonArrayRequest request_events = new JsonArrayRequest
                (Request.Method.GET, api_event + id_where, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        for(int i = 0; i< response.length(); i++){
                            JSONObject object = (JSONObject) response.opt(i);

                            try {

                                //d_event_name.add(object.getString("name"));
                                //d_event_time.add(object.getString("time"));
                                //d_event_date.add(object.getString("date"));
                                //d_event_img.add(object.getString("img"));
                                //d_event_des.add(object.getString("des"));
                                //d_event_toVenueId.add(object.getString("toVenue"));

                                //Layout single template
                                View card = vi.inflate(R.layout.activity_plan_card, null);

                                //Restore margin left attribute
                                LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                                layoutParams.setMargins(50, 30,50, 30);
                                card.setLayoutParams(layoutParams);


                                //Find elements under template
                                TextView c_title = (TextView) card.findViewById(R.id.p_c_title);
                                TextView c_time = (TextView) card.findViewById(R.id.p_c_time);

                                SimpleDraweeView c_img = (SimpleDraweeView) card.findViewById(R.id.p_c_img);

                                //Set Text
                                c_time.setText(getVenue(object.getString("toVenue")) + " | " + object.getString("time"));
                                c_title.setText(object.getString("name"));


                                //Set image using fresco
                                Uri c_imageUrl = Uri.parse(object.getString("img"));
                                c_img.setImageURI(c_imageUrl);

                                //Ready to set tag
                                AppCompatButton p_c_addBtn = (AppCompatButton) card.findViewById(R.id.p_c_addBtn);
                                CardView p_c_single = (CardView) card.findViewById(R.id.p_c_single);

                                TextView pCE_location = (TextView) card.findViewById(R.id.pCE_location);
                                TextView pCE_date = (TextView) card.findViewById(R.id.pCE_date);
                                TextView pCE_time = (TextView) card.findViewById(R.id.pCE_time);
                                TextView pCE_des = (TextView) card.findViewById(R.id.pCE_des);

                                pCE_location.setText(getVenueAdd(object.getString("toVenue")));
                                pCE_date.setText(object.getString("date"));
                                pCE_time.setText(object.getString("time"));
                                pCE_des.setText(object.getString("des"));

                                //Set tag
                                p_c_addBtn.setTag(object.getString("id"));
                                p_c_single.setTag(object.getString("id"));

                                LinearLayoutCompat eListner = (LinearLayoutCompat) card.findViewById(R.id.toExpandListener);
                                LinearLayoutCompat mI_container = (LinearLayoutCompat) card.findViewById(R.id.more_info_container);
                                eListner.setTag(object.getString("id"));
                                mI_container.setTag(object.getString("id"));

                                //Add view
                                container.addView(card, i);

                                //Set request state to finished
                                requestState = 1;

                                //mTextView2.setText("Events is: "+ d_venue_name);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mTextView.setText("That didn't work!" + error);
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(request_venues);
        queue.add(request_events);
        queue.start();

    }

    public void removeFromPlanList(View v){



        Object eid_obj = v.getTag();
        String eid = eid_obj.toString();
        l_list_event.removeAll(Arrays.asList(eid));

        if(l_list_event.size() == 1){
            l_list_event.clear();
        }

        //create or update event list database by current date
        //Log.v("a", cD());

        ViewGroup container = (ViewGroup) findViewById(R.id.myPlanList_Container);
        CardView sItem = (CardView) container.findViewWithTag(eid);
        container.removeView(sItem);

        editSingleEvent(cD());

        List<Plan> planList = databaseHelper.getPlan(cD());

        String tempEid = planList.get(0).getEidArr();


    }

    public void editSingleEvent(String date){

        if(databaseHelper.checkPlan(date)){

            //If eventList already in database
            databaseHelper.updatePlan(date, l_list_event.toString());
            Log.v("a","a");

        }else{

            //If not in database
            databaseHelper.addPlan(date, l_list_event.toString());
            Log.v("a","b");
        }

        //re render cards to view

    }

    public void clickToExpand(View v){



        //Get tag
        Object eid_obj = v.getTag();
        String eid_string = eid_obj.toString();
        //int eid = Integer.parseInt(eid_string);

        LinearLayoutCompat expandLayout = (LinearLayoutCompat) findViewById(R.id.more_info_container).findViewWithTag(eid_string);
        expandLayout.setVisibility(View.VISIBLE);



    }

    public void clickToEBack(View v){


        //Get tag
        //Object eid_obj = v.getTag();
        //String eid_string = eid_obj.toString();
        //int eid = Integer.parseInt(eid_string);

        //LinearLayoutCompat expandLayout = (LinearLayoutCompat) findViewById(R.id.more_info_container).findViewWithTag(eid_string);
        v.setVisibility(View.GONE);



    }

    public void initPlanlist(){
        //Init Plan list
        if(databaseHelper.checkPlan(cD())){
            List<Plan> planList = databaseHelper.getPlan(cD());

            String planList_string = planList.get(0).getEidArr();
            planList_string = planList_string.substring(1, planList_string.length()-1);
            planList_string = planList_string.replaceAll(" +","");

            List<String> planList_after = new ArrayList<String>(Arrays.asList(planList_string.split(",")));
            for(int cp = 0; cp < planList_after.size();cp++){
                String toInsert = planList_after.get(cp).replaceAll(",","");
                l_list_event.add(planList_after.get(cp));
            }
            l_list_event = planList_after;

        }

    }





    public String getVenue(String thisId){
        String res = null;
        for(int i=0; i < d_venue_id.size(); i++){
            if(thisId.equals(d_venue_id.get(i))){
                res = d_venue_name.get(i);
            }
        }

        return res;
    }

    public String getVenueAdd(String thisId){
        String res = null;
        for(int i=0; i < d_venue_id.size(); i++){
            if(thisId.equals(d_venue_id.get(i))){
                res = d_venue_add.get(i);
            }
        }

        return res;
    }



    public String cD(){
        //Initial date and get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int mouth = calendar.get(Calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);

        String s_year = Integer.toString(year);
        String s_mouth = Integer.toString(mouth);
        String s_day = Integer.toString(day);

        String e_date = s_day + "-" + s_mouth + "-" + s_year;
        return e_date;
    }

    public void backToMap(View view){
        finish();
    }

    public void onFirstTimeAlert(){
        SharedPreferences check_loginState = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String state = check_loginState.getString("state", null);


        if(state.equals("2")){
            Alerter.create(this)
                    .setTitle("Review your unbeatable night out below.")
                    .setText("\n\n\n" +"Remember to share \nyour experience with \nfriends on social media \nto gain diamonds!")
                    .setTitleAppearance(R.style.alertText_plan_title)
                    .setTextAppearance(R.style.alertText_plan_title)
                    .setIconColorFilter(0)
                    .setBackgroundResource(R.drawable.alert_bg_gift)
                    .showIcon(false)
                    .enableInfiniteDuration(true)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Alerter.hide();
                            /*SharedPreferences change_state = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor s_editor = change_state.edit();
                            s_editor.putString("state", "3");
                            s_editor.apply();*/
                        }
                    })
                    .show();
        }


    }

}