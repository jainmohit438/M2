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

public class customer_options extends AppCompatActivity {

    ListView lvdisplay ;

    List<services_detail> servicelist ;

    DatabaseReference dbservice ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_options);

        lvdisplay = findViewById(R.id.lv1) ;

        servicelist = new ArrayList<>() ;

        dbservice = FirebaseDatabase.getInstance().getReference("services") ;

    }

    @Override
    protected void onStart() {
        super.onStart();

        dbservice.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                servicelist.clear() ;

                for (DataSnapshot servicesnap : dataSnapshot.getChildren()){
                    services_detail s2 = servicesnap.getValue(services_detail.class) ;
                    servicelist.add(s2) ;
                }

                services_list adapter = new services_list( customer_options.this , servicelist ) ;

                lvdisplay.setAdapter(adapter) ;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
