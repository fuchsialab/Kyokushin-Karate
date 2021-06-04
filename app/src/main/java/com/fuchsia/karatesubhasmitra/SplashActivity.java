package com.fuchsia.karatesubhasmitra;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
public class SplashActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash);
        hideNavigationbar();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        if(sharedPreferences.contains(TEXT)){

            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class); startActivity(i);
                    finish(); } }, 1500);


        }else {

            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    Intent i = new Intent(SplashActivity.this, LogIn.class); startActivity(i);
                    finish(); } }, 1500);
        }



    }

    private void hideNavigationbar(){
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        );
    }

}

