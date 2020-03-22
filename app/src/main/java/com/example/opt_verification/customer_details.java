package com.example.opt_verification;

import java.util.List;

public class customer_details {

    String id ;
    String cname;
    String cusername;
    String cemail;
    String cphone;
    List<appointment> a;

    public customer_details() {
        this.a = null ;
    }

    public customer_details(String id , String cname, String cusername, String cemail , String cphone) {
        this.id = id ;
        this.cname = cname;
        this.cusername = cusername;
        this.cemail = cemail;
        this.cphone = cphone;
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

    public String getCusername() { return cusername; }

    public void setCusername(String cusername) { this.cusername = cusername;}

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public List<appointment> getA() {
        return a;
    }

    public void setA(List<appointment> a) {
        this.a = a;
    }
}
