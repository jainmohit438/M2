package com.example.opt_verification;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class services_list extends ArrayAdapter<services_detail> {


    private Activity cont ;
    private List<services_detail> sl;

    public services_list(Activity context , List<services_detail> slist ){
        super( context , R.layout.services_list_layout , slist);
        this.cont = context ;
        this.sl = slist ;
    }

    public View getView(int pos , View convert , ViewGroup par ){
        LayoutInflater inf = cont.getLayoutInflater() ;

        View lv_item = inf.inflate(R.layout.services_list_layout , null , true) ;

        TextView tv_service_name = lv_item.findViewById(R.id.tv_service_name) ;

        services_detail s = sl.get(pos) ;

        tv_service_name.setText(s.getName()) ;

        return lv_item ;
    }


}
