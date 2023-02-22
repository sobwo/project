package com.example.test5;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

public class Dialog_care extends AppCompatActivity {
    private Context context;
    String vaccination_name = "";
    int vaccination_date = 0;

    public Dialog_care(Context context) {
        this.context = context;
    }

    public void callFunction() {
        final Dialog dlg = new Dialog(context);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setContentView(R.layout.dialog_care);
        dlg.show();
        TextView d_name = findViewById(R.id.name2);
        TextView d_date = findViewById(R.id.date2);
        TextView d_info = findViewById(R.id.info2);
        switch (vaccination_name) {
            case "결핵":
                d_name.setText(vaccination_name);
                d_date.setText(vaccination_date);
                d_info.setText("");
                break;
            case "B형 간염":
                d_name.setText(vaccination_name);
                d_date.setText(vaccination_date);
                d_info.setText("");
                break;
            case "디프테리아 / 파상풍 / 백일해":
                d_name.setText(vaccination_name);
                d_date.setText(vaccination_date);
                d_info.setText("");
                break;
            case "폴리오":
                d_name.setText(vaccination_name);
                d_date.setText(vaccination_date);
                d_info.setText("");
                break;
            case "B형헤모필루스인플루엔자":
                d_name.setText(vaccination_name);
                d_date.setText(vaccination_date);
                d_info.setText("");
                break;
            case "폐렴구균":
                d_name.setText(vaccination_name);
                d_date.setText(vaccination_date);
                d_info.setText("");
                break;
            case "홍역 / 유행성이하선염 / 풍진":
                d_name.setText(vaccination_name);
                d_date.setText(vaccination_date);
                d_info.setText("");
                break;
            case "수두":
                d_name.setText(vaccination_name);
                d_date.setText(vaccination_date);
                d_info.setText("");
                break;
            case "A형 간염":
                d_name.setText(vaccination_name);
                d_date.setText(vaccination_date);
                d_info.setText("");
                break;
            case "일본 뇌염":
                d_name.setText(vaccination_name);
                d_date.setText(vaccination_date);
                d_info.setText("");
                break;
            case "사람유두종바이러스 감염증":
                d_name.setText(vaccination_name);
                d_date.setText(vaccination_date);
                d_info.setText("");
                break;
            case "인플루엔자":
                d_name.setText(vaccination_name);
                d_date.setText(vaccination_date);
                d_info.setText("");
                break;
            case "로타바이러스 감염증":
                d_name.setText(vaccination_name);
                d_date.setText(vaccination_date);
                d_info.setText("");
                break;
        }
    }
}