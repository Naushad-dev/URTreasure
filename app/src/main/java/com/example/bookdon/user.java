package com.example.bookdon;

public class user {
    private String name,email,password,branch,year;

    public user() {
    }



    public user(String name, String email, String password, String branch, String year) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.branch=branch;
        this.year=year;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getBranch() {
        return branch;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
