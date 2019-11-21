package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class admin_display extends AppCompatActivity {

    public static final String adminname = "name" ;
    public static final String adminnum = "num" ;

    Button btn_master ;

    ListView lvdisplay ;

    List<admin_details> adminlist ;

    DatabaseReference dbadmin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_display);

        btn_master = findViewById(R.id.btn_admin_master) ;

        lvdisplay = findViewById(R.id.lv_admin_display) ;

        adminlist = new ArrayList<>() ;

        dbadmin = FirebaseDatabase.getInstance().getReference("admin") ;

        btn_master.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent intent = new Intent(getApplicationContext() , admin_master.class) ;
                Intent intent = new Intent(getApplicationContext() , admin_master.class) ;
                startActivity(intent);

            }
        });

        lvdisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                admin_details ad = adminlist.get(i) ;

                Intent intent = new Intent( getApplicationContext() , admin_verify.class) ;

                intent.putExtra(adminname , ad.getAdmin_name()) ;
                intent.putExtra(adminnum , ad.getAdmin_number()) ;

                startActivity(intent) ;

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        dbadmin.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                adminlist.clear() ;

                for (DataSnapshot adminsnap : dataSnapshot.getChildren()){
                    admin_details w2 = adminsnap.getValue(admin_details.class) ;
                    adminlist.add(w2) ;
                }

                admin_list adapter = new admin_list( admin_display.this , adminlist ) ;

                lvdisplay.setAdapter(adapter) ;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
