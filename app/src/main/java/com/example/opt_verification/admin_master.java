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

                master_verify();

                Intent intent = new Intent(getApplicationContext() , admin_options.class) ;
                startActivity(intent);

            }
        });

    }

    private void master_verify(){
/*
        if (et_id.getText()=="avi27mitts"){
            if (et_pswd=="12345"){
                Intent intent = new Intent(getApplicationContext() , admin_options.class) ;
                startActivity(intent);
            }
        }
        else if(et_id=="mohit"){
            if(et_pswd=="12345678"){
                Intent intent = new Intent(getApplicationContext() , admin_options.class) ;
                startActivity(intent) ;
            }
        }
        else{
            Toast.makeText(getApplicationContext() , "Wrong details" , Toast.LENGTH_SHORT).show();
        }
*/
    }

}
