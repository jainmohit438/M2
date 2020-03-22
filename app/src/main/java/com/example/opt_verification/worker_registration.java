package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class worker_registration extends AppCompatActivity {

    public static final String numb = "num" ;

    EditText etname , etaadhar ;

    TextView tvnum ;

    DatabaseReference dbWorker , dbservice;

    FirebaseAuth fbauth ;

    ListView lv ;

    List<services_detail> servicelist ;

    services_detail s ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_registration);

        etaadhar = findViewById(R.id.et_aadhar) ;
        etname = findViewById(R.id.et_name) ;

        tvnum = findViewById(R.id.tv_number) ;

        lv = findViewById(R.id.wr_lv) ;

        servicelist = new ArrayList<>() ;

        dbWorker = FirebaseDatabase.getInstance().getReference("workers") ;
        dbservice = FirebaseDatabase.getInstance().getReference("services") ;

        Intent intent = getIntent() ;
        tvnum.setText(intent.getStringExtra(worker_login.NUMBER) );

        fbauth = FirebaseAuth.getInstance();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                s = servicelist.get(i) ;

                addWorker();
            }
        });

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

                services_list adapter = new services_list( worker_registration.this , servicelist ) ;

                lv.setAdapter(adapter) ;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private boolean validatewname() {
        String wnameinput=etname.getText().toString().trim();

        if (wnameinput.isEmpty()) {
            Toast.makeText(this,"Enter valid name",Toast.LENGTH_LONG).show();
            return true;
        }

        else {
            return false;
        }
    }

    private void addWorker(){

        String name = etname.getText().toString().trim() ;
        String aadhar = etaadhar.getText().toString().trim() ;
        String work =  s.getName() ;
        String num = tvnum.getText().toString().trim() ;

        if( aadhar.isEmpty() || validatewname()  ){

            Toast.makeText( getApplicationContext() , "Please enter details properly . " , Toast.LENGTH_SHORT).show() ;

        }
        else if (aadhar.length()!= 12){

            etaadhar.setError( " Invalid aadhar number. " );
            etaadhar.requestFocus() ;
            return ;

        }
        else {

            worker_details w = new worker_details( name , aadhar , work , num  ) ;

            dbWorker.child(num).setValue(w) ;

            Toast.makeText( getApplicationContext() , "Registration successful. . " , Toast.LENGTH_SHORT).show() ;

            finish() ;
            Intent intent = new Intent( getApplicationContext() , worker_login.class) ;
            fbauth.signOut() ;
            startActivity(intent) ;
            Toast.makeText( worker_registration.this , "Please login again to continue." , Toast.LENGTH_SHORT).show() ;
        }

    }

}
