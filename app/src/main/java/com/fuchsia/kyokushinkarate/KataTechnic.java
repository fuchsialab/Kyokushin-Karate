package com.fuchsia.kyokushinkarate;


import static com.google.firebase.database.FirebaseDatabase.getInstance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Map;


public class KataTechnic extends AppCompatActivity {

    private AdView mAdView;
    private static InterstitialAd mInterstitialAd;

    static KataTechnic instance;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    private static final String COMMON_TAG = "OrintationChange";

    RecyclerView recview;

    whiteadapter wadapter;
    yellowadapter yadapter;
    blackadapter blaadapter;
    blueadapter bluadapter;
    brownadapter bradapter;
    greenadapter gadapter;
    orangeadapter oadapter;;

    ProgressBar progressBar;

    TextView kata;
    Bundle bundle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kata_technic);

        bannerAds();
        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);

        instance = this;

        progressBar = findViewById(R.id.progressbar);
        kata = findViewById(R.id.katas);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Map<String, AdapterStatus> statusMap = initializationStatus.getAdapterStatusMap();
                for (String adapterClass : statusMap.keySet()) {
                    AdapterStatus status = statusMap.get(adapterClass);
                    Log.d("MyApp", String.format(
                            "Adapter name: %s, Description: %s, Latency: %d",
                            adapterClass, status.getDescription(), status.getLatency()));
                }

                // Start loading ads here...
            }
        });

        checkConnection();

        recview = (RecyclerView) findViewById(R.id.recyclek);
        recview.setLayoutManager(new LinearLayoutManager(this));

        bundle= getIntent().getExtras();
        if(bundle !=null){
            String name= bundle.getString("belt");
            assert name != null;
            setUp(name);

        }


    }

    private void setUp(String name) {


        switch (name) {
            case "white": {

                kata.setText("Kata for White Belt");


                FirebaseRecyclerOptions<whitemodel> options =
                        new FirebaseRecyclerOptions.Builder<whitemodel>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("white"), whitemodel.class)
                                .build();

                wadapter = new whiteadapter(options);
                wadapter.startListening();
                recview.setAdapter(wadapter);


                break;
            }
            case "orange": {


                kata.setText("Kata for Orange Belt");

                FirebaseRecyclerOptions<orangemodel> options =
                        new FirebaseRecyclerOptions.Builder<orangemodel>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("orange"), orangemodel.class)
                                .build();

                oadapter = new orangeadapter(options);
                oadapter.startListening();
                recview.setAdapter(oadapter);

                break;
            }
            case "blue": {

                kata.setText("Kata for Blue Belt");

                FirebaseRecyclerOptions<bluemodel> options =
                        new FirebaseRecyclerOptions.Builder<bluemodel>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("blue"), bluemodel.class)
                                .build();

                bluadapter = new blueadapter(options);
                bluadapter.startListening();
                recview.setAdapter(bluadapter);


                break;
            }
            case "yellow": {

                kata.setText("Kata for Yellow Belt");

                FirebaseRecyclerOptions<yellowmodel> options =
                        new FirebaseRecyclerOptions.Builder<yellowmodel>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("yellow"), yellowmodel.class)
                                .build();

                yadapter = new yellowadapter(options);
                yadapter.startListening();
                recview.setAdapter(yadapter);

                break;
            }
            case "green": {

                kata.setText("Kata for Green Belt");

                FirebaseRecyclerOptions<greenmodel> options =
                        new FirebaseRecyclerOptions.Builder<greenmodel>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("green"), greenmodel.class)
                                .build();

                gadapter = new greenadapter(options);
                gadapter.startListening();
                recview.setAdapter(gadapter);

                break;
            }
            case "brown": {

                kata.setText("Kata for Brown Belt");

                FirebaseRecyclerOptions<brownmodel> options =
                        new FirebaseRecyclerOptions.Builder<brownmodel>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("brown"), brownmodel.class)
                                .build();

                bradapter = new brownadapter(options);
                bradapter.startListening();
                recview.setAdapter(bradapter);

                break;
            }
            case "black": {

                kata.setText("Kata for Black Belt");

                FirebaseRecyclerOptions<blackmodel> options =
                        new FirebaseRecyclerOptions.Builder<blackmodel>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("black"), blackmodel.class)
                                .build();

                blaadapter = new blackadapter(options);
                blaadapter.startListening();
                recview.setAdapter(blaadapter);

                break;
            }


        }

    }


    private String checkConnection() {

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {

            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {

            }
        } else {
            Toast.makeText(getApplicationContext(),"No Internet Connection!", Toast.LENGTH_LONG).show();

        }
        return null;
    }

    public static KataTechnic getInstance() {
        return instance;
    }


    @SuppressLint("MissingPermission")
    public void bannerAds(){

        View view= findViewById(R.id.bannerad);
        mAdView=new AdView(KataTechnic.this);
        ((RelativeLayout)view).addView(mAdView);
        mAdView.setAdSize(AdSize.SMART_BANNER);
        mAdView.setAdUnitId(getResources().getString(R.string.bannerid));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //MediationTestSuite.launch(basicKarate.this);

        InterstitialAd.load(KataTechnic.this,getResources().getString(R.string.interstitialId), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                mInterstitialAd = interstitialAd;

            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

                mInterstitialAd = null;

            }
        });

    }



    public void showInterstitial() {


        if (mInterstitialAd != null) {

            mInterstitialAd.show(KataTechnic.this);

            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdDismissedFullScreenContent() {

                    AdRequest adRequest = new AdRequest.Builder().build();

                    InterstitialAd.load(KataTechnic.this, getResources().getString(R.string.interstitialId), adRequest, new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                            mInterstitialAd = interstitialAd;

                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

                            mInterstitialAd = null;

                        }
                    });

                }

            });

        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(COMMON_TAG,"MainActivity onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(COMMON_TAG,"MainActivity onSaveInstanceState");
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation ==Configuration.ORIENTATION_LANDSCAPE){
            Log.i(COMMON_TAG, "landscape");
        }else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            Log.i(COMMON_TAG, "portrait");
        }
    }

}