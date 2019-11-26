package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class customer_registration extends AppCompatActivity {

    EditText etname ,etusername, etemail , etpassword , etphone;

    Button btn_register , btn_options ;

    FirebaseAuth fbauth ;

    DatabaseReference dbcustomer;

    ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        fbauth = FirebaseAuth.getInstance() ;

        dbcustomer = FirebaseDatabase.getInstance().getReference("customer");

        etname= (EditText)findViewById(R.id.etname);
        etusername = (EditText) findViewById(R.id.etusername);
        etemail= (EditText)findViewById(R.id.etemail);
        etpassword =(EditText) findViewById(R.id.etpassword);
        etphone= (EditText) findViewById(R.id.etphone);

        btn_register = findViewById(R.id.bsignup) ;

        progressDialog = new ProgressDialog( customer_registration.this) ;

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addcustomer() ;

            }
        });

    }

    private void addcustomer(){
        final String name     = etname.getText().toString().trim()     ;
        final String username = etusername.getText().toString().trim() ;
        final String email    = etemail.getText().toString().trim()    ;
        final String pswd     = etpassword.getText().toString()        ;
        final String phone    = etphone.getText().toString().trim()    ;

        if(TextUtils.isEmpty(name)  || TextUtils.isEmpty(username) || TextUtils.isEmpty(email)  || TextUtils.isEmpty(phone) ){

            Toast.makeText(this,"Please Fill Your Entries correctly",Toast.LENGTH_LONG).show();

        }
        else if(phone.length()!= 10){
            Toast.makeText( getApplicationContext() , "Invalid phone number." , Toast.LENGTH_SHORT).show() ;
        }
        else {
            progressDialog.setMessage("Registering....Please wait.");
            progressDialog.show() ;
            fbauth.createUserWithEmailAndPassword(email , pswd ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){

                        customer_details cust = new customer_details(name,username,email,phone);

                        dbcustomer.child(FirebaseAuth.getInstance().getUid()).setValue(cust).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                progressDialog.dismiss() ;
                                Toast.makeText( getApplicationContext() ,"Customer Added" , Toast.LENGTH_LONG ).show() ;

                                etname.setText("");
                                etemail.setText("");
                                etpassword.setText("");
                                etphone.setText("");
                                etusername.setText("");

                                finish() ;
                                Intent intent = new Intent(getApplicationContext() , customer_options.class) ;
                                startActivity(intent) ;

                            }
                        }) ;

                    }
                    else{
                        progressDialog.dismiss() ;
                        Toast.makeText( getApplicationContext() , "Registration failed." , Toast.LENGTH_SHORT).show() ;
                    }
                }
            }) ;

        }


    }
}
