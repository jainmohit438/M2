package com.example.opt_verification;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class worker_list extends ArrayAdapter <workers> {

    private Activity cont ;
    private List<workers> wl;

    public worker_list(Activity context , List<workers> wlist ){
        super( context , R.layout.list_layout , wlist);
        this.cont = context ;
        this.wl = wlist ;
    }

    public View getView(int pos , View convert , ViewGroup par ){
        LayoutInflater inf = cont.getLayoutInflater() ;
        View lv_item = inf.inflate(R.layout.list_layout , null , true) ;

        TextView tv_name = lv_item.findViewById(R.id.tv_name) ;
        TextView tv_aadhar = lv_item.findViewById(R.id.tv_aadhar) ;
        TextView tv_work = lv_item.findViewById(R.id.tv_work) ;

        workers w = wl.get(pos) ;

        tv_name.setText(w.getName()) ;
        tv_aadhar.setText(w.getAadhar()) ;
        tv_work.setText(w.getWork()) ;

        return lv_item ;
    }

}
