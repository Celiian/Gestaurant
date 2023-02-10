package com.example.gestaurant.models;

public class Employee {
    private String firstName;
    private String lastName;
    private String job;
    private String phone;

    public Employee(String name, String lastName, String job, String phone) {
        this.firstName = name;
        this.lastName = lastName;
        this.job = job;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "employee{" +
                "name='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", job='" + job + '\'' +
                ", phone=" + phone +
                '}';
    }
}