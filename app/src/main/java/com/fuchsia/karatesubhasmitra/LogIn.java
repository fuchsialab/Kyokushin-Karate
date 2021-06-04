package com.fuchsia.karatesubhasmitra;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class LogIn extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    private String getCode;
    Button login;
    EditText editText;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);



        login = findViewById(R.id.login);
        editText = findViewById(R.id.code);

        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();

        DatabaseReference rootref= getInstance().getReference().child("Code");
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                getCode=String.valueOf(Objects.requireNonNull(dataSnapshot.child("newcode").getValue()).toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String code = Objects.requireNonNull(editText.getText()).toString().trim();

                if(!code.isEmpty() ){

                    if(!(getCode == null)){

                        if(code.equals(getCode)){

                            Intent intent = new Intent(LogIn.this,MainActivity.class);
                            startActivity(intent);

                            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(TEXT,code);

                            editor.apply();
                            Toast.makeText(LogIn.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();

                            finish();
                        }
                        else {
                            Toast.makeText(LogIn.this,"Enter a Valid Code! Contact to Shihan Subhas Mitra for your code.",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(LogIn.this,"Check your internet connection.",Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(LogIn.this,"Enter a code please.",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }


}