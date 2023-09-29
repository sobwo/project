package com.example.kakao_sound;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

public class CustomDialog extends AppCompatActivity {
    private Context context;

    public CustomDialog(Context context) {
        this.context = context;
    }

    public void callFunction() {
        final Dialog dlg = new Dialog(context);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setContentView(R.layout.input);
        dlg.show();

        final EditText food_message = dlg.findViewById(R.id.food_message);
        final EditText date_message = dlg.findViewById(R.id.date_mesgase);
        final EditText count_message = dlg.findViewById(R.id.count_mesgase);
        final Button okButton = dlg.findViewById(R.id.okButton);
        final Button cancelButton = dlg.findViewById(R.id.cancelButton);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "\"재료 :" + food_message.getText().toString() + "\n개수 :"
                        + count_message.getText().toString() + "\n유통기한 :"
                        + date_message.getText().toString() + "을 입력하였습니다.", Toast.LENGTH_SHORT).show();
                if (food_message.getText().toString() != null && count_message.getText().toString() != null && date_message.getText().toString() != null) {
                    Singleton.getInstance().setFood_txt(food_message.getText().toString());
                    Singleton.getInstance().setDate_txt(date_message.getText().toString());
                    Singleton.getInstance().setCount_txt(count_message.getText().toString());
                }
                Intent i = new Intent(context, alert_service.class);
                context.startService(i);
                dlg.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "취소 했습니다.", Toast.LENGTH_SHORT).show();
                dlg.dismiss();
            }
        });
    }
}