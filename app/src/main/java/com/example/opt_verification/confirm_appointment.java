package com.example.opt_verification;

import java.util.Date;

public class confirm_appointment {

    String id , cid , cname , wid , wname , work ;
    Date d ;
    Float rating ;

    public confirm_appointment() {
    }

    public confirm_appointment(String id, String cid , String cname , String wid , String wname , String work, Date d) {
        this.id = id;
        this.cid = cid;
        this.cname = cname ;
        this.wid = wid;
        this.wname = wname ;
        this.work = work;
        this.d = d;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cname) {
        this.cid = cid;
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

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

}
