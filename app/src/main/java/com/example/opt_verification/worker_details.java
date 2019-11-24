package com.example.opt_verification;

public class worker_details {

    String name , aadhar , work , number ;
    Double rating ;
    Integer jobs_done ;

    public worker_details() {
        rating = 10.0 ;
        jobs_done = 0;
    }

    public worker_details(String name, String aadhar, String work, String number) {
        this.name = name;
        this.aadhar = aadhar;
        this.work = work;
        this.number = number;
        rating = 10.0 ;
        jobs_done = 0;
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getJobs_done() {
        return jobs_done;
    }

    public void setJobs_done(Integer jobs_done) {
        this.jobs_done = jobs_done;
    }
}
