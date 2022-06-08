package com.example.boken;

public class User {
    public String uid;
    public String firstname;
    public String lastname ;
    public String phone;
    public String email;

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getUid() {
        return uid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public User(String uid, String firstname, String lastname, String phone, String email) {
        this.uid = uid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone= phone;
        this.email = email;
    }
}

