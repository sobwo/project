package com.example.kakao_sound;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class rightdoor_open extends AppCompatActivity {
    String[] foodlist = new String[30];
    int[] timelist = new int[30];
    int food_date_num = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        setContentView(R.layout.activity_rightdoor);

        ImageView rightdoor_open = findViewById(R.id.rightdoor);
        rightdoor_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (Singleton.getInstance().getFood()[0] != null) {
            foodlist = Singleton.getInstance().getFood();
            timelist = Singleton.getInstance().getTime();
            food_date_num = Singleton.getInstance().getFood_date();
        }

        ImageView t01 = findViewById(R.id.t01);
        ImageView t02 = findViewById(R.id.t02);

        for (int i = 0; i < food_date_num; i++) {
            if (foodlist[i].equals("배추")) {
                t01.setImageResource(R.drawable.vegetable);
                setcolor1(timelist, i);
            }
            if (foodlist[i].equals("우유")) {
                t02.setImageResource(R.drawable.milk);
                setcolor2(timelist, i);
            }
        }

    }

    public void setcolor1(int[] timelist, int i) {
        ImageView check1 = findViewById(R.id.check01);
        if (timelist[i] <= 7)
            check1.setImageResource(R.drawable.red);
        else if (timelist[i] > 7 && timelist[i] <= 15)
            check1.setImageResource(R.drawable.yellow);
        else if (timelist[i] > 15)
            check1.setImageResource(R.drawable.green);
    }

    public void setcolor2(int[] timelist, int i) {
        ImageView check2 = findViewById(R.id.check02);
        if (timelist[i] <= 7)
            check2.setImageResource(R.drawable.red);
        else if (timelist[i] > 7 && timelist[i] <= 15)
            check2.setImageResource(R.drawable.yellow);
        else if (timelist[i] > 15)
            check2.setImageResource(R.drawable.green);
    }

    public void setcolor3(int[] timelist, int i) {
        ImageView check3 = findViewById(R.id.check03);
        if (timelist[i] <= 7)
            check3.setImageResource(R.drawable.red);
        else if (timelist[i] > 7 && timelist[i] <= 15)
            check3.setImageResource(R.drawable.yellow);
        else if (timelist[i] > 15)
            check3.setImageResource(R.drawable.green);
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}

