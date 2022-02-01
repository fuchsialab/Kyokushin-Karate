package com.fuchsia.kyokushinkarate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements UpdateHelper.OnUpdateCheckListener{

    private long backPressTime;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    private AdView mAdView;

    CardView white, orange, blue, yellow, green, brown, black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UpdateHelper.with(this)
                .onUpdateCheck(this)
                .check();

        bannerAds();

        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();

        white = findViewById(R.id.white);
        orange = findViewById(R.id.orange);
        blue = findViewById(R.id.blue);
        yellow = findViewById(R.id.yellow);
        green = findViewById(R.id.green);
        brown = findViewById(R.id.brown);
        black = findViewById(R.id.black);

        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = "white";
                Intent myIntent = new Intent(view.getContext(),KataTechnic.class);
                myIntent.putExtra("belt",text);
                startActivity(myIntent);

            }
        });
        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = "orange";
                Intent myIntent = new Intent(view.getContext(),KataTechnic.class);
                myIntent.putExtra("belt",text);
                startActivity(myIntent);

            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = "blue";
                Intent myIntent = new Intent(view.getContext(),KataTechnic.class);
                myIntent.putExtra("belt",text);
                startActivity(myIntent);

            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = "yellow";
                Intent myIntent = new Intent(view.getContext(),KataTechnic.class);
                myIntent.putExtra("belt",text);
                startActivity(myIntent);

            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = "green";
                Intent myIntent = new Intent(view.getContext(),KataTechnic.class);
                myIntent.putExtra("belt",text);
                startActivity(myIntent);

            }
        });
        brown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = "brown";
                Intent myIntent = new Intent(view.getContext(),KataTechnic.class);
                myIntent.putExtra("belt",text);
                startActivity(myIntent);
            }
        });
        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = "black";
                Intent myIntent = new Intent(view.getContext(),KataTechnic.class);
                myIntent.putExtra("belt",text);
                startActivity(myIntent);
            }
        });


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout= findViewById(R.id.container);
        navigationView= findViewById(R.id.navdrawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menuHome:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;

                    case R.id.menuprivacy:
                        Intent browse =new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.lkmkm)));
                        startActivity(browse);
                        drawerLayout.closeDrawer(GravityCompat.START);

                        return true;

                    case R.id.menuwhatsapp:

                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.fuchsia.saver")));


                        return true;

                    case R.id.menurate:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        final String appPackageName = getPackageName();
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }

                        return true;


                    case R.id.menushare:

                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "Download Kyokushin Karate App.  https://play.google.com/store/apps/details?id=com.fuchsia.kyokushinkarate";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Kyokushin Karate App");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        drawerLayout.closeDrawer(GravityCompat.START);

                        return true;


                    case R.id.menuexit:

                        finish();
                        System.exit(0);

                        return true;
                }
                return false;


            }
        });


        AppRate.with(this)
                .setInstallDays(0)
                .setLaunchTimes(5)
                .setRemindInterval(10)
                .setShowLaterButton(true)
                .setDebug(false)
                .setOnClickButtonListener(new OnClickButtonListener() {
                    @Override
                    public void onClickButton(int which) {
                        Log.d(MainActivity.class.getName(), Integer.toString(which));
                    }
                })
                .monitor();

        AppRate.showRateDialogIfMeetsConditions(this);




    }

    public void bannerAds(){

        View view= findViewById(R.id.bannerad);
        mAdView=new AdView(MainActivity.this);
        ((RelativeLayout)view).addView(mAdView);
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId(getResources().getString(R.string.bannerid));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //MediationTestSuite.launch(basicKarate.this);


    }

    @Override
    public void onUpdateCheckListener(String urlApp) {

        AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setTitle("New Version Available")
                .setMessage(" Please update for better experience")
                .setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.jjjj))));

                    }
                }).setNegativeButton("NOT NOW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
        alertDialog.show();

    }

    @Override
    public void onBackPressed() {


        if (backPressTime+2000>System.currentTimeMillis()){

            super.onBackPressed();
            return;

        }else {
            Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT).show();
        }

        backPressTime= System.currentTimeMillis();
    }


}