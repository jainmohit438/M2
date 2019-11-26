package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
    DatabaseReference dbpa , dbwa , dbca ;
    String idpa , wrk , cname , wname ;
    Date da ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_pending);

        lv = findViewById(R.id.wp_lv) ;
        li = new ArrayList<>() ;
        dbpa = FirebaseDatabase.getInstance().getReference("pappointment") ;
        dbwa = FirebaseDatabase.getInstance().getReference("wappointment") ;
        dbca = FirebaseDatabase.getInstance().getReference("cappointment") ;

        wrk = getIntent().getStringExtra( worker_options.w ) ;
        wname = getIntent().getStringExtra( worker_options.na) ;

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                pending_appointment pa1 = li.get(i) ;
                idpa = pa1.getId() ;
                //Toast.makeText(getApplicationContext() , "af" , Toast.LENGTH_SHORT).show() ;

                cname = pa1.getCname() ;
                da = pa1.getD() ;

                dbwa = FirebaseDatabase.getInstance().getReference("wappointment").child(wname) ;
                dbca = FirebaseDatabase.getInstance().getReference("cappointment").child(cname) ;

                AlertDialog.Builder builder1 = new AlertDialog.Builder( worker_pending.this ) ;
                builder1.setMessage("Are you sure you want to accept " + wrk + " for " + cname + " at " + da + " ??")
                        .setCancelable(true)
                        .setPositiveButton("YES,ACCEPT!!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                confirm_appointment ac = new confirm_appointment(cname , wname , wrk , da ) ;

                                String idw = dbwa.push().getKey() ;
                                dbwa.child(idw).setValue(ac) ;

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
    protected void onStart() {
        super.onStart();

        dbpa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                li.clear() ;

                for (DataSnapshot appsnap : dataSnapshot.getChildren()){
                    pending_appointment p = appsnap.getValue(pending_appointment.class) ;
                    if (p.getWork().equals(wrk)){
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
