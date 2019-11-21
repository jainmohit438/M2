package com.example.opt_verification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_admin extends AppCompatActivity {

    EditText et_name , et_number , et_password ;

    Button btn_add ;

    DatabaseReference dbadmin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        et_name = findViewById(R.id.et_name) ;
        et_number = findViewById(R.id.et_number) ;
        et_password = findViewById(R.id.et_password) ;

        btn_add = findViewById(R.id.btn_create_new_admin) ;

        dbadmin = FirebaseDatabase.getInstance().getReference("admin") ;

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addAdmin();
            }
        });

    }

    private void addAdmin(){

        String name = et_name.getText().toString().trim() ;
        String num = et_number.getText().toString().trim() ;
        String pass = et_password.getText().toString().trim() ;

        if (name.isEmpty() || num.isEmpty()|| pass.isEmpty()){

            Toast.makeText(getApplicationContext() , "Please enter details properly." , Toast.LENGTH_SHORT).show();

        }
        else {

            admin_details a = new admin_details(name , num , pass) ;

            dbadmin.child(num).setValue(a) ;

            Toast.makeText(getApplicationContext() , "Admin added successfully" , Toast.LENGTH_SHORT).show() ;

            et_name.setText("") ;
            et_password.setText("") ;
            et_number.setText("") ;

            finish() ;
            Intent intent = new Intent( getApplicationContext() , admin_options.class) ;
            startActivity(intent) ;

        }

    }
}
