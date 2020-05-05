package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
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

public class customer_confirm_appointment extends AppCompatActivity {

    DatabaseReference dbca ;
    List<confirm_appointment> cl ;
    ListView lv ;
    private Spinner s ;
    private Date c_date = new Date() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_confirm_appointment);

        dbca = FirebaseDatabase.getInstance().getReference("confirmed") ;
        lv = findViewById(R.id.cca_lv) ;
        s = findViewById(R.id.cca_s) ;
        cl = new ArrayList<>() ;

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                final String choice = adapterView.getItemAtPosition(i).toString() ;

                dbca.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        cl.clear() ;

                        for ( DataSnapshot ds : dataSnapshot.getChildren() ){

                            confirm_appointment pa = ds.getValue(confirm_appointment.class) ;
                            String n = pa.getCid() ;
                            if( FirebaseAuth.getInstance().getCurrentUser().getUid().equals(n) )
                            {
                                if (choice.equals("Upcoming"))
                                {
                                    if( pa.getD().after(c_date) )
                                    {
                                        cl.add(pa) ;
                                    }
                                }
                                else if (choice.equals("Completed"))
                                {
                                    if( pa.getD().before(c_date) )
                                    {
                                        cl.add(pa) ;
                                    }
                                }
                            }
                        }

                        customer_confirm adapter = new customer_confirm( customer_confirm_appointment.this , cl ) ;

                        lv.setAdapter(adapter) ;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                confirm_appointment ca = cl.get(i) ;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
