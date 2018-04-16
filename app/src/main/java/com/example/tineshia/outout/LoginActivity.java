package com.example.tineshia.outout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
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
import com.example.tineshia.outout.sql.DatabaseHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity implements View.OnClickListener {
    private final Activity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;
    private AppCompatButton appCompatButtonRegister;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private ProgressDialog nDialog;

    private String token = "zDcUlI2Sbb9rN9Coq5La";
    private String api_cuser = "http://outout.isjeff.com/api/c_user.php?token=" + token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        checkLogin();
        initViews();
        initListeners();
        initObjects();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

    /**
     * This method is to initialize views
     */


    public void saveLogin(){
        SharedPreferences save_loginState = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor s_editor = save_loginState.edit();
        s_editor.putString("email", textInputEditTextEmail.getText().toString());
        s_editor.putString("state", "2");
        s_editor.apply();

    }

    public void checkLogin(){
        SharedPreferences check_loginState = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String email = check_loginState.getString("email", null);
        String state = check_loginState.getString("state", null);

        //If already logged in
        if(email != null){
            if(state.equals("2") || state.equals("3")){
                Intent toMap = new Intent(activity, MapsActivity.class);
                startActivity(toMap);
            }

        }

        //if didn't login
        else if(email == null){
            Toast.makeText(this, "Please Log in first", Toast.LENGTH_SHORT).show();
        }

        //if didn't finished user creation
        else if(state.equals("1")){
            Intent toUC = new Intent(activity, userCreation.class);
            startActivity(toUC);
        }

    }


    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
        appCompatButtonRegister.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.appCompatButtonRegister:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.textViewLinkRegister:
                //Navigate to MainActivity temporarily
                Intent intentMain = new Intent (getApplicationContext(), MapsActivity.class);
                startActivity(intentMain);
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {

        showLoading();
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }

        request();
    }

    public void request(){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request VENUE LIST
        StringRequest check_user = new StringRequest
                (Request.Method.POST, api_cuser, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        nDialog.dismiss();

                        if(response.contains("pass")){
                            //Save login state
                            saveLogin();

                            //To map
                            toMap();
                        }

                        else if(response.contains("epsw")){
                            Snackbar.make(nestedScrollView, "Wrong Username or Password.", Snackbar.LENGTH_LONG).show();
                        }

                        else if(response.contains("no")){
                            Snackbar.make(nestedScrollView, "Wrong Username or Password.", Snackbar.LENGTH_LONG).show();
                        }

                        else if(response.contains("empty")){
                            Snackbar.make(nestedScrollView, "Check your input field.", Snackbar.LENGTH_LONG).show();
                        }

                        else{
                            Snackbar.make(nestedScrollView, "Check Internet connection.", Snackbar.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        nDialog.dismiss();
                        Snackbar.make(nestedScrollView, "Check Internet connection.", Snackbar.LENGTH_LONG).show();
                    }
                }){
            @Override
            //POST ITEM
            protected Map<String, String> getParams() {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", textInputEditTextEmail.getText().toString().trim());

                //Get user password ready to be encypted
                String psw_before_en = textInputEditTextPassword.getText().toString().trim();

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
        queue.add(check_user);

    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }

    public void toMap(){
        Intent toMap = new Intent(activity, MapsActivity.class);
        toMap.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
        startActivity(toMap);
    }

    public void showLoading(){
        nDialog = new ProgressDialog(LoginActivity.this);
        nDialog.setMessage("Log in in process...");
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