package com.example.test5;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Care_list extends AppCompatActivity {
    String vaccination_name[] = {"결핵", "B형 간염", "디프테리아", "폴리오", "b형헤모필루스인플루엔자", "폐렴구균", "홍역/유행성이하선염/풍진", "수두", "A형간염", "일본뇌염", "사람유두종바이러스 감염증", "인플루엔자"};
    LinearLayout outlayout;
    TextView name[] = new TextView[30];
    TextView blank, blank2;
    int num = 0, j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        outlayout = findViewById(R.id.outLayout);

        for (int i = 0; i < vaccination_name.length; i++) {
            switch (vaccination_name[i]) {
                case "결핵":
                    blank = new TextView(this);
                    blank.setBackgroundColor(Color.BLACK);
                    blank.setWidth(1);
                    blank.setHeight(60);
                    blank2 = new TextView(this);
                    blank2.setWidth(20);
                    blank2.setHeight(60);
                    LinearLayout layout = new LinearLayout(this);
                    layout.setOrientation(LinearLayout.HORIZONTAL);
                    name[num] = new TextView(this);
                    name[num].setText(vaccination_name[i]);
                    name[num].setTextSize(15);
                    name[num].setGravity(Gravity.CENTER);
                    name[num].setWidth(300);
                    name[num].setHeight(60);
                    CheckBox checkBox = new CheckBox(this);
                    checkBox.setText("1차");
                    checkBox.setHeight(60);
                    layout.addView(name[num]);
                    layout.addView(blank);
                    layout.addView(blank2);
                    layout.addView(checkBox);
                    outlayout.addView(layout);
                    num++;
                    break;
                case "B형 간염":
                    blank = new TextView(this);
                    blank.setBackgroundColor(Color.BLACK);
                    blank.setWidth(1);
                    blank.setHeight(60);
                    blank2 = new TextView(this);
                    blank2.setWidth(20);
                    blank2.setHeight(60);
                    LinearLayout layout2 = new LinearLayout(this);
                    layout2.setOrientation(LinearLayout.HORIZONTAL);
                    name[num] = new TextView(this);
                    name[num].setText(vaccination_name[i]);
                    name[num].setTextSize(15);
                    name[num].setGravity(Gravity.CENTER);
                    name[num].setWidth(300);
                    name[num].setHeight(60);
                    layout2.addView(name[num]);
                    layout2.addView(blank);
                    layout2.addView(blank2);
                    CheckBox checkBox1[] = new CheckBox[3];
                    for (j = 0; j < 3; j++) {
                        checkBox1[j] = new CheckBox(this);
                        checkBox1[j].setHeight(60);
                        checkBox1[j].setText((j + 1) + "차");
                        layout2.addView(checkBox1[j]);
                    }
                    outlayout.addView(layout2);
                    num++;
                    break;
                case "디프테리아":
                    LinearLayout layout3 = new LinearLayout(this);
                    layout3.setOrientation(LinearLayout.HORIZONTAL);
                    name[num] = new TextView(this);
                    name[num].setText(vaccination_name[i]);
                    name[num].setTextSize(15);
                    name[num].setGravity(Gravity.CENTER);
                    name[num].setWidth(300);
                    name[num].setHeight(60);
                    layout3.addView(name[num]);
                    CheckBox checkBox2[] = new CheckBox[5];
                    for (j = 0; j < 3; j++) {
                        checkBox2[j] = new CheckBox(this);
                        checkBox2[j].setHeight(60);
                        checkBox2[j].setText((j + 1) + "차");
                        layout3.addView(checkBox2[j]);
                    }
                    outlayout.addView(layout3);
                    num++;
                    break;
                case "폴리오":
                    LinearLayout layout4 = new LinearLayout(this);
                    layout4.setOrientation(LinearLayout.HORIZONTAL);
                    name[num] = new TextView(this);
                    name[num].setText(vaccination_name[i]);
                    name[num].setTextSize(15);
                    name[num].setGravity(Gravity.CENTER);
                    name[num].setWidth(300);
                    name[num].setHeight(60);
                    layout4.addView(name[num]);
                    CheckBox checkBox3[] = new CheckBox[5];
                    for (j = 0; j < 3; j++) {
                        checkBox3[j] = new CheckBox(this);
                        checkBox3[j].setHeight(60);
                        checkBox3[j].setText((j + 1) + "차");
                        layout4.addView(checkBox3[j]);
                    }
                    outlayout.addView(layout4);
                    num++;
                    break;
                case "b형헤모필루스인플루엔자":
                    LinearLayout layout5 = new LinearLayout(this);
                    layout5.setOrientation(LinearLayout.HORIZONTAL);
                    name[num] = new TextView(this);
                    name[num].setText(vaccination_name[i]);
                    name[num].setTextSize(15);
                    name[num].setGravity(Gravity.CENTER);
                    name[num].setWidth(300);
                    name[num].setHeight(60);
                    layout5.addView(name[num]);
                    CheckBox checkBox5[] = new CheckBox[5];
                    for (j = 0; j < 3; j++) {
                        checkBox5[j] = new CheckBox(this);
                        checkBox5[j].setHeight(60);
                        checkBox5[j].setText((j + 1) + "차");
                        layout5.addView(checkBox5[j]);
                    }
                    outlayout.addView(layout5);
                    num++;
                    break;
                case "폐렴구균":
                    LinearLayout layout6 = new LinearLayout(this);
                    layout6.setOrientation(LinearLayout.HORIZONTAL);
                    name[num] = new TextView(this);
                    name[num].setText(vaccination_name[i]);
                    name[num].setTextSize(15);
                    name[num].setGravity(Gravity.CENTER);
                    name[num].setWidth(300);
                    name[num].setHeight(60);
                    layout6.addView(name[num]);
                    CheckBox checkBox6[] = new CheckBox[5];
                    for (j = 0; j < 3; j++) {
                        checkBox6[j] = new CheckBox(this);
                        checkBox6[j].setHeight(60);
                        checkBox6[j].setText((j + 1) + "차");
                        layout6.addView(checkBox6[j]);
                    }
                    outlayout.addView(layout6);
                    num++;
                    break;
                case "홍역/유행성이하선염/풍진":
                    LinearLayout layout7 = new LinearLayout(this);
                    layout7.setOrientation(LinearLayout.HORIZONTAL);
                    name[num] = new TextView(this);
                    name[num].setText(vaccination_name[i]);
                    name[num].setTextSize(15);
                    name[num].setGravity(Gravity.CENTER);
                    name[num].setWidth(300);
                    name[num].setHeight(60);
                    layout7.addView(name[num]);
                    CheckBox checkBox7[] = new CheckBox[5];
                    for (j = 0; j < 3; j++) {
                        checkBox7[j] = new CheckBox(this);
                        checkBox7[j].setHeight(60);
                        checkBox7[j].setText((j + 1) + "차");
                        layout7.addView(checkBox7[j]);
                    }
                    outlayout.addView(layout7);
                    num++;
                    break;
                case "수두":
                    LinearLayout layout8 = new LinearLayout(this);
                    layout8.setOrientation(LinearLayout.HORIZONTAL);
                    name[num] = new TextView(this);
                    name[num].setText(vaccination_name[i]);
                    name[num].setTextSize(15);
                    name[num].setGravity(Gravity.CENTER);
                    name[num].setWidth(300);
                    name[num].setHeight(60);
                    layout8.addView(name[num]);
                    CheckBox checkBox8[] = new CheckBox[5];
                    for (j = 0; j < 3; j++) {
                        checkBox8[j] = new CheckBox(this);
                        checkBox8[j].setHeight(60);
                        checkBox8[j].setText((j + 1) + "차");
                        layout8.addView(checkBox8[j]);
                    }
                    outlayout.addView(layout8);
                    num++;
                    break;
                case "A형간염":
                    LinearLayout layout9 = new LinearLayout(this);
                    layout9.setOrientation(LinearLayout.HORIZONTAL);
                    name[num] = new TextView(this);
                    name[num].setText(vaccination_name[i]);
                    name[num].setTextSize(15);
                    name[num].setGravity(Gravity.CENTER);
                    name[num].setWidth(300);
                    name[num].setHeight(60);
                    layout9.addView(name[num]);
                    CheckBox checkBox9[] = new CheckBox[5];
                    for (j = 0; j < 3; j++) {
                        checkBox9[j] = new CheckBox(this);
                        checkBox9[j].setHeight(60);
                        checkBox9[j].setText((j + 1) + "차");
                        layout9.addView(checkBox9[j]);
                    }
                    outlayout.addView(layout9);
                    num++;
                    break;
                case "일본뇌염":
                    LinearLayout layout10 = new LinearLayout(this);
                    layout10.setOrientation(LinearLayout.HORIZONTAL);
                    name[num] = new TextView(this);
                    name[num].setText(vaccination_name[i]);
                    name[num].setTextSize(15);
                    name[num].setGravity(Gravity.CENTER);
                    name[num].setWidth(300);
                    name[num].setHeight(60);
                    layout10.addView(name[num]);
                    CheckBox checkBox10[] = new CheckBox[5];
                    for (j = 0; j < 3; j++) {
                        checkBox10[j] = new CheckBox(this);
                        checkBox10[j].setHeight(60);
                        checkBox10[j].setText((j + 1) + "차");
                        layout10.addView(checkBox10[j]);
                    }
                    outlayout.addView(layout10);
                    num++;
                    break;
                case "사람유두종바이러스감염증":
                    LinearLayout layout11 = new LinearLayout(this);
                    layout11.setOrientation(LinearLayout.HORIZONTAL);
                    name[num] = new TextView(this);
                    name[num].setText(vaccination_name[i]);
                    name[num].setTextSize(15);
                    name[num].setGravity(Gravity.CENTER);
                    name[num].setWidth(300);
                    name[num].setHeight(60);
                    layout11.addView(name[num]);
                    CheckBox checkBox11[] = new CheckBox[5];
                    for (j = 0; j < 3; j++) {
                        checkBox11[j] = new CheckBox(this);
                        checkBox11[j].setHeight(60);
                        checkBox11[j].setText((j + 1) + "차");
                        layout11.addView(checkBox11[j]);
                    }
                    outlayout.addView(layout11);
                    num++;
                    break;
                case "인플루엔자":
                    LinearLayout layout12 = new LinearLayout(this);
                    layout12.setOrientation(LinearLayout.HORIZONTAL);
                    name[num] = new TextView(this);
                    name[num].setText(vaccination_name[i]);
                    name[num].setTextSize(15);
                    name[num].setGravity(Gravity.CENTER);
                    name[num].setWidth(300);
                    name[num].setHeight(60);
                    layout12.addView(name[num]);
                    CheckBox checkBox12[] = new CheckBox[5];
                    for (j = 0; j < 3; j++) {
                        checkBox12[j] = new CheckBox(this);
                        checkBox12[j].setHeight(60);
                        checkBox12[j].setText((j + 1) + "차");
                        layout12.addView(checkBox12[j]);
                    }
                    outlayout.addView(layout12);
                    num++;
                    break;
            }
        }
    }
}