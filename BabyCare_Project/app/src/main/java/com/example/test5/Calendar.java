package com.example.test5;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendar extends AppCompatActivity {
    private Context context;
    ReturnDate returnDate;
    DatePickerDialog datePickerDialog;
    //datePicker
    int year, month,day;
    String y,m,d;
    String birth;

    public Calendar(Context context) {
        this.context = context;
    }

    public void calender() {
        returnDate = new ReturnDate();
        //날짜 입력 받기
        //현재 날짜 입력
        year = Integer.parseInt(returnDate.returnYear());
        month = Integer.parseInt(returnDate.returnMonth());
        day = Integer.parseInt(returnDate.returnDay());
        //입력 끝
        datePickerDialog = new DatePickerDialog(context, listener, year, month, day);
        datePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            y = Integer.toString(year);
            m = Integer.toString(month);
            d = Integer.toString(dayOfMonth);
            birth = y+m+d;
            Singleton.getInstance().setBirthdate(birth);
        }
    };

        public class ReturnDate {
            //특성
            long now;
            Date date;
            SimpleDateFormat sdf;

        ReturnDate() {
            now = System.currentTimeMillis();
            date = new Date(now);
        }

        public String returnYear() {
            sdf = new SimpleDateFormat("yyyy");
            String year = sdf.format(date);
            return year;
        }

        public String returnMonth() {
            sdf = new SimpleDateFormat("MM");
            String month = sdf.format(date);
            return month;
        }

        public String returnDay() {
            sdf = new SimpleDateFormat("dd");
            String day = sdf.format(date);
            return day;
        }
    }
}
