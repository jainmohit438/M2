package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class customer_confirm_appointment extends AppCompatActivity {

    DatabaseReference dbca ;
    List<appointment> cl ;
    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_confirm_appointment);

        String n = getIntent().getStringExtra(customer_options.customer_name) ;
        dbca = FirebaseDatabase.getInstance().getReference("cappointment").child(n) ;
        lv = findViewById(R.id.cca_lv) ;
        cl = new ArrayList<>() ;
    }

    @Override
    protected void onStart() {
        super.onStart();

        dbca.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                cl.clear() ;

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    appointment a = ds.getValue(appointment.class) ;
                    cl.add(a) ;
                }

                customer_confirm adapter = new customer_confirm( customer_confirm_appointment.this , cl) ;
                lv.setAdapter(adapter) ;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) ;
    }
}
