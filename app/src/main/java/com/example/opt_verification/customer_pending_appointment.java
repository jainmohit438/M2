package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class customer_pending_appointment extends AppCompatActivity {

    DatabaseReference dbpa , dbcustomer ;
    ListView lv ;
    List<pending_appointment> pa_list ;
    String na = "abc" ;
    FirebaseAuth fbauth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_pending_appointment);

        pa_list = new ArrayList<>() ;
        lv = findViewById(R.id.cpa_lv) ;

        na = getIntent().getStringExtra(customer_options.customer_name) ;
        dbpa = FirebaseDatabase.getInstance().getReference("pappointment") ;
        dbcustomer = FirebaseDatabase.getInstance().getReference("customer") ;
        fbauth = FirebaseAuth.getInstance() ;

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                pending_appointment pa = pa_list.get(i) ;
                final String idpa = pa.getId() ;

                AlertDialog.Builder builder = new AlertDialog.Builder( customer_pending_appointment.this ) ;
                builder.setMessage("Are you sure you want to cancel this appointment?")
                        .setCancelable(true)
                        .setPositiveButton("Yes, CANCEL!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dbpa.child(idpa).removeValue() ;
                            }
                        })
                        .setNegativeButton("No!", new DialogInterface.OnClickListener() {
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
    protected void onStart() {
        super.onStart();

        dbpa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                pa_list.clear() ;

                for (DataSnapshot psnap : dataSnapshot.getChildren()){
                    pending_appointment p = psnap.getValue(pending_appointment.class) ;
                    String n = p.getCname() ;
                    if ( na.equals( n ) ) {
                        pa_list.add(p) ;
                    }
                }

                customer_pending_for_customer adapter = new customer_pending_for_customer( customer_pending_appointment.this , pa_list );

                lv.setAdapter(adapter) ;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) ;
    }
}
