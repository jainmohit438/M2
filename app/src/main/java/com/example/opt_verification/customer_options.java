package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class customer_options extends AppCompatActivity {

    DatabaseReference dbcustomer ;

    TextView tv_name ;

    FirebaseAuth fbauth ;

    Button btn_logout , btn_new , btn_pending , btn_confirm ;

    ProgressDialog progressDialog ;

    public static final String customer_name = "name" ;
    String c_name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_options);

        dbcustomer = FirebaseDatabase.getInstance().getReference("customer") ;

        tv_name = findViewById(R.id.tv_cust_name) ;

        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( getApplicationContext() , c_name + "." , Toast.LENGTH_SHORT).show() ;
            }
        });

        btn_logout = findViewById(R.id.btn_customer_logout) ;
        btn_new = findViewById(R.id.co_btn_new) ;
        btn_pending = findViewById(R.id.co_btn_pending) ;
        btn_confirm = findViewById(R.id.co_btn_confirmed) ;

        fbauth = FirebaseAuth.getInstance() ;

        progressDialog = new ProgressDialog( customer_options.this) ;

        FirebaseUser user = fbauth.getCurrentUser() ;

        if ( user == null){
            finish() ;
            Intent intent = new Intent( getApplicationContext() , customer_login.class) ;
            startActivity(intent) ;
        }

        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext() , customer_new_appointment.class) ;
                intent.putExtra(customer_name , c_name ) ;
                startActivity(intent);
            }
        });

        btn_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext() , customer_pending_appointment.class) ;
                intent.putExtra(customer_name , c_name) ;
                startActivity(intent);
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext() , customer_confirm_appointment.class) ;
                intent.putExtra(customer_name , c_name) ;
                startActivity(intent) ;
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder( customer_options.this ) ;
                builder.setMessage("Are you sure you want to logout?")
                        .setCancelable(true)
                        .setPositiveButton("Yes!LOGOUT.", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                fbauth.signOut();
                                finish() ;
                                Intent intent = new Intent( getApplicationContext() , customer_login.class) ;
                                startActivity(intent) ;
                            }
                        })
                        .setNegativeButton("No!Book another appointment.", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.cancel() ;
                            }
                        });

                AlertDialog alertDialog = builder.create() ;
                alertDialog.show() ;
            }
        });
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder( this ) ;
        builder.setMessage("Are you sure you want to exit? You will be logged out!! ")
                .setCancelable(true)
                .setPositiveButton("Yes!LOGOUT.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fbauth.signOut();
                        customer_options.super.onBackPressed() ;
                    }
                })
                .setNegativeButton("No!Book another appointment.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel() ;
                    }
                });

        AlertDialog alertDialog = builder.create() ;
        alertDialog.show() ;

    }

    @Override
    protected void onStart() {
        super.onStart();

        dbcustomer.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FirebaseUser user2 = fbauth.getCurrentUser() ;
                c_name = dataSnapshot.child(user2.getUid()).child("cname").getValue().toString();

                tv_name.setText("Welcome back " + c_name + ".");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) ;
    }
}
