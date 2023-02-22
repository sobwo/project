package com.example.kakao_sound;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class leftdoor_open extends AppCompatActivity {
    String[] foodlist = new String[30];
    int[] timelist = new int[30];
    int food_date_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        setContentView(R.layout.activity_leftdoor);
        if (Singleton.getInstance().getFood() != null) {
            foodlist = Singleton.getInstance().getFood();
            food_date_num = Singleton.getInstance().getFood_date();
            timelist = Singleton.getInstance().getTime();
        }

        final ImageView leftdoor_open = findViewById(R.id.leftdoor);

        leftdoor_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageView t1 = findViewById(R.id.t1);

        for (int i = 0; i < food_date_num; i++) {
            if (foodlist[i].equals("고기")) {
                t1.setImageResource(R.drawable.meat);
                setcolor1(timelist, i);
            }
        }
    }

    public void setcolor1(int[] timelist, int i) {
        ImageView check1 = findViewById(R.id.check1);
        if (timelist[i] <= 7)
            check1.setImageResource(R.drawable.red);
        else if (timelist[i] > 7 && timelist[i] <= 15)
            check1.setImageResource(R.drawable.yellow);
        else if (timelist[i] > 15)
            check1.setImageResource(R.drawable.green);
    }

    public void setcolor2(int[] timelist, int i) {
        ImageView check2 = findViewById(R.id.check2);
        if (timelist[i] <= 7)
            check2.setImageResource(R.drawable.red);
        else if (timelist[i] > 7 && timelist[i] <= 15)
            check2.setImageResource(R.drawable.yellow);
        else if (timelist[i] > 15)
            check2.setImageResource(R.drawable.green);
    }

    public void setcolor3(int[] timelist, int i) {
        ImageView check3 = findViewById(R.id.check3);
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
