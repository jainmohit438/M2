package com.example.opt_verification;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class appointment_list extends ArrayAdapter<pending_appointment> {


    private Activity cont ;
    private List<pending_appointment> pa1;

    public appointment_list(Activity context , List<pending_appointment> plist ){
        super( context , R.layout.appointment_list_layout , plist);
        this.cont = context ;
        this.pa1 = plist ;
    }

    public View getView(int pos , View convert , ViewGroup par ){
        LayoutInflater inf = cont.getLayoutInflater() ;

        View lv_item = inf.inflate(R.layout.appointment_list_layout , null , true) ;

        TextView tv_work = lv_item.findViewById(R.id.all_tv_work) ;
        TextView tv_date = lv_item.findViewById(R.id.all_tv_date) ;

        pending_appointment pa = pa1.get(pos) ;

        tv_work.setText(pa.getCname());
        tv_date.setText(pa.getD().toString());

        return lv_item ;
    }


}
