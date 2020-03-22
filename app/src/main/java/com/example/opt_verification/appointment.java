package com.example.opt_verification;

import java.sql.Time;
import java.util.Date;

public class appointment {

    String id , customer , worker , work ;
    Date d ;

    public appointment() {
    }

    public appointment(String id, String customer, String worker, String work, Date d) {
        this.id = id;
        this.customer = customer;
        this.worker = worker;
        this.work = work;
        this.d = d;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
