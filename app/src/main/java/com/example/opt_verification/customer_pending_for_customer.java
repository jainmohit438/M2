package com.example.opt_verification;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class customer_pending_for_customer extends ArrayAdapter<pending_appointment> {

    private Activity cont ;
    private List<pending_appointment> pa1;

    public customer_pending_for_customer(Activity context , List<pending_appointment> plist ){
        super( context , R.layout.customer_pending_for_worker , plist);
        this.cont = context ;
        this.pa1 = plist ;
    }

    public View getView(int pos , View convert , ViewGroup par ){
        LayoutInflater inf = cont.getLayoutInflater() ;

        View lv_item = inf.inflate(R.layout.customer_pending_for_worker , null , true) ;

        TextView tv_cname = lv_item.findViewById(R.id.all_tv_cname) ;
        TextView tv_date = lv_item.findViewById(R.id.all_tv_date) ;

        pending_appointment pa = pa1.get(pos) ;

        Date d = pa.getD() ;

        tv_cname.setText(pa.getWork());
        tv_date.setText( pa.getD().toString() );

        return lv_item ;
    }

}
