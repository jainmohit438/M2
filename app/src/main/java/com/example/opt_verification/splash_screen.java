package com.example.opt_verification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.database.Transaction;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler hand = new Handler() ;
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {

                finish();
                Intent intent = new Intent( getApplicationContext() , MainActivity.class) ;
                startActivity(intent);

            }
        } , 2500 ) ;

    }

}

