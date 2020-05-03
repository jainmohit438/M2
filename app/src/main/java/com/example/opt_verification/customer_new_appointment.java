package com.example.opt_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class customer_new_appointment extends AppCompatActivity
                    implements DatePickerDialog.OnDateSetListener
                    , TimePickerDialog.OnTimeSetListener {

    ListView lvdisplay ;

    List<services_detail> servicelist ;

    DatabaseReference dbservice , dbpappointment ;

    Date date;

    private DatePickerDialog.OnDateSetListener date_input ;
    private TimePickerDialog.OnTimeSetListener time_input ;

    DatePickerDialog dp ;
    TimePickerDialog tp ;

    Date current_date = new Date() ;

    Integer year_final , month_final , date_final , hour_final , minute_final ;

    FirebaseAuth fbauth ;
    FirebaseUser user ;

    services_detail s ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_new_appointment);

        lvdisplay = findViewById(R.id.cna_lv) ;

        servicelist = new ArrayList<>() ;

        dbservice     = FirebaseDatabase.getInstance().getReference("services")    ;
        dbpappointment = FirebaseDatabase.getInstance().getReference("pappointment") ;

        fbauth = FirebaseAuth.getInstance() ;
        user = fbauth.getCurrentUser() ;

        if ( user == null){
            finish() ;
            Intent intent = new Intent( getApplicationContext() , customer_login.class) ;
            startActivity(intent) ;
        }

        lvdisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                s = servicelist.get(i) ;

                Calendar cal = Calendar.getInstance() ;

                int d1 = cal.get(Calendar.DAY_OF_MONTH) ;
                int month = cal.get(Calendar.MONTH) ;
                int year = cal.get(Calendar.YEAR) ;

                dp = new DatePickerDialog( customer_new_appointment.this , customer_new_appointment.this
                        , year , month , d1 ) ;

                dp.show() ;

            }
        }) ;
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

                services_list adapter = new services_list( customer_new_appointment.this , servicelist ) ;

                lvdisplay.setAdapter(adapter) ;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int y, int m, int d) {

        year_final = y - 1900;
        month_final = m ;
        date_final = d ;

        Calendar c = Calendar.getInstance() ;
        int hour = c.get(Calendar.HOUR_OF_DAY) , minute = c.get(Calendar.MINUTE) ;

        tp = new TimePickerDialog( customer_new_appointment.this , customer_new_appointment.this
                , hour , minute , DateFormat.is24HourFormat(this)) ;

        tp.show() ;

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int h, int m) {

        hour_final = h ;
        minute_final = m ;

        date = new Date(year_final , month_final , date_final , hour_final , minute_final) ;

        set_appointment() ;

    }

    private void set_appointment(){

        if (date.after(current_date)){
            String idw = dbpappointment.push().getKey() ;
            pending_appointment pa = new pending_appointment ( idw , user.getUid() , s.getName() , date ) ;
            dbpappointment.child(idw).setValue(pa) ;

            Toast.makeText(getApplicationContext() , "Appointment set." , Toast.LENGTH_SHORT).show() ;

        }
        else{
            Toast.makeText( getApplicationContext() , "Please enter a valid date." , Toast.LENGTH_SHORT).show() ;
            dp.show();
        }
    }
}
