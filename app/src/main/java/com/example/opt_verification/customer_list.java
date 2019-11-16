package com.example.opt_verification;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class customer_list extends ArrayAdapter<customer_details> {

    private Activity cont ;
    private List<customer_details> cl;

    public customer_list(Activity context , List<customer_details> clist ){
        super( context , R.layout.customer_list_layout , clist);
        this.cont = context ;
        this.cl = clist ;
    }

    public View getView(int pos , View convert , ViewGroup par ){
        LayoutInflater inf = cont.getLayoutInflater() ;

        View lv_item = inf.inflate(R.layout.customer_list_layout , null , true) ;

        TextView tv_customer_name = lv_item.findViewById(R.id.tv_customer_name) ;
        TextView tv_customer_email = lv_item.findViewById(R.id.tv_customer_email) ;
        TextView tv_customer_number = lv_item.findViewById(R.id.tv_customer_number) ;

        customer_details c = cl.get(pos) ;

        tv_customer_name.setText(c.getCname());
        tv_customer_email.setText(c.getCemail());
        tv_customer_number.setText(c.getCphone());

        return lv_item ;
    }


}
