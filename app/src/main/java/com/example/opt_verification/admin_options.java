package com.example.opt_verification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin_options extends AppCompatActivity {

    Button btn_cust , btn_work , btn_serv , btn_admin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_options);

        btn_cust = findViewById(R.id.btn_customer_details) ;
        btn_work = findViewById(R.id.btn_worker_details) ;
        btn_serv = findViewById(R.id.btn_add_service) ;
        btn_admin = findViewById(R.id.btn_add_admin) ;

        btn_cust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext() , customer_display.class) ;
                startActivity(intent);

            }
        });

        btn_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext() , worker_display.class) ;
                startActivity(intent) ;

            }
        });

        btn_serv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( getApplicationContext() , add_service.class) ;
                startActivity(intent) ;

            }
        });

        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext() , add_admin.class) ;
                startActivity(intent) ;
            }
        });

    }
}
