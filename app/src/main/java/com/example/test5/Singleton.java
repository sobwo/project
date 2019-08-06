package com.example.test5;

public class Singleton {
    int _id;
    String getName;
    String getGender;
    String getPicture;
    String getBirthdate;

    private Singleton() { }
    public static class SingletonHolder{
        public static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public int get_id() {return _id;}

    public void set_id(int _id) { this._id = _id;}

    public String getName() {
        return getName;
    }

    public void setName(String getName) {
        this.getName = getName;
    }

    public String getGender() {
        return getGender;
    }

    public void setGender(String getGender) {
        this.getGender = getGender;
    }

    public String getPicture() {
        return getPicture;
    }

    public void setPicture(String getPicture) {
        this.getPicture = getPicture;
    }

    public String getBirthdate() {
        return getBirthdate;
    }

    public void setBirthdate(String getBirthdate) {
        this.getBirthdate = getBirthdate;
    }
}


