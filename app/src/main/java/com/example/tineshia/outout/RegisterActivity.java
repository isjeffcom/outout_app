package com.example.tineshia.outout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tineshia.outout.helpers.InputValidation;
import com.example.tineshia.outout.model.User;
import com.example.tineshia.outout.sql.DatabaseHelper;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends Activity implements View.OnClickListener {

    private final Activity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton appCompatButtonLogin;
    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    private RequestQueue queue;
    private ProgressDialog nDialog;

    private String token = "zDcUlI2Sbb9rN9Coq5La";
    private String api_reg = "http://outout.isjeff.com/api/up_reg.php?token=" + token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

    public void saveReg(String uid){
        SharedPreferences save_loginState = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor s_editor = save_loginState.edit();
        s_editor.putString("email", textInputEditTextEmail.getText().toString().trim());
        s_editor.putString("username", textInputEditTextName.getText().toString().trim());
        s_editor.putString("uid", uid);
        s_editor.putString("state", "1");
        s_editor.apply();


        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
    }

    public void toUserCreation(){
        SharedPreferences check_loginState = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String email = check_loginState.getString("email", null);
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        if(email != null){
            Intent toUC = new Intent(activity, userCreation.class);
            startActivity(toUC);
        }
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
        appCompatButtonLogin.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatButtonLogin:
                // Navigate to LoginActivity
                finish();
                break;

            case R.id.appCompatTextViewLoginLink:
                // Navigate to LoginActivity
                finish();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {

        showLoading();

        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());
            request();


            //toUserCreation();

            //databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            //Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            //emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    public void request(){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request VENUE LIST
        StringRequest up_new_user = new StringRequest
                (Request.Method.POST, api_reg, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        nDialog.dismiss();

                        if(response.contains("regs")){
                            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
                            String[] un_before = response.split(",");
                            String get_userid = un_before[1];
                            saveReg(get_userid);
                            toUserCreation();
                        }

                        else if(response.contains("exist")){
                            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
                        }

                        else if(response.contains("dbe")){
                            Snackbar.make(nestedScrollView, "Check Internet connection.", Snackbar.LENGTH_LONG).show();
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
                        //mTextView.setText("That didn't work!" + error);
                        Snackbar.make(nestedScrollView, "Check Internet connection.", Snackbar.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    //POST ITEM
                    protected Map<String, String> getParams() {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("username", textInputEditTextName.getText().toString().trim());
                        params.put("email", textInputEditTextEmail.getText().toString().trim());

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
        queue.add(up_new_user);

    }

    public void showLoading(){
        nDialog = new ProgressDialog(RegisterActivity.this);
        nDialog.setMessage("Creating your account...");
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