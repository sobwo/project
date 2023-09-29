package com.example.test5;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;


public class alert extends Service {
    Handler handler;
    int[] birtdate = new int[10];
    int num;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("BoundService", "onBind()");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @SuppressLint("HandlerLeak")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("서비스 시작");

        birtdate = Singleton.getInstance().getBirthdate();
        num = Singleton.getInstance().getNum();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                //
            }
        };
        class NewRunnable implements Runnable {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0);
                }
            }
        }
        NewRunnable nr_sub = new NewRunnable();
        Thread t_sub = new Thread(nr_sub);
        t_sub.start();

        return super.onStartCommand(intent, flags, startId);
    }

    public void alert_notify(String str) {
        int j=5;
        final NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        for (int i = 0; i <= j; i++) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(android.R.drawable.ic_dialog_alert)
                    .setContentTitle(str)
                    .setContentText(str);
            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent pending = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pending);
            builder.setAutoCancel(true);
            Notification notification = builder.build();
            manager.notify((int) (System.currentTimeMillis() / 1000), notification);
        }
    }

    private void date_sort(int date){
        // sort source code
    }

    private void birth_calculate(String date){
        Date date_now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        String tmp_date_now = dateFormat.format(date_now);

        date = Integer.toString(Integer.parseInt(tmp_date_now) - Integer.parseInt(date)); // 최적화 필요
        Singleton.getInstance().setBirthdate(date);
    }

    //toast 메시지 출력
    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}

