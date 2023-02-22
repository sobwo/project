package com.example.test5;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment1 extends AppCompatActivity {
    private Context context;
    TextView birthButton;  //달력 입력
    Button ok_button;
    Button cancel_button;
    ImageView pictureImage;
    EditText nameEdit; // 이름 edit

    public Fragment1(Context context) {
        this.context = context;
    }

    public void fragment1_function() {
        final Dialog dlg = new Dialog(context);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setContentView(R.layout.fragment1);
        dlg.setTitle("아기정보");
        dlg.show();

        birthButton = dlg.findViewById(R.id.birthButton);
        nameEdit = dlg.findViewById(R.id.nameEdit);
        ok_button = dlg.findViewById(R.id.okButton);
        cancel_button = dlg.findViewById(R.id.cancelButton);
        pictureImage = dlg.findViewById(R.id.pictureimageView);

        // 확인,취소버튼
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayToast("이름을 입력하세요.");
                dlg.dismiss();
            }
        });
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "취소 했습니다.", Toast.LENGTH_SHORT).show();
                dlg.dismiss();
            }
        });
        birthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = new Calendar(context);
                calendar.calender();
                birthButton.setText(Singleton.getInstance().getBirthdate);
                showDialog(0);
            }
        });
        pictureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Photo.class);
                context.startActivity(intent);
            }
        });
    }

    private void displayToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
