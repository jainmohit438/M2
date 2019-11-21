package com.example.opt_verification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class customer_registration extends AppCompatActivity {


    EditText etname ,etusername, etemail , etpassword , etphone;

    Button btn_register , btn_options ;

    DatabaseReference databasecustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        databasecustomer= FirebaseDatabase.getInstance().getReference("customer");

        etname= (EditText)findViewById(R.id.etname);
        etusername = (EditText) findViewById(R.id.etusername);
        etemail= (EditText)findViewById(R.id.etemail);
        etpassword =(EditText) findViewById(R.id.etpassword);
        etphone= (EditText) findViewById(R.id.etphone);
        btn_register = findViewById(R.id.bsignup) ;


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addcustomer() ;

                Intent intent = new Intent(getApplicationContext() , customer_options.class) ;
                startActivity(intent);

            }
        });

    }

    private void addcustomer(){
        String name =etname.getText().toString().trim();
        String username = etusername.getText().toString().trim();
        String email =etemail.getText().toString().trim();
        String password =etpassword.getText().toString().trim();
        String phone =etphone.getText().toString().trim();

        if(TextUtils.isEmpty(name)  || TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(phone) ){

            Toast.makeText(this,"Please Fill Your Entries correctly",Toast.LENGTH_LONG).show();

        }

        else {

            String id= databasecustomer.push().getKey();

            customer_details cust = new customer_details(name,username,email,password,phone);

            databasecustomer.child(id).setValue(cust);

            Toast.makeText(this,"Customer Added",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext() , customer_options.class) ;
            startActivity(intent) ;
        }


    }
}
