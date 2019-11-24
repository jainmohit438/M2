package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class worker_all_display extends AppCompatActivity {

    FirebaseAuth fbauth ;

    ListView lv ;

    List<pending_appointment> app_list ;

    DatabaseReference dbpa , dbwa , dbca , dbworker ;

    String wrk , wrkr , wrkl , wname , cname  ;

    Date da;

    TextView tv , tvname ;

    String idpa ;

    //pending_appointment pa ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_all_display);

        lv = findViewById(R.id.wad_lv) ;

        tv = findViewById(R.id.wad_tv) ;
        tvname = findViewById(R.id.wad_tv_name) ;

        fbauth = FirebaseAuth.getInstance() ;

        FirebaseUser user = fbauth.getCurrentUser() ;
        String n = getIntent().getStringExtra(worker_login.NUMBER) ;

        app_list = new ArrayList<>() ;

        wrkl = getIntent().getStringExtra(worker_login.numb) ;
        wrkr = getIntent().getStringExtra(worker_registration.numb) ;

        if (!wrkr.isEmpty()){
            wrk = wrkr ;
        }
        else if(!wrkl.isEmpty()){
            wrk = wrkl ;
        }
        else {
            wrk = "" ;
            Toast.makeText( getApplicationContext() , "empty" , Toast.LENGTH_SHORT).show() ;
        }
        tv.setText("Pending appointments for " + wrk + " are :") ;

        dbpa = FirebaseDatabase.getInstance().getReference("pappointment") ;
        dbwa = FirebaseDatabase.getInstance().getReference("wappointment") ;
        dbca = FirebaseDatabase.getInstance().getReference("cappointment") ;
        dbworker = FirebaseDatabase.getInstance().getReference("workers").child(n) ;

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                pending_appointment pa1 = app_list.get(i) ;
                idpa = pa1.getId() ;
                //Toast.makeText(getApplicationContext() , "af" , Toast.LENGTH_SHORT).show() ;

                cname = pa1.getCname() ;
                da = pa1.getD() ;

                dbwa = FirebaseDatabase.getInstance().getReference("wappointment").child(wname) ;
                dbca = FirebaseDatabase.getInstance().getReference("cappointment").child(cname) ;

                AlertDialog.Builder builder1 = new AlertDialog.Builder( worker_all_display.this ) ;
                builder1.setMessage("Are you sure you want to accept " + wrk + " for " + cname + " at " + da + " ??")
                        .setCancelable(true)
                        .setPositiveButton("YES,ACCEPT!!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                appointment_worker aw = new appointment_worker(cname , wname , wrk , da ) ;
                                appointment_customer ac = new appointment_customer(cname , wname , wrk , da ) ;

                                String idw = dbwa.push().getKey() ;
                                dbwa.child(idw).setValue(aw) ;

                                String idc = dbca.push().getKey() ;
                                dbca.child(idc).setValue(ac) ;

                                dbpa.child(idpa).removeValue() ;

                            }
                        })
                        .setNegativeButton("No.", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                return;
                            }
                        });

                AlertDialog al = builder1.create() ;
                al.show();
            }
        });
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
                        worker_all_display.super.onBackPressed() ;
                    }
                })
                .setNegativeButton("No!Confirm another appointment.", new DialogInterface.OnClickListener() {
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
        super.onStart() ;

        dbpa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                app_list.clear() ;

                for (DataSnapshot appsnap : dataSnapshot.getChildren()){
                    pending_appointment p = appsnap.getValue(pending_appointment.class) ;
                    if (p.getWork().equals(wrk)){
                        app_list.add(p) ;
                    }
                }

                customer_pending_for_worker adapter = new customer_pending_for_worker( worker_all_display.this , app_list) ;

                lv.setAdapter(adapter) ;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) ;

        dbworker.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                wname = dataSnapshot.child("name").getValue().toString() ;
                tvname.setText("Welcome back " + wname + ".");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) ;
    }
}
