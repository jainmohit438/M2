package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class worker_display extends AppCompatActivity {

    ListView lvdisplay ;

    List<worker_details> workerlist ;

    DatabaseReference dbWorker ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_display);

        lvdisplay = findViewById(R.id.lv_worker_display) ;

        workerlist = new ArrayList<>() ;

        dbWorker = FirebaseDatabase.getInstance().getReference("workers") ;

    }

    @Override
    protected void onStart() {
        super.onStart();

        dbWorker.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                workerlist.clear() ;

                for (DataSnapshot workersnap : dataSnapshot.getChildren()){
                    worker_details w2 = workersnap.getValue(worker_details.class) ;
                    workerlist.add(w2) ;
                }

                worker_list adapter = new worker_list( worker_display.this , workerlist ) ;

                lvdisplay.setAdapter(adapter) ;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
