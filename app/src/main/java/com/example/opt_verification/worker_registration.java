package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class worker_registration extends AppCompatActivity {

    Button btnsubmit ,btnnext ;

    EditText etname , etaadhar ;

    Spinner spnwork ;

    ListView lvdisplay ;

    List<workers> workerlist ;

    DatabaseReference dbWorker ;

    /*
    @Override
    protected void onStart() {
        super.onStart();

        dbWorker.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText( getApplicationContext() , "Button clicked. " , Toast.LENGTH_LONG).show() ;

                //workerlist.clear() ;

                for (DataSnapshot workersnap : dataSnapshot.getChildren()){
                    workers w2 = workersnap.getValue(workers.class) ;
                    workerlist.add(w2) ;
                }

                list adapter = new list( worker_registration.this , workerlist ) ;

                lvdisplay.setAdapter(adapter) ;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_registration);

        btnsubmit = findViewById(R.id.btn_submit) ;
        btnnext = findViewById(R.id.btn_next) ;

        etaadhar = findViewById(R.id.et_aadhar) ;
        etname = findViewById(R.id.et_name) ;

        spnwork = findViewById(R.id.spn_work) ;

        dbWorker = FirebaseDatabase.getInstance().getReference("workers") ;

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addWorker() ;

            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( getApplicationContext() , customer_login.class ) ;
                startActivity(intent) ;

            }
        });

    }

    private void addWorker(){

        String name = etname.getText().toString().trim() ;
        String aadhar = etaadhar.getText().toString().trim() ;
        String work = spnwork.getSelectedItem().toString() ;

        if( name.isEmpty() || aadhar.isEmpty() ){

            Toast.makeText( getApplicationContext() , "Please enter details properly . " , Toast.LENGTH_SHORT).show() ;

        }
        else {

            String id = dbWorker.push().getKey() ;

            workers w = new workers( name , aadhar , work ) ;

            dbWorker.child(id).setValue(w) ;

            Toast.makeText( getApplicationContext() , "Worker added . " , Toast.LENGTH_SHORT).show() ;

        }

    }

}
