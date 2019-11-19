package com.example.opt_verification;

public class worker_details {

    String name , aadhar , work , number ;

    public worker_details() {
    }

    public worker_details(String name, String aadhar, String work, String number) {
        this.name = name;
        this.aadhar = aadhar;
        this.work = work;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
