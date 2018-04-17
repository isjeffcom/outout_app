package com.example.tineshia.outout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tineshia.outout.helpers.InputValidation;
import com.tapadoo.alerter.Alerter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class editProfileActivity extends AppCompatActivity {

    private final String token = "zDcUlI2Sbb9rN9Coq5La";
    private final String api_uprofile = "http://outout.isjeff.com/api/up_uprofile.php?token=" + token;
    private final String api_data_u = "http://outout.isjeff.com/api/data_u.php?token=" + token;

    private InputValidation inputValidation;

    private ProgressDialog nDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);



        SharedPreferences check_loginState = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String email = check_loginState.getString("email", null);

        request();

        AppCompatTextView ph_email = (AppCompatTextView) findViewById(R.id.placeHolderEmail);
        ph_email.setText(email);



    }

    public void request(){
        showLoading();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request VENUE LIST
        StringRequest get_username = new StringRequest
                (Request.Method.POST, api_data_u, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        nDialog.dismiss();
                        if(response.contains("dbe")){
                            Toast.makeText(editProfileActivity.this, "Unknow Error", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        TextInputEditText tE_name = (TextInputEditText) findViewById(R.id.inputName);
                        tE_name.setText(response.toString());

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        nDialog.dismiss();
                        Toast.makeText(editProfileActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            //POST ITEM
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences check_loginState = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                String email = check_loginState.getString("email", null);
                params.put("email", email);

                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(get_username);
    }

    public void update_profile(View v){

        update();
    }

    public void update(){

        showLoading();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request VENUE LIST
        StringRequest update_user_detail = new StringRequest
                (Request.Method.POST, api_uprofile, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        nDialog.dismiss();

                        if(response.contains("successful")){
                            Alerter.create(editProfileActivity.this)
                                    .setTitle("Update successful")
                                    .setText("You have successfully update your information. \nPlease keep your account detail safe. Thanks for your support.")
                                    .setTitleAppearance(R.style.alertText_plan_title)
                                    .setTextAppearance(R.style.alertText_plan_title)
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
                        }

                        else if(response.contains("empty")){
                            Alerter.create(editProfileActivity.this)
                                    .setTitle("Check your input")
                                    .setText("We currently only support change both username and password at once. \nSorry about your inconvenience.")
                                    .setTitleAppearance(R.style.alertText_plan_title)
                                    .setTextAppearance(R.style.alertText_plan_title)
                                    .setBackgroundColorRes(R.color.outout_alert_red)
                                    .showIcon(false)
                                    .setDuration(3000)
                                    .setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Alerter.hide();
                                        }
                                    })
                                    .show();
                        } else{
                            Toast.makeText(editProfileActivity.this, "Please try later.", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        nDialog.dismiss();
                        Toast.makeText(editProfileActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();

                    }
                }){
            @Override
            //POST ITEM
            protected Map<String, String> getParams() {
                TextInputEditText tE_name = (TextInputEditText) findViewById(R.id.inputName);
                TextInputEditText tE_psw = (TextInputEditText) findViewById(R.id.inputPsw);
                Map<String, String>  params = new HashMap<String, String>();
                SharedPreferences check_loginState = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                String email = check_loginState.getString("email", null);

                //Log.e("username",tE_name.getText().toString().trim());
                params.put("username", tE_name.getText().toString().trim());
                params.put("email", email);
                String psw_before_en = tE_psw.getText().toString().trim();

                /*
                 * Be aware the current prototype used MD5 Encryption is not the best solution
                 * For a more security data protection
                 * Please change the way of encryption or add other step of account verify
                 * Also the API should used HTTPS/SSH encryption
                 * */

                //MD5 Encryption Psw
                params.put("psw", md5(psw_before_en));

                return params;
            }
        };



        // Add the request to the RequestQueue.
        queue.add(update_user_detail);

    }

    public void toProfile(View v){
        finish();
    }

    public void showLoading(){
        nDialog = new ProgressDialog(editProfileActivity.this);
        nDialog.setMessage("Getting or Updating Your Data......");
        nDialog.setTitle("Loading..");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(false);
        nDialog.show();
    }

    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
