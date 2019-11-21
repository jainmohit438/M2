package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class customer_options extends AppCompatActivity {

    ListView lvdisplay ;

    List<services_detail> servicelist ;

    TextView dat ;

    DatabaseReference dbservice , dbappointment;

    private DatePickerDialog.OnDateSetListener d ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_options);

        lvdisplay = findViewById(R.id.lv1) ;

        dat = findViewById(R.id.tv_appointment_date) ;

        servicelist = new ArrayList<>() ;

        Calendar cal = Calendar.getInstance() ;

        final int date = cal.get(Calendar.DAY_OF_MONTH) ;
        final int month = cal.get(Calendar.MONTH) ;
        final int year = cal.get(Calendar.YEAR) ;

        dbservice = FirebaseDatabase.getInstance().getReference("services") ;

        dbappointment = FirebaseDatabase.getInstance().getReference("appointment") ;

        lvdisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                services_detail s = servicelist.get(i) ;

                DatePickerDialog dp = new DatePickerDialog( getApplicationContext() ,
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth ,
                        d , year , month , date ) ;

                //dp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dp.show() ;

            }
        });

        d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int da) {

                m += 1 ;
                String final_date = da + "/" + m + "/" + y ;
                dat.setText(final_date) ;

            }
        } ;
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

                services_list adapter = new services_list( customer_options.this , servicelist ) ;

                lvdisplay.setAdapter(adapter) ;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
