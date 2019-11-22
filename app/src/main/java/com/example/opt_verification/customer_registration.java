package com.example.opt_verification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class customer_registration extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*\\d)(?=.*[A-Z])([@$%&#])[0-9a-zA-Z]{4,}$");
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

            }
        });

    }

    private boolean validatePassword(){
        String passwordinput =etpassword.getText().toString().trim();

        if (passwordinput.isEmpty()) {
            Toast.makeText(this,"Field can not be empty",Toast.LENGTH_LONG).show();
            return true;
        }

        else if (!PASSWORD_PATTERN.matcher(passwordinput).matches()) {
            Toast.makeText(this,"Password too weak",Toast.LENGTH_LONG).show();
            return true;
        }

        else {
            return false;
        }
    }
    private boolean validatemobile() {
        String mobileinput = etphone.getText().toString().trim();
        String mobilepattern = "[0-9]{10}";

        if (!mobileinput.matches(mobilepattern)) {
            Toast.makeText(this,"Enter correct mobile no",Toast.LENGTH_LONG).show();
            return true;
        }
        else {
            return false;
        }
    }

    private boolean validationusername() {
        String usernameinput=etusername.getText().toString().trim();
        String upattern = "^[a-z0-9_-]{5,15}$";

        if (!usernameinput.matches(upattern)) {
            Toast.makeText(this,"Enter a valid Username",Toast.LENGTH_LONG).show();
            return true;
        }

        else {
            return false;
        }
    }
    private boolean validationname() {
        String nameinput =etname.getText().toString().trim();
        String namepattern = "^[a-z0-9_-]{5,15}$";

        if (!nameinput.matches(namepattern)) {
            Toast.makeText(this,"Enter a valid name",Toast.LENGTH_LONG).show();
            return true;
        }
        else {
            return false;
        }
    }
    private boolean validateEmail() {
        String emailinput =etemail.getText().toString().trim();

        if (emailinput.isEmpty()) {
            Toast.makeText(this,"Field Can not be Empty",Toast.LENGTH_LONG).show();
            return true;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailinput).matches()) {
            Toast.makeText(this,"Please enter a valid Email",Toast.LENGTH_LONG).show();
            return true;
        }
        else {
            return false;
        }
    }

    private void addcustomer(){
        String name =etname.getText().toString().trim();
        String username = etusername.getText().toString().trim();
        String email =etemail.getText().toString().trim();
        String password =etpassword.getText().toString().trim();
        String phone =etphone.getText().toString().trim();

        if(validationname()  || validateEmail() ||  validationusername() ||  validatemobile() ){

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
