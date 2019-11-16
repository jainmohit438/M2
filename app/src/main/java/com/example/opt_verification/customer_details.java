package com.example.opt_verification;

public class customer_details {

    String cname;
    String cemail;
    String cpassword;
    String cphone;

    public customer_details() {
    }

    public customer_details(String cname, String cemail, String cpassword, String cphone) {
        this.cname = cname;
        this.cemail = cemail;
        this.cpassword = cpassword;
        this.cphone = cphone;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

}
