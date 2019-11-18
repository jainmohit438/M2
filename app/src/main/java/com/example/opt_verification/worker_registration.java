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

import java.util.ArrayList;
import java.util.List;

public class worker_registration extends AppCompatActivity {

    Button btnsubmit  ;

    EditText etname , etaadhar , etnum ;

    Spinner spnwork ;

    DatabaseReference dbWorker ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_registration);

        btnsubmit = findViewById(R.id.btn_submit) ;

        etaadhar = findViewById(R.id.et_aadhar) ;
        etname = findViewById(R.id.et_name) ;
        etnum = findViewById(R.id.et_num) ;

        spnwork = findViewById(R.id.spn_work) ;

        dbWorker = FirebaseDatabase.getInstance().getReference("workers") ;

        Bundle b = getIntent().getExtras() ;

        etnum.setText(b.getString("num")) ;

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addWorker() ;

            }
        });

    }

    private void addWorker(){

        String name = etname.getText().toString().trim() ;
        String aadhar = etaadhar.getText().toString().trim() ;
        String work = spnwork.getSelectedItem().toString() ;
        String num = etnum.getText().toString().trim() ;

        if( name.isEmpty() || aadhar.isEmpty() ){

            Toast.makeText( getApplicationContext() , "Please enter details properly . " , Toast.LENGTH_SHORT).show() ;

        }
        else if (aadhar.length()!= 12){

            etaadhar.setError( " Invalid aadhar number. " );
            etaadhar.requestFocus() ;
            return ;

        }
        else {

            worker_details w = new worker_details( name , aadhar , work , num ) ;

            dbWorker.child(num).setValue(w) ;

            Toast.makeText( getApplicationContext() , "Worker added . " , Toast.LENGTH_SHORT).show() ;

        }

    }

}
