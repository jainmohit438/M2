package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Button btn_customer_login , btn_worker_login , btn_admin ;

    Button btnverify , btnotp , btnnext ;
    EditText etnumber , etotp;

    String sent_code ;

    FirebaseAuth fbauth ;// = FirebaseAuth.getInstance() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main) ;


        btn_customer_login=(Button)findViewById(R.id.btn_customer_login);
        btn_worker_login=(Button)findViewById(R.id.btn_worker_login);
        btn_admin = findViewById(R.id.btn_admin) ;

        btn_customer_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),customer_login.class);
                startActivity(intent);
            }
        });

        btn_worker_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( getApplicationContext() , salary.class );
                startActivity(intent);
            }
        });

        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext() , admin_options.class) ;
                startActivity(intent) ;

                //Toast.makeText(this , "button clicked" , Toast.LENGTH_LONG).show() ;
            }
        });

    }

}
