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

public class customer_display extends AppCompatActivity {

    ListView lvdisplay ;

    List<customer_details> customerlist ;

    DatabaseReference dbcustomer ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_display);

        lvdisplay = findViewById(R.id.lv_customer_display) ;

        customerlist = new ArrayList<>() ;

        dbcustomer = FirebaseDatabase.getInstance().getReference("customer") ;

    }

    @Override
    protected void onStart() {
        super.onStart();

        dbcustomer.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                customerlist.clear() ;

                for (DataSnapshot customersnap : dataSnapshot.getChildren()){
                    customer_details w2 = customersnap.getValue(customer_details.class) ;
                    customerlist.add(w2) ;
                }

                customer_list adapter = new customer_list( customer_display.this , customerlist ) ;

                lvdisplay.setAdapter(adapter) ;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
