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
import java.util.regex.Pattern;

public class add_admin extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*\\d)(?=.*[A-Z])([@$%&#])[0-9a-zA-Z]{4,}$");

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


    private boolean validateaname() {
        String anameinput =et_name.getText().toString().trim();
        String apattern = "^[a-z0-9_-]{5,15}$";

        if (!anameinput.matches(apattern)) {
            Toast.makeText(this,"Enter a valid name",Toast.LENGTH_LONG).show();
            return true;
        }

        else {
            return false;
        }
    }

    private boolean validateamobile() {
        String amobileinput =et_number.getText().toString().trim();
        String mobileapattern = "[0-9]{10}";
        if (!amobileinput.matches(mobileapattern)) {
            Toast.makeText(this,"Enter a valid mobile no",Toast.LENGTH_LONG).show();
            return true;
        }

        else {
            return true;
        }


    }

    private boolean validateapassword () {
        String apasswordinput =et_password.getText().toString().trim();

        if (apasswordinput.isEmpty()) {
            Toast.makeText(this,"Password can not be empty",Toast.LENGTH_LONG).show();
            return true;
        }

        else if (!PASSWORD_PATTERN.matcher(apasswordinput).matches()) {
            Toast.makeText(this,"Password too weak",Toast.LENGTH_LONG).show();
            return true;
        }

        else {
            return false;
        }
    }

    private void addAdmin(){

        String name = et_name.getText().toString().trim() ;
        String num = et_number.getText().toString().trim() ;
        String pass = et_password.getText().toString().trim() ;

        if (validateaname() || validateamobile()|| validateapassword()){

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
