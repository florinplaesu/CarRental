package com.example.carrental.Model;

public class User {

    private String name, surname, email, licenseId;
    private int age;

    public User(){}

    public User(String name, String surname, String email, String licenseId, int age) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.licenseId = licenseId;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public String getSurname() {
        return surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
