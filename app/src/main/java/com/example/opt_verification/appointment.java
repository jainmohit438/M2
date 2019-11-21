package com.example.opt_verification;

import java.sql.Time;
import java.util.Date;

public class appointment {

    String customer , worker , work ;
    Date d ;
    Time t ;

    public appointment() {
    }

    public appointment(String customer, String worker, String work, Date d, Time t) {
        this.customer = customer;
        this.worker = worker;
        this.work = work;
        this.d = d;
        this.t = t;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public Time getT() {
        return t;
    }

    public void setT(Time t) {
        this.t = t;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
