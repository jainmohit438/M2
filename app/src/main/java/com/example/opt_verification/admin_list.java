package com.example.opt_verification;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class admin_list extends ArrayAdapter<admin_details> {
    
    private Activity cont ;
    private List<admin_details> a1;

    public admin_list(Activity context , List<admin_details> alist ){
        super( context , R.layout.admin_list_layout , alist);
        this.cont = context ;
        this.a1 = alist ;
    }

    public View getView(int pos , View convert , ViewGroup par ){
        LayoutInflater inf = cont.getLayoutInflater() ;

        View lv_item = inf.inflate(R.layout.admin_list_layout , null , true) ;

        TextView tv_admin_name = lv_item.findViewById(R.id.tv_admin_name) ;
        TextView tv_admin_number = lv_item.findViewById(R.id.tv_admin_number) ;

        admin_details a = a1.get(pos) ;

        tv_admin_name.setText(a.getAdmin_name());
        tv_admin_number.setText(a.getAdmin_number());

        return lv_item ;
    }

}
