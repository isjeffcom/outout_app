package com.example.tineshia.outout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;

public class userCreation extends Activity {


    public int c_view = 1;

    int[] c_view_1 = new int[] {
            R.drawable.classical_button_selector,
            R.drawable.decades_button_selector,
            R.drawable.electronic_button_selector,
            R.drawable.hiphop_button_selector,
            R.drawable.jazz_button_selector,
            R.drawable.opera_button_selector,
            R.drawable.pop_button_selector,
            R.drawable.rock_button_selector,
            R.drawable.randb_button_selector,
    };

    int[] c_view_2 = new int[] {
            R.drawable.bar_button_selector,
            R.drawable.concerthall_button_selector,
            R.drawable.nightclub_button_selector,
            R.drawable.theatre_button_selector,
    };

    int[] c_view_3 = new int[]{
            R.drawable.free_button_selector,
            R.drawable.one_to_five_button_selector,
            R.drawable.six_to_ten_button_selector,
            R.drawable.eleven_button_selector
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_user_creation);

        //Initial first screen display
        viewController(c_view);

        //Identify 2 elements
        ImageButton buttonNext = (ImageButton) findViewById(R.id.buttonGo);
        ImageButton buttonLast = (ImageButton) findViewById(R.id.buttonPre);

        //Set button opacity
        buttonLast.getBackground().setAlpha(150);


        //Go next
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                c_view = c_view + 1;
                if (c_view > 3) {
                    /*SharedPreferences save_loginState = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor s_editor = save_loginState.edit();
                    s_editor.putString("state", "2");
                    s_editor.apply();*/

                    //Go to home
                    toHome();

                }else{
                    viewController(c_view);
                    progressControl();
                }

            }
        });

        //Click next
        buttonLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageButton buttonLast = (ImageButton) findViewById(R.id.buttonPre);

                if(c_view > 1){
                    c_view = c_view - 1;
                    viewController(c_view);
                    progressControl();
                    if(c_view == 1){
                        buttonLast.getBackground().setAlpha(150);
                    }else{
                        buttonLast.getBackground().setAlpha(225);
                    }

                }else{
                    progressControl();
                    buttonLast.getBackground().setAlpha(150);
                }

            }
        });


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

    //Control and render data
    public void viewController(int view_count){
        TableLayout container = (TableLayout) findViewById(R.id.selection_container);
        container.removeAllViews();

        ImageButton buttonLast = (ImageButton) findViewById(R.id.buttonPre);


        int[] tempImg = null;
        if(view_count == 1){
            tempImg = c_view_1;
            buttonLast.getBackground().setAlpha(150);
        }else if(view_count == 2){
            tempImg = c_view_2;
            buttonLast.getBackground().setAlpha(225);
        }else if(view_count == 3){
            tempImg = c_view_3;
            buttonLast.getBackground().setAlpha(225);

        }

        int count = 0;
        int tempCount = 0;
        int marginValue = 0;

        for (int i=0;i<tempImg.length;i++){

            //Create tablerow
            if(tempImg.length > 4){
                if(count == 0 || count == 3 || count == 6){
                    TableRow tb = new TableRow((userCreation.this));
                    container.addView(tb);
                    tempCount = count;
                    tb.setId(tempCount);

                }
            }else{
                if(count == 0 || count == 2 || count == 5){
                    TableRow tb = new TableRow((userCreation.this));
                    container.addView(tb);
                    tempCount = count;
                    tb.setId(tempCount);

                }
            }


            //Identify tablerow
            String c = String.valueOf(tempCount);
            int resID = getResources().getIdentifier(c, "id", getPackageName());
            TableRow c_tablerow = (TableRow) findViewById(resID);

            //Create button in tablerow
            ImageButton selection = new ImageButton(userCreation.this);
            c_tablerow.addView(selection);
            selection.setBackgroundResource(tempImg[i]);

            if(tempImg.length > 4){
                marginValue = 30;
            }else{
                marginValue = 40;
            }
            ((ViewGroup.MarginLayoutParams) selection.getLayoutParams()).leftMargin = marginValue;
            ((ViewGroup.MarginLayoutParams) selection.getLayoutParams()).rightMargin = marginValue;
            ((ViewGroup.MarginLayoutParams) selection.getLayoutParams()).topMargin = marginValue;
            ((ViewGroup.MarginLayoutParams) selection.getLayoutParams()).bottomMargin = marginValue;


            count = count + 1;
        }

    }

    //Progress bar control
    public void progressControl(){
        ProgressBar progress = (ProgressBar) findViewById(R.id.determinateBar);
        switch (c_view) {
            case 1:
                progress.setProgress(33);
                break;
            case 2:
                progress.setProgress(66);
                break;
            case 3:
                progress.setProgress(95);
        }
    }

    //Go to Home acitivity
    public void toHome(){
        Intent toMap = new Intent(userCreation.this, MapsActivity.class);
        startActivity(toMap);
    }



}
