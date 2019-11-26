package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class worker_options extends AppCompatActivity {

    TextView tv_name , tv_work ;
    Button btn_pending , btn_confirm ;
    DatabaseReference db ;
    String wrk , wname ;
    public static final String w = "work" , na = "name";
    FirebaseAuth fbauth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_options);

        tv_name = findViewById(R.id.wo_tv_name) ;
        tv_work = findViewById(R.id.wo_tv_work) ;

        btn_confirm = findViewById(R.id.wo_btn_confirmed) ;
        btn_pending = findViewById(R.id.wo_btn_pending) ;

        fbauth = FirebaseAuth.getInstance() ;

        String nl = getIntent().getStringExtra(worker_login.NUMBER) ;
        String nr = getIntent().getStringExtra(worker_registration.numb) ;
        String n;
        if(nl.isEmpty()){
            n = nr ;
        }
        else {
            n = nl ;
        }

        db = FirebaseDatabase.getInstance().getReference("workers").child(n) ;

        btn_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext() , worker_pending.class) ;
                intent.putExtra(w , wrk ) ;
                intent.putExtra( na , wname) ;
                startActivity( intent ) ;
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext() , worker_confirm.class) ;
                intent.putExtra( na , wname) ;
                startActivity( intent );
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                wname = dataSnapshot.child("name").getValue().toString() ;
                wrk = dataSnapshot.child("work").getValue().toString() ;
                tv_name.setText("Welcome back " + wname + ".");
                tv_work.setText("Pending appointments for " + wrk + " are :") ;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) ;
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder( this ) ;
        builder.setMessage("Are you sure you want to wait? You will be logged out!! ")
                .setCancelable(true)
                .setPositiveButton("Yes!LOGOUT.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        fbauth.signOut();
                        worker_options.super.onBackPressed() ;
                    }
                })
                .setNegativeButton("No!Set another appointment.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel() ;
                    }
                });

        AlertDialog alertDialog = builder.create() ;
        alertDialog.show() ;

    }
}
