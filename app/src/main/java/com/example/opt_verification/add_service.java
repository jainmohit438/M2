package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class add_service extends AppCompatActivity {

    EditText et_name , et_sal , new_sal ;

    Button btn_add ;

    ListView lv ;

    List<services_detail> servicelist ;

    DatabaseReference dbservice ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        et_name = findViewById(R.id.et_new_service) ;
        et_sal = findViewById(R.id.et_salary) ;
        new_sal = new EditText(this) ;

        btn_add = findViewById(R.id.btn_add_serv) ;

        lv = findViewById(R.id.as_lv) ;

        servicelist = new ArrayList<>() ;

        dbservice = FirebaseDatabase.getInstance().getReference("services") ;

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_services();

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final services_detail s = servicelist.get(i) ;

                final AlertDialog.Builder builder = new AlertDialog.Builder( add_service.this ) ;
                final AlertDialog.Builder builder1 = new AlertDialog.Builder( add_service.this ) ;

                builder.setMessage("Enter new salary of " + s.getName() + ".")
                        .setView(new_sal)
                        .setCancelable(false)
                        .setPositiveButton("Update salary!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final String ns = new_sal.getText().toString() ;
                                builder1.setMessage("Are you sure you want to change salary of " + s.getName() + " from " + s.getSalary() + " to " + ns + " ?")
                                        .setCancelable(true)
                                        .setPositiveButton("Yes, change!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                update_salary( s.getId() , s.getName() , ns );
                                            }
                                        })
                                        .setNegativeButton("No!", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel() ;
                                            }
                                        });

                                AlertDialog alertDialog1 = builder1.create() ;
                                alertDialog1.show() ;

                            }
                        })
                        .setNegativeButton("Delete " + s.getName() + " !" , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dbservice.child(s.getId()).removeValue() ;
                            }
                        })
                        .setNeutralButton("Let it be!", new DialogInterface.OnClickListener() {
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

    private void update_salary( String w , String n , String ns ){

        dbservice.child(w).child("salary").setValue(ns);
        Toast.makeText( getApplicationContext() , "Salary of " + n + " updated to Rs." + ns + " .", Toast.LENGTH_SHORT ).show() ;

    }

    private void add_services(){

        String name = et_name.getText().toString().trim() ;
        String sal = et_sal.getText().toString().trim() ;

        if( name.isEmpty() || sal.isEmpty() ){

            Toast.makeText( getApplicationContext() , "Please enter details properly . " , Toast.LENGTH_SHORT).show() ;

        }
        else {

            String id = dbservice.push().getKey() ;

            services_detail s = new services_detail( id , name , sal ) ;

            dbservice.child(id).setValue(s) ;

            Toast.makeText( getApplicationContext() , "Service added . " , Toast.LENGTH_SHORT).show() ;

            et_name.setText("") ;
            et_sal.setText("") ;

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        dbservice.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                servicelist.clear();

                for (DataSnapshot servicesnap : dataSnapshot.getChildren()) {
                    services_detail s2 = servicesnap.getValue(services_detail.class);
                    servicelist.add(s2);
                }

                services_list adapter = new services_list(add_service.this, servicelist);

                lv.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
