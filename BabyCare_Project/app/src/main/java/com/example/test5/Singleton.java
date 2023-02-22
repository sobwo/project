package com.example.test5;

public class Singleton {
    private int _id;
    private String[] name = new String[10];;
    private String[] gender = new String[10];;
    private String[] picture = new String[10];;
    private int[] birthdate = new int[10];
    private int num = 0;

    private Singleton() { }
    public static class SingletonHolder{
        public static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance(){
        return SingletonHolder.INSTANCE;
    }

//    public int get_id() {return _id;}
//
//    public void set_id(int _id) { this._id = _id;}

    public void setName(String getName) {
        this.name[num] = getName;
    }
    public void setGender(String getGender) {
        this.gender[num] = getGender;
    }
    public void setPicture(String getPicture) { this.picture[num] = getPicture; }
    public void setNum(int num) { this.num = num; }
    public void setBirthdate(String getBirthdate) {
        this.birthdate[num] = Integer.parseInt(getBirthdate);
        this.num++;
    }

    public String[] getName() { return name; }
    public String[] getGender() {
        return gender;
    }
    public String[] getPicture() { return picture; }
    public int[] getBirthdate() { return birthdate; }
    public int getNum() { return num; }
}


