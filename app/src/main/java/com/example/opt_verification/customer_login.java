package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class customer_login extends AppCompatActivity {

    EditText et_username,et_password;
    Button btn_sign_up ;
    Button btn_submit_sigin;
    DatabaseReference databasecustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        et_username=(EditText) findViewById(R.id.etusername);
        et_username=(EditText) findViewById(R.id.etpassword);

        btn_sign_up = findViewById(R.id.bsignin) ;

        databasecustomer = FirebaseDatabase.getInstance().getReference("customer") ;

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext() , customer_registration.class) ;
                startActivity(intent) ;

            }
        });

        btn_submit_sigin =findViewById(R.id.btn_submit_sigin);

        btn_submit_sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Query q = databasecustomer.orderByChild("cusername").equalTo(et_username.getText().toString().trim());
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            for (DataSnapshot user : dataSnapshot.getChildren()) {

                                customer_details cust = user.getValue(customer_details.class);

                                if (cust.cpassword.equals(et_password.getText().toString().trim())) {
                                    Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getApplicationContext() , "incorrect " , Toast.LENGTH_SHORT).show();
                                }
                            }


                        }
                        else {
                            Toast.makeText(getApplicationContext() , "not found" , Toast.LENGTH_SHORT).show() ;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
