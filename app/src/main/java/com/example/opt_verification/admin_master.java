package com.example.opt_verification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class admin_master extends AppCompatActivity {

    EditText et_id , et_pswd ;

    Button btn_master_login ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_master);

        et_id = findViewById(R.id.et_master_id) ;
        et_pswd = findViewById(R.id.et_master_password) ;

        btn_master_login = findViewById(R.id.btn_master_login) ;

        btn_master_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                master_verify();/*
                Intent intent = new Intent(getApplicationContext() , admin_options.class) ;
                startActivity(intent);*/

            }
        });

    }

    private void master_verify(){

        if (et_id.getText().toString().equals("avi27mitts")){
            if (et_pswd.getText().toString().equals("12345")){
                finish() ;
                Intent intent = new Intent(getApplicationContext() , admin_display.class) ;
                startActivity(intent);
            }
            else{
                Toast.makeText( admin_master.this , "Wrong password." , Toast.LENGTH_SHORT).show() ;
            }
        }
        else if(et_id.getText().toString().equals("jainmohit438")){
            if(et_pswd.getText().toString().equals("12345678")){
                finish() ;
                Intent intent = new Intent(getApplicationContext() , admin_display.class) ;
                startActivity(intent) ;
            }
            else {
                Toast.makeText( admin_master.this , "Wrong password." , Toast.LENGTH_SHORT).show() ;
            }
        }
        else{
            Toast.makeText(getApplicationContext() , "Wrong details." , Toast.LENGTH_SHORT).show();
        }
    }
}
