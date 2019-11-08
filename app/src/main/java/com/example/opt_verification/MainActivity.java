package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    Button btnverify , btnotp ;
    EditText etnumber , etotp;

    String sent_code ;

    FirebaseAuth fbauth ;// = FirebaseAuth.getInstance() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main) ;

        fbauth = FirebaseAuth.getInstance() ;

        btnotp = findViewById(R.id.btnget_otp) ;
        btnverify = findViewById(R.id.btnverify) ;

        etnumber = findViewById(R.id.etnumber) ;
        etotp = findViewById(R.id.etotp) ;

        String sent_code ;

        //fbauth = FirebaseAuth.getInstance() ;

        btnotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( getApplicationContext() , "Button clicked. " , Toast.LENGTH_SHORT).show() ;
                sendotp();
            }
        });

        btnverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verifycode();
            }
        });

    }

    private void sendotp() {
        String num = etnumber.getText().toString() ;

        if ( num.isEmpty() ){
            etnumber.setError( " Entering phone number is mandatory. " );
            etnumber.requestFocus() ;
            return ;
        }

        if ( num.length() < 10 ){
            etnumber.setError( " Please enter a valid phone number. " );
            etnumber.requestFocus() ;
            return ;
        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                num,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            Toast.makeText( getApplicationContext() , "Code sent successfully. " , Toast.LENGTH_LONG).show() ;

            sent_code = s ;

        }
    } ;

    private void verifycode() {
        String code = etotp.getText().toString() ;

        if (code.isEmpty()){
            etotp.setError( " Please enter a valid otp. " ) ;
            etnumber.requestFocus() ;
            return ;
        }

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(sent_code, code) ;

        signInWithPhoneAuthCredential(credential) ;
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        fbauth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) { // if verifiaction is successful
                            Toast.makeText( getApplicationContext() , "Verification approved." , Toast.LENGTH_LONG).show() ;

                        } else { // if verification fails
                            Toast.makeText( getApplicationContext() , "Verification failed." , Toast.LENGTH_LONG).show() ;

                        }
                    }
                });
    }

}
