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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class worker_pending extends AppCompatActivity {

    ListView lv ;
    List<pending_appointment> li ;
    DatabaseReference dbpa , dbca , dbw ;
    String idpa , wrk , cid , wnum ;
    Date da , cdate = new Date() ;
    TextView tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_pending);

        lv = findViewById(R.id.wp_lv) ;
        li = new ArrayList<>() ;

        dbpa = FirebaseDatabase.getInstance().getReference("pappointment") ;
        dbw = FirebaseDatabase.getInstance().getReference("workers").child(wnum) ;

        wnum = getIntent().getStringExtra( worker_options.na) ;
        Toast.makeText( getApplicationContext() , wnum , Toast.LENGTH_SHORT ).show() ;

        tv = findViewById(R.id.wp_tv_work) ;

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                pending_appointment pa1 = li.get(i) ;
                idpa = pa1.getId() ;
                //Toast.makeText(getApplicationContext() , "af" , Toast.LENGTH_SHORT).show() ;

                cid = pa1.getCid() ;
                da = pa1.getD() ;

                dbca = FirebaseDatabase.getInstance().getReference("confirmed") ;

                AlertDialog.Builder builder1 = new AlertDialog.Builder( worker_pending.this ) ;
                builder1.setMessage("Are you sure you want to accept " + wrk + " for " + FirebaseDatabase.getInstance().getReference("customer").child(cid).child("name").toString() + " at " + da + " ??")
                        .setCancelable(true)
                        .setPositiveButton("YES,ACCEPT!!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String idc = dbca.push().getKey() ;
                                confirm_appointment acc = new confirm_appointment( idc , cid , wnum , wrk , da ) ;
                                dbca.child(idc).setValue(acc) ;

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
    protected void onStart() {
        super.onStart();

        dbw.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                wrk = dataSnapshot.child("work").getValue().toString() ;
                tv.setText("Pending appointments for " + wrk + " are :") ;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbpa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                li.clear() ;

                for (DataSnapshot appsnap : dataSnapshot.getChildren()){
                    pending_appointment p = appsnap.getValue(pending_appointment.class) ;
                    if (p.getWork().equals(wrk) && p.getD().after(cdate) ){
                        li.add(p) ;
                    }
                }

                customer_pending_for_worker adapter = new customer_pending_for_worker( worker_pending.this , li ) ;

                lv.setAdapter(adapter) ;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) ;
    }
}
