package com.example.tineshia.outout;

import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;
import com.example.tineshia.outout.model.Plan;
import com.example.tineshia.outout.sql.DatabaseHelper;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaeger.library.StatusBarUtil;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.tapadoo.alerter.Alerter;
import com.tarek360.instacapture.Instacapture;
import com.tarek360.instacapture.listener.SimpleScreenCapturingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static android.view.View.GONE;
import static java.util.Arrays.asList;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private HorizontalScrollView home_EventCard;
    private AppCompatImageButton home_burgerMenu;
    private AppCompatImageButton home_userProfile;
    private LinearLayout home_searchBar;
    private ProgressDialog nDialog;

    private DatabaseHelper databaseHelper;

    private int mColor;

    private AccountHeader headerResult = null;
    private Drawer result = null;
    private View slideView;


    private DatePickerDialog.OnDateSetListener mDatesetListener;

    private static final String token = "zDcUlI2Sbb9rN9Coq5La";
    private static final String api_venue = "http://outout.isjeff.com/api/data_venue.php?token=" + token;
    private static final String api_event = "http://outout.isjeff.com/api/data_event.php?token=" + token;

    private static Context mContext;
    public String selected_date;

    List<Marker> map_markers = new ArrayList<>();

    public static Context getContext() {
        return mContext;
    }

    private int requestState = 0;


    //View
    //LinearLayoutCompat cardContainer = (LinearLayoutCompat) findViewById(R.id.event_card_container);
    //HorizontalScrollView cardScrollView = (HorizontalScrollView) findViewById(R.id.event_card_scrollview);


    Toolbar toolbar;

    //Save data from Json
    ArrayList<String> d_venue_id = new ArrayList<String>();
    ArrayList<String> d_venue_name = new ArrayList<String>();
    ArrayList<String> d_venue_gl_la = new ArrayList<String>();
    ArrayList<String> d_venue_gl_lo = new ArrayList<String>();
    ArrayList<String> d_event_name = new ArrayList<String>();
    ArrayList<String> d_event_id = new ArrayList<String>();
    ArrayList<String> d_event_time = new ArrayList<String>();
    ArrayList<String> d_event_date = new ArrayList<String>();
    ArrayList<String> d_event_img = new ArrayList<String>();
    ArrayList<String> d_event_toVenueId = new ArrayList<String>();


    List<String>  l_list_event = new ArrayList<String>();

    private void initObjects() {
        databaseHelper = new DatabaseHelper(MapsActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Set transparent status bar and navigation bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//Set Status Bar transparent
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//Set Navigation Bar transparent
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        selected_date = cD();
        //Set status bar transparent
        //StatusBarUtil.setTransparent(MapsActivity.this);

        setContentView(R.layout.activity_maps);

        //Init fresco image loader *From Facebook Fresco OSP
        Fresco.initialize(this);

        //Init some basic runtime object
        initObjects();

        //Init toolbar
        toolbar =(Toolbar) findViewById(R.id.toolbar) ;

        //Set Drawer
        SecondaryDrawerItem dI_i_settings = new SecondaryDrawerItem().withIdentifier(1).withName(R.string.settings_drawer_title);

        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder().withActivity(this).withHeaderBackground(R.drawable.drawer_bg)
                .addProfiles(
                        new ProfileDrawerItem().withName("Jeff").withEmail(getUserEmail()).withIcon(R.drawable.c2)
                ).withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener(){
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                }).build();


        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .withActivity(this)
                //.withToolbar(toolbar)
                .addDrawerItems(
                        dI_i_settings
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = null;
                        if(drawerItem.getIdentifier() == 1){
                            intent = new Intent(MapsActivity.this, SettingsActivity.class);
                        }

                        if (intent != null) {
                            MapsActivity.this.startActivity(intent);
                        }

                        return false;

                        // do something with the clicked item :D
                    }
                })
                .build();





        //Bring to front
        home_EventCard = (HorizontalScrollView) findViewById(R.id.event_card_scrollview);
        home_userProfile = (AppCompatImageButton) findViewById(R.id.user_profile_link);
        home_searchBar = (LinearLayout) findViewById(R.id.search_bar);
        AppCompatTextView changeAreaBtn = (AppCompatTextView) findViewById(R.id.changeArea);
        home_EventCard.bringToFront();
        home_userProfile.bringToFront();
        home_searchBar.bringToFront();
        toolbar.bringToFront();
        changeAreaBtn.bringToFront();





        //Link to User profile
        AppCompatImageButton userProfile = (AppCompatImageButton) findViewById(R.id.user_profile_link);

        //Set user profile icon listener
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, ProfileActivity.class);

                if (intent != null) {
                    MapsActivity.this.startActivity(intent);
                }
            }
        });


        //Welcome alert if first login
        onFirstTimeAlert();

        //Request API
        request(selected_date);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    public void onStart(){
        super.onStart();


        new CountDownTimer(15000, 15000) {


            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                Log.e("change area btn run","a");
                AppCompatTextView changeAreaBtn = (AppCompatTextView) findViewById(R.id.changeArea);
                changeAreaBtn.setVisibility(View.GONE);
            }
        }.start();


    }

    public void onResume(){
        //Init planlist
        initPlanlist();
        super.onResume();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng Portsmouth = new LatLng(50.794332, -1.097800);

        mMap.setIndoorEnabled(false);
        mMap.setBuildingsEnabled(false);

        //Set map style
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                //Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            //Log.e(TAG, "Can't find style. Error: ", e);
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(Portsmouth));
    }


    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

    }

    public String getUserEmail(){
        SharedPreferences check_loginState = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String email = check_loginState.getString("email", null);
        return email;
    }

    public void onFirstTimeAlert(){
        SharedPreferences check_loginState = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String state = check_loginState.getString("state", null);

        if(state.equals("1")){
            Alerter.create(this)
                    .setTitle("Welcome Jeff,")
                    .setText("Enjoy Out Out, now available \nin Portsmouth to plan your \nunbeatable night out!" +
                            "\n\n\n" +
                            "Keep an eye out for \nupdates, coming to \nother areas soon!"
                    )
                    .setTitleAppearance(R.style.alertText)
                    .setTextAppearance(R.style.alertText)
                    .setIconColorFilter(0)
                    .setBackgroundResource(R.drawable.alert_bg_city)
                    .showIcon(false)
                    .enableInfiniteDuration(true)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Alerter.hide();
                            SharedPreferences change_state = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor s_editor = change_state.edit();
                            s_editor.putString("state", "2");
                            s_editor.apply();
                        }
                    })
                    .show();
        }


    }

    public void request(String request_date){

        showLoading();

        Log.e("request date", request_date);
        //Initial dynamic inflater layout.
        final LayoutInflater vi = (LayoutInflater) getLayoutInflater();

        //Layout container
        final ViewGroup container = (ViewGroup) findViewById(R.id.event_card_container);

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
                                d_venue_gl_la.add(object.getString("gl_la"));
                                d_venue_gl_lo.add(object.getString("gl_lo"));


                                double gl_la = Double.parseDouble(object.getString("gl_la"));
                                double gl_lo = Double.parseDouble(object.getString("gl_lo"));

                                //mMap.setOnMarkerClickListener(this);

                                Marker venue_marker = mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(gl_la, gl_lo))
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker_white))
                                        .title(object.getString("name")));
                                venue_marker.setTag(object.getString("id"));

                                map_markers.add(venue_marker);




                                //mTextView.setText("Venue is: "+ d_venue_name + d_venue_gl_la +d_venue_gl_lo);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        //Set marker click listener
                        final LinearLayoutCompat cardContainer = (LinearLayoutCompat) findViewById(R.id.event_card_container);
                        //Change card and marker color when click on marker
                        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker m) {
                                //Log.e("a",m.getTag().toString());
                                String tagId = m.getTag().toString();

                                for (Marker marker : map_markers) {
                                    if (marker.getTag().equals(tagId)) {
                                        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker_green));

                                    }else{
                                        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker_white));
                                    }
                                }

                                //m.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker_green));
                                for(int i=0;i<d_venue_id.size();i++){
                                    LinearLayoutCompat cardSingle = (LinearLayoutCompat) cardContainer.findViewWithTag(d_event_toVenueId.get(i));

                                    if(tagId.equals(d_event_toVenueId.get(i))){

                                        cardSingle.setBackground(getResources().getDrawable(R.color.colorPrimary));
                                    }else{
                                        cardSingle.setBackground(getResources().getDrawable(R.color.colorAccent));
                                    }
                                }
                                return true;
                            }
                        });

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!" + error);
            }
        });

        // Request EVENT LIST
        JsonArrayRequest request_events = new JsonArrayRequest
                (Request.Method.GET, api_event+"&rdate=%27"+request_date+"%27", null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        for(int i = 0; i< response.length(); i++){
                            JSONObject object = (JSONObject) response.opt(i);
                            try {

                                //d_event_name.add(object.getString("name"));
                                //d_event_time.add(object.getString("time"));
                                //d_event_date.add(object.getString("date"));
                                //d_event_img.add(object.getString("img"));
                                d_event_toVenueId.add(object.getString("toVenue"));



                                //Layout single template
                                View card = vi.inflate(R.layout.activity_map_card, null);

                                //Restore margin left attribute
                                LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                                layoutParams.setMargins(15, 15,15, 15);
                                card.setLayoutParams(layoutParams);


                                //Find elements under template
                                TextView c_title = (TextView) card.findViewById(R.id.m_c_title);
                                TextView c_time = (TextView) card.findViewById(R.id.m_c_time);
                                SimpleDraweeView c_img = (SimpleDraweeView) card.findViewById(R.id.m_c_img);

                                //Set Text
                                c_time.setText(getVenue(object.getString("toVenue")) + " | " + object.getString("time"));
                                c_title.setText(object.getString("name"));


                                //Set image using fresco
                                Uri c_imageUrl = Uri.parse(object.getString("img"));
                                c_img.setImageURI(c_imageUrl);

                                //Give tag
                                AppCompatButton m_c_addBtn = (AppCompatButton) card.findViewById(R.id.m_c_addBtn);
                                m_c_addBtn.setTag(object.getString("id"));

                                LinearLayoutCompat card_more_info = (LinearLayoutCompat) card.findViewById(R.id.content_container);
                                card_more_info.setTag(object.getString("toVenue"));


                                container.addView(card, i);

                                //view my plan button
                                viewMyPlanBtn();

                                //Update request state to finished
                                requestState = 1;

                                initPlanlist();

                                nDialog.dismiss();

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



    }

    public void selectDate(View v){

        //Initial date from selected date
        Calendar calendar = Calendar.getInstance();
        List<String> date_after = new ArrayList<String>(asList(selected_date.split("-")));

        int year = Integer.parseInt(date_after.get(0));
        int mouth = Integer.parseInt(date_after.get(1));
        int day = Integer.parseInt(date_after.get(2));


        DatePickerDialog dialog = new DatePickerDialog(
                MapsActivity.this,
                android.R.style.Theme_Material_Light_Dialog,
                mDatesetListener,
                year, mouth, day);
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        dialog.show();

        mDatesetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int mouth, int day) {

                String s_mouth = Integer.toString(mouth);
                //if mouth 1-9 convert to 01-09
                if(s_mouth.length() == 1){
                    s_mouth = "0" + s_mouth;
                }
                String current_Date = day + "-" + s_mouth + "-" + year;

                //Update selected date and made request again
                selected_date = current_Date;
                saveSelectedDate(current_Date);
                TextView date_text = (TextView) findViewById(R.id.date_text);
                date_text.setText(current_Date);

                //Clear cardView container before request
                LinearLayoutCompat cardContainer = (LinearLayoutCompat) findViewById(R.id.event_card_container);
                cardContainer.removeAllViews();

                initPlanlist();

                request(selected_date);
            }
        };
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

    public void viewMyPlanBtn(){
        RelativeLayout container = (RelativeLayout) findViewById(R.id.viewMyPlan_Container);

        if(l_list_event.size() > 0){

            if(l_list_event.get(0) == null || l_list_event.get(0) == ""){

                HorizontalScrollView cardScrollView = (HorizontalScrollView) findViewById(R.id.event_card_scrollview);
                setMargins(cardScrollView, 0, 0, 0, 0);
                container.setVisibility(View.GONE);

            }else{
                Button vMP_b = new Button(MapsActivity.this);

                vMP_b.setBackgroundResource(R.color.colorPrimary);
                vMP_b.setText("View My Plan");
                vMP_b.setTextSize(16);
                vMP_b.setTextColor(getResources().getColor(R.color.material_drawer_hint_text));


                RelativeLayout.LayoutParams vMP_b_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                vMP_b.setLayoutParams(vMP_b_params);

                container.addView(vMP_b);

                container.bringToFront();
                container.setVisibility(View.VISIBLE);
                vMP_b.bringToFront();

                vMP_b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openPlan(view);
                    }
                });


                /*AnimatorSet set = new AnimatorSet();
                set.playTogether(
                        Glider.glide(Skill.BounceEaseInOut, 300, ObjectAnimator.ofFloat(cardContainer, "translationY", 0,20))
                );

                set.setDuration(200);
                set.start();*/

                HorizontalScrollView cardScrollView = (HorizontalScrollView) findViewById(R.id.event_card_scrollview);
                setMargins(cardScrollView, 0, 0, 0, 120);
            }

        }else if(l_list_event.size() == 0){

            //HorizontalScrollView cardContainer = (HorizontalScrollView) findViewById(R.id.event_card_scrollview);
            HorizontalScrollView cardScrollView = (HorizontalScrollView) findViewById(R.id.event_card_scrollview);
            setMargins(cardScrollView, 0, 0, 0, 0);
            container.setVisibility(View.GONE);
        }


    }


    public void addToEventList(View v){



        Object eid_obj = v.getTag();
        String eid = eid_obj.toString();
        LinearLayoutCompat cardContainer = (LinearLayoutCompat) findViewById(R.id.event_card_container);
        Button addBtn = (Button) cardContainer.findViewWithTag(eid);


        if(l_list_event.contains(eid)){
            //Do nothing
            l_list_event.remove(eid);
            addBtn.setText("Add");
            //Toast.makeText(this, "exist", Toast.LENGTH_SHORT).show();

        }else{

            if(l_list_event.isEmpty()){
                l_list_event.add(eid);
            }
            else if(l_list_event.get(0) == null || l_list_event.get(0) == ""){

                l_list_event.clear();
                l_list_event.add(eid);
            }else{
                l_list_event.add(eid);
            }

            addBtn.setText("Remove");
            //Toast.makeText(this, eid, Toast.LENGTH_SHORT).show();

        }

        editSingleEvent(selected_date);

        /*List<Plan> planList = databaseHelper.getPlan(cD());

        String tempEid = planList.get(0).getEidArr();

        Log.v("aaaaac:", tempEid.toString());*/

        viewMyPlanBtn();
    }

    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    public void editSingleEvent(String date){


        if(databaseHelper.checkPlan(date)){

            //If eventList already in database
            databaseHelper.updatePlan(date, l_list_event.toString());

        }else{

            //If not in database
            databaseHelper.addPlan(date, l_list_event.toString());
        }

        //re render cards to view

    }

    public void initPlanlist(){

        l_list_event.clear();

        LinearLayoutCompat cardContainer = (LinearLayoutCompat) findViewById(R.id.event_card_container);

        //Init Plan list
        if(databaseHelper.checkPlan(selected_date)){

            List<Plan> planList = databaseHelper.getPlan(selected_date);

            String planList_string = planList.get(0).getEidArr();
            planList_string = planList_string.substring(1, planList_string.length()-1);
            planList_string = planList_string.replaceAll(" +","");
            planList_string = planList_string.replaceAll("","");

            List<String> planList_after = new ArrayList<String>(asList(planList_string.split(",")));
            for(int cp = 0; cp < planList_after.size();cp++){
                String toInsert = planList_after.get(cp).replaceAll(",","");
                l_list_event.add(toInsert);
            }

            Log.v("test when init",l_list_event.toString());
        }

        if(requestState == 1){

            for (int i = 0; i < cardContainer.getChildCount(); i++) {
                View thisView = cardContainer.getChildAt(i);
                View thisAddButton = thisView.findViewById(R.id.m_c_addBtn);
                Object tags_obj = thisAddButton.getTag();
                String tags = tags_obj.toString();
                Button addBtn = (Button) thisAddButton.findViewWithTag(tags);

                if( l_list_event.contains(tags)){
                    addBtn.setText("Remove");
                    //do nothing...
                }else{
                    addBtn.setText("Add");
                }
            }
        }
    }

    public void contentContainerClick(View v){
        String tagId = v.getTag().toString();
        //Set marker click listener
        LinearLayoutCompat cardContainer = (LinearLayoutCompat) findViewById(R.id.event_card_container);



        for (Marker marker : map_markers) {
            if (marker.getTag().equals(tagId)) {
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker_green));
            }else{
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker_white));
            }
        }

        //m.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker_green));
        for(int i=0;i<d_venue_id.size();i++){
            LinearLayoutCompat cardSingle = (LinearLayoutCompat) cardContainer.findViewWithTag(d_event_toVenueId.get(i));

            if(tagId.equals(d_event_toVenueId.get(i))){

                cardSingle.setBackground(getResources().getDrawable(R.color.colorPrimary));
            }else{
                cardSingle.setBackground(getResources().getDrawable(R.color.colorAccent));
            }
        }


    }


    public void openPlan(View view){

        GoogleMap.SnapshotReadyCallback callback = new GoogleMap.SnapshotReadyCallback() {

            @Override
            public void onSnapshotReady(Bitmap snapshot) {
                // TODO Auto-generated method stub
                Bitmap bitmap = snapshot;

                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, bs);
                Intent toPlan = new Intent(MapsActivity.this, planActivity.class);
                toPlan.putExtra("forwardBackground", bs.toByteArray());
                toPlan.putExtra("selectedDate",selected_date);
                MapsActivity.this.startActivity(toPlan);

            }

        };

        mMap.snapshot(callback);

    }

    public void saveSelectedDate(String sd){
        selected_date = sd;
    }



    public String cD(){
        //Initial date and get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int mouth = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(calendar.DAY_OF_MONTH);

        String s_year = Integer.toString(year);
        String s_mouth = Integer.toString(mouth);
        String s_day = Integer.toString(day);

        //if mouth 1-9 convert to 01-09
        if(s_mouth.length() == 1){
            s_mouth = "0" + s_mouth;
        }

        String e_date = s_day + "-" + s_mouth + "-" + s_year;
        return e_date;
    }

    public static boolean isRepeatInCollection(Collection<?> datas) {
        if (datas == null) {// 为空则认为不含重复元素
            return false;
        }
        if (datas instanceof Set) {//如果是set则不含有重复元素
            return false;
        }
        Set<?> noRepeatSet = new HashSet<>(datas);
        return !(datas.size() == noRepeatSet.size());
    }

    public void showLoading(){
        nDialog = new ProgressDialog(MapsActivity.this);
        nDialog.setMessage("Getting data...");
        nDialog.setTitle("Loading..");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(false);
        nDialog.show();
    }

    public void openLocationPicker(View v){
        Alerter.create(this)
                .setTitle("Coming Soon...")
                .setText("\n\n\n" +"We are looking forward \nto bring this services to \nnew area. \nWe coming soon!")
                .setTitleAppearance(R.style.alertText_plan_title)
                .setTextAppearance(R.style.alertText_plan_title)
                .setIconColorFilter(0)
                .setBackgroundResource(R.drawable.alert_bg_city)
                .showIcon(false)
                .setDuration(3000)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Alerter.hide();
                    }
                })
                .show();
    }
















}
