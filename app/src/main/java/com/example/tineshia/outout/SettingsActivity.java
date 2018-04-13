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
    }

    public void logout(){
        SharedPreferences check_loginState = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor s_editor = check_loginState.edit();
        s_editor.putString("email", null);
        s_editor.apply();

        Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);

        if (intent != null) {
            SettingsActivity.this.startActivity(intent);
        }

    }

    public void toHome(){
        finish();
    }

}
