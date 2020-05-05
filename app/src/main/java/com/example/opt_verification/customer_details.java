package com.example.opt_verification;

import java.util.List;

public class customer_details {

    String id ;
    String cname;
    String cusername;
    String cemail;
    String cphone;
    String caddress ;

    public customer_details() {

    }

    public customer_details(String id , String cname, String cusername, String cemail , String cphone , String caddress) {
        this.id = id ;
        this.cname = cname;
        this.cusername = cusername;
        this.cemail = cemail;
        this.cphone = cphone;
        this.caddress = caddress ;
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

    public String getCAddress() {
        return caddress;
    }

    public void setCAddress(String address) {
        this.caddress = address;
    }

}
