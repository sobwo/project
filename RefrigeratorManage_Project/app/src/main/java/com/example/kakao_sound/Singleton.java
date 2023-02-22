package com.example.kakao_sound;

public class Singleton {
    private String[] food = new String[30];
    private String[] date = new String[30];
    private int[] time = new int[30];
    private int[] count= new int[30];
    private int food_date = 0;
    private String[] food_dialog = new String[30];
    private int num_dialog = 0;

    private Singleton() { }
    public static class SingletonHolder{
        public static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void setFood(String[] food){
        this.food = food;
    }
    public void setDate(String[] date){
        this.date = date;
    }
    public void setCount(int[] count){ this.count = count; }
    public void setTime(int[] time){
        this.time = time;
    }
    public void setFood_date(int food_date){
        this.food_date = food_date;
    }
    public void setFood_txt(String food_txt){
        this.food[food_date] = food_txt; }

    public void setDate_txt(String date_txt) {
        this.date[food_date] = date_txt;
    }
    public void setCount_txt(String count_txt){
        this.count[food_date] = Integer.parseInt(count_txt);
        this.food_date++;
    }
    public void setCount_txt_2(String count_txt_2,int index){
        this.count[index] = Integer.parseInt(count_txt_2);
    }
    public void setfood_dialog(String food_dialog){
        this.food_dialog[num_dialog] = food_dialog;
    }
    public void setNum_dialog(int num_dialog){
        this.num_dialog=num_dialog;
    }
    public void setCount_alter(String count_alter,int food_date_alter){
        this.count[food_date_alter]=Integer.parseInt(count_alter);
    }
    public void setFood_alter(String food,int food_date_alter){
        this.food[food_date_alter] = food;
        while(this.food[food_date_alter+1]!=null)
        {
            this.food[food_date_alter]=this.food[food_date_alter+1];
            food_date_alter++;
        }
    }
    public void setCount_alter2(int count_alter,int food_date_alter){
        this.count[food_date_alter]= count_alter;
        while(this.count[food_date_alter+1]!=0)
        {
            this.count[food_date_alter]=this.count[food_date_alter+1];
            food_date_alter++;
        }
    }
    public void setDate_alter(String date,int food_date_alter){
        this.date[food_date_alter] = date;
        while(this.date[food_date_alter+1]!=null)
        {
            this.food[food_date_alter]=this.food[food_date_alter+1];
            food_date_alter++;
        }
    }
    public void setTime_alter(int time,int food_date_alter){
        this.time[food_date_alter] = time;
        while(this.time[food_date_alter+1]!=0)
        {
            this.food[food_date_alter]=this.food[food_date_alter+1];
            food_date_alter++;
            this.food_date = this.food_date-1;
        }
    }
    public String[] getFood(){
        return food;
    }
    public String[] getDate(){
        return date;
    }
    public int[] getCount(){ return count;}
    public int[] getTime(){
        return time;
    }
    public int getFood_date(){
        return food_date;
    }
    public String[] getFood_dialog(){return food_dialog;}
    public int getNum_dialog(){return num_dialog;}
}

