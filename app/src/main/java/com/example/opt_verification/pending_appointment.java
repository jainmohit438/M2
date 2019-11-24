package com.example.opt_verification;

import java.util.Date;

public class pending_appointment {

    String id , cname ;
    Date d;

    public pending_appointment() {
    }

    public pending_appointment(String id , String cname , Date d) {
        this.id = id ;
        this.cname = cname;
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

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }
}
