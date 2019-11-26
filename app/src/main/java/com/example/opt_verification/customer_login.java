package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.Query;

public class customer_login extends AppCompatActivity {

    Button btn_sign_up , btn_submit_sigin ;

    EditText etusername , etpswd ;

    FirebaseAuth fbauth ;

    ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        btn_sign_up = findViewById(R.id.bsignin) ;
        btn_submit_sigin = findViewById(R.id.btn_submit_sigin) ;

        etusername = findViewById(R.id.etusername) ;
        etpswd = findViewById(R.id.etpassword) ;

        fbauth = FirebaseAuth.getInstance() ;

        progressDialog = new ProgressDialog( customer_login.this ) ;

        if (fbauth.getCurrentUser() != null){
            finish() ;
            Intent intent = new Intent( getApplicationContext() , customer_options.class ) ;
            startActivity(intent) ;
        }

        btn_submit_sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify_customer() ;
            }
        });

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext() , customer_registration.class) ;
                startActivity(intent) ;

            }
        });
    }

    private void verify_customer(){
        String username = etusername.getText().toString().trim() ;
        String pswd = etpswd.getText().toString() ;

        if (username.isEmpty() || pswd.isEmpty()){
            Toast.makeText( getApplicationContext() , "Please enter details properly." , Toast.LENGTH_SHORT).show() ;
        }
        else {
            progressDialog.setMessage("Logging in.....please wait!!");
            progressDialog.show() ;
            fbauth.signInWithEmailAndPassword(username , pswd).addOnCompleteListener(customer_login.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        finish() ;
                        Intent intent = new Intent( getApplicationContext() , customer_options.class ) ;
                        startActivity(intent) ;
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText( getApplicationContext() , "Email id/Password is incorrect." , Toast.LENGTH_SHORT).show() ;
                    }

                }
            }) ;
        }
    }
}
