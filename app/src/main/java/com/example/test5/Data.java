package com.example.test5;

public class Data {

    private String name;
    private String gender;
    private String birthdate;

    public String getName() {
        name = Singleton.getInstance().getName();
        return name;
    }

    public void setName(String name) {
        name = Singleton.getInstance().getName();
        this.name = name;
    }

    public String getGender() {
        gender = Singleton.getInstance().getGender();
        return gender;
    }

    public void setGender(String gender) {
        gender = Singleton.getInstance().getGender();
        this.gender = gender;
    }

    public String getBirthdate() {
        birthdate = Singleton.getInstance().getBirthdate();
        return birthdate;
    }

    public void setBirthdate(String gender) {
        birthdate = Singleton.getInstance().getGender();
        this.birthdate = birthdate;
    }

    public Data(String name) {
        this.name = name;
    }

}