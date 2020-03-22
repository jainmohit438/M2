package com.example.opt_verification;

import java.util.Date;

public class confirm_appointment {

    String id , cname , wname , work ;
    Date d ;

    public confirm_appointment() {
    }

    public confirm_appointment(String id, String cname, String wname, String work, Date d) {
        this.id = id;
        this.cname = cname;
        this.wname = wname;
        this.work = work;
        this.d = d;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }
}
