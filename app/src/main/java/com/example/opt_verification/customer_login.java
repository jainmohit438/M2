package com.example.opt_verification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.Query;

public class customer_login extends AppCompatActivity {

    Button btn_sign_up ;
    Button btn_submit_sigin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        btn_sign_up = findViewById(R.id.bsignin) ;
        btn_submit_sigin = findViewById(R.id.btn_submit_sigin) ;

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext() , customer_registration.class) ;
                startActivity(intent) ;

            }
        });

        btn_submit_sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext() , customer_options.class);
                startActivity(intent) ;
            }
        });

    }
}
