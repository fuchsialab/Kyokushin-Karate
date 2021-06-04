package com.fuchsia.karatesubhasmitra;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class KumiteTechnic extends AppCompatActivity {

        FirebaseAuth mAuth;
        DatabaseReference mDatabase;

        private static final String COMMON_TAG = "OrintationChange";

        RecyclerView recview;
        KumiteAdapter adapter;
        ProgressBar progressBar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_kumite_technic);

            mAuth=FirebaseAuth.getInstance();
            mDatabase= FirebaseDatabase.getInstance().getReference();
            mDatabase.keepSynced(true);

            progressBar = findViewById(R.id.progressbar);

            checkConnection();

            recview = (RecyclerView) findViewById(R.id.recyclek);
            recview.setLayoutManager(new LinearLayoutManager(this));

            FirebaseRecyclerOptions<KumiteModel> options =
                    new FirebaseRecyclerOptions.Builder<KumiteModel>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Kumite"), KumiteModel.class)
                            .build();

            adapter = new KumiteAdapter(options);
            adapter.startListening();
            recview.setAdapter(adapter);

            if (adapter.getItemCount() == 0){

                Toast.makeText(getApplicationContext(),"Kumite videos will be uploaded soon!", Toast.LENGTH_LONG).show();
            }

        }

        private String checkConnection() {

            ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {

                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {

                }
            } else {
                Toast.makeText(getApplicationContext(),"No Internet Connection!", Toast.LENGTH_LONG).show();

            }
            return null;
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
