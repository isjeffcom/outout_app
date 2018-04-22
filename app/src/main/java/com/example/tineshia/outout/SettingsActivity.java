package com.example.tineshia.outout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.tineshia.outout.sql.DatabaseHelper;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends Activity {

    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        AppCompatButton logoutButton = (AppCompatButton) findViewById(R.id.appCompatButtonLogout);
        AppCompatTextView settingsTitle = (AppCompatTextView) findViewById(R.id.settingsTitle);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        settingsTitle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                toHome();
            }
        });

        initObjects();
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(SettingsActivity.this);
    }

    public void logout(){
        SharedPreferences check_loginState = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor s_editor = check_loginState.edit();
        s_editor.putString("email", null);
        s_editor.apply();

        cleanPlanList();
        //cleanLogin();

        Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);

        if (intent != null) {
            SettingsActivity.this.startActivity(intent);
        }

    }

    public void cleanPlanList(){
        databaseHelper.cleanPlan();
    }

    public void cleanLogin(){
        SharedPreferences save_loginState = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor s_editor = save_loginState.edit();
        s_editor.putString("email", "");
        s_editor.putString("username", "");
        s_editor.putString("uid", "");
        s_editor.putString("state", "");
        s_editor.apply();

    }

    public void toProfile(View v){
        Intent intent = new Intent(SettingsActivity.this, profileActivity.class);

        if (intent != null) {
            SettingsActivity.this.startActivity(intent);
        }
    }

    public void toAppInfo(View v){
        Intent intent = new Intent(SettingsActivity.this, appInfoActivity.class);

        if (intent != null) {
            SettingsActivity.this.startActivity(intent);
        }
    }

    public void toHome(){
        finish();
    }

}
