package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class customer_pending_appointment extends AppCompatActivity {

    DatabaseReference dbpa ;
    ListView lv ;
    List<pending_appointment> pa_list ;
    String cname ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_pending_appointment);

        cname = getIntent().getStringExtra(customer_options.customer_name) ;
        dbpa = FirebaseDatabase.getInstance().getReference("pappointment").child(cname) ;
        pa_list = new ArrayList<>() ;
        lv = findViewById(R.id.cpa_lv) ;

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
                    if (p.cname.equals(cname)){
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
