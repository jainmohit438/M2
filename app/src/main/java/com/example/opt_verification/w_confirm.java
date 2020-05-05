package com.example.opt_verification;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class w_confirm extends ArrayAdapter<confirm_appointment> {

    private Activity cont;
    private List<confirm_appointment> a1;

    public w_confirm(Activity context, List<confirm_appointment> alist) {
        super(context, R.layout.customer_confirm, alist);
        this.cont = context;
        this.a1 = alist;
    }

    public View getView(int pos, View convert, ViewGroup par) {
        LayoutInflater inf = cont.getLayoutInflater();

        View lv_item = inf.inflate(R.layout.customer_confirm, null, true);

        TextView tv_wrk = lv_item.findViewById(R.id.cc_tv_work);
        TextView tv_cname = lv_item.findViewById(R.id.cc_tv_wname);
        TextView tv_date = lv_item.findViewById(R.id.cc_tv_date);

        confirm_appointment a = a1.get(pos);

        tv_wrk.setText(a.getWork());
        tv_cname.setText( a.getCname() );
        tv_date.setText(a.getD().toString());

        return lv_item;
    }

}
