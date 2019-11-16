package com.example.opt_verification;

public class services_detail {

    String name , salary ;

    public services_detail() {
    }

    public services_detail(String name, String salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
