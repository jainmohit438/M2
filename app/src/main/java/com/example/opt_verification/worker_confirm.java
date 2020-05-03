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

public class worker_confirm extends AppCompatActivity {

    ListView lv ;
    List<confirm_appointment> l ;
    DatabaseReference dbca ;
    String na ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_confirm);

        lv = findViewById(R.id.wc_lv) ;
        l = new ArrayList<>() ;

        na = getIntent().getStringExtra( worker_options.na ) ;
        dbca = FirebaseDatabase.getInstance().getReference("confirmed") ;

    }

    @Override
    protected void onStart() {
        super.onStart();

        dbca.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                l.clear() ;
                for (DataSnapshot ds : dataSnapshot.getChildren() ){
                    confirm_appointment ca = ds.getValue(confirm_appointment.class) ;
                    if( ca.getWid().equals(na) ){
                        l.add(ca) ;
                    }
                }

                w_confirm adapter = new w_confirm( worker_confirm.this , l );
                lv.setAdapter(adapter) ;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }) ;
    }
}
