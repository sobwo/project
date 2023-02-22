package com.example.kakao_sound;

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
import java.util.StringTokenizer;


public class alert_service extends Service {
    Handler handler;
    String[] food_alert = new String[30];
    String[] date_alert = new String[30];
    int[] count_alert = new int[30];
    int[] timeDiff_alert = new int[30];
    int food_date_num_alert = 0;
    int food_date_num_alert_2 = 0;

    private int j = 0;

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
        food_alert = Singleton.getInstance().getFood();
        date_alert = Singleton.getInstance().getDate();
        food_date_num_alert = Singleton.getInstance().getFood_date();
        timeDiff_alert = Singleton.getInstance().getTime();
        count_alert = Singleton.getInstance().getCount();

        System.out.println("서비스 시작");

        String elements = intent.getStringExtra("elements");
        if (elements != null)
            getString(elements);

        time_get(date_alert, food_date_num_alert);
        Singleton.getInstance().setFood(food_alert);
        Singleton.getInstance().setDate(date_alert);
        Singleton.getInstance().setTime(timeDiff_alert);
        Singleton.getInstance().setFood_date(food_date_num_alert);
        Singleton.getInstance().setCount(count_alert);

        food_alert = Singleton.getInstance().getFood();
        date_alert = Singleton.getInstance().getDate();
        food_date_num_alert = Singleton.getInstance().getFood_date();
        timeDiff_alert = Singleton.getInstance().getTime();

        for (int i = 0; i < food_date_num_alert; i++) {
            System.out.println(i + "번째 음식 : " + food_alert[i]);
            System.out.println(i + "번째 음식 개수 : " + count_alert[i]);
            System.out.println(i + "번째 유통기한 : " + date_alert[i]);
        }
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Date date_now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
                food_alert = Singleton.getInstance().getFood();
                date_alert = Singleton.getInstance().getDate();
                food_date_num_alert = Singleton.getInstance().getFood_date();
                timeDiff_alert = Singleton.getInstance().getTime();

                String temp_now = dateFormat.format(date_now);

                restdate();
                time_get(date_alert, food_date_num_alert);

                Singleton.getInstance().setFood(food_alert);
                Singleton.getInstance().setDate(date_alert);
                Singleton.getInstance().setTime(timeDiff_alert);
                Singleton.getInstance().setCount(count_alert);
                Singleton.getInstance().setFood_date(food_date_num_alert);

                food_alert = Singleton.getInstance().getFood();
                date_alert = Singleton.getInstance().getDate();
                food_date_num_alert = Singleton.getInstance().getFood_date();
                timeDiff_alert = Singleton.getInstance().getTime();

                for (int i = 0; i < food_date_num_alert; i++)
                    if ((timeDiff_alert[i] < 7) && (food_alert[i].length() > 0)) {
                        alert_notify(food_alert);
                        j = i;
                    }
                if (Singleton.getInstance().getFood() == null) {
                    stopSelf();
                    System.out.println("서비스 종료");
                }
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

    // 음성인식 텍스트로 변환 후 필요한 부분 추출

    private void getString(String elements) {
        StringBuilder tst = new StringBuilder();
        StringBuilder[] temp = new StringBuilder[100];
        StringBuilder[] food_tmp = new StringBuilder[100];
        StringBuilder[] date_tmp = new StringBuilder[100];
        StringBuilder[] count_tmp = new StringBuilder[100];

        for (int i = 0; i < 100; i++) {
            temp[i] = new StringBuilder();
            food_tmp[i] = new StringBuilder();
            date_tmp[i] = new StringBuilder();
            count_tmp[i] = new StringBuilder();
        }

        String temp_str;
        String temp_str_2;
        String date_temp;
        int num = 1;
        int j;
        int idx, idx_date, idx_date_1, idx_date_2;

        temp[0].delete(0, temp[0].length());
        temp[0].append(elements);

        temp_str = temp[0].toString();
        for (int i = 0; i < temp_str.length(); i++) {
            idx = temp_str.indexOf("일");
            temp[num].insert(0, temp_str.substring(0, idx + 1));
            num++;
            if (idx == temp_str.length() - 1)
                j = 1;
            else
                j = 2;
            temp_str = temp_str.substring(idx + j);
        }

        for (int i = 1; i < num; i++) {
            tst.append(temp[i] + "\n");
        }
        toast(tst.toString());

        for (int i = 1; i < num; i++) {
            if (temp[i].toString().contains("년") && temp[i].toString().contains("월") && temp[i].toString().contains("일")) {
                temp_str_2 = temp[i].toString();
                StringTokenizer st_2 = new StringTokenizer(temp_str_2);
                food_tmp[food_date_num_alert].append(st_2.nextToken());
                count_tmp[food_date_num_alert].append(st_2.nextToken());
                date_tmp[food_date_num_alert].append(st_2.nextToken());
                date_tmp[food_date_num_alert].append(st_2.nextToken());
                date_tmp[food_date_num_alert].append(st_2.nextToken());
                food_alert[food_date_num_alert] = food_tmp[food_date_num_alert].toString();
                if (count_tmp[food_date_num_alert].toString().length() == 3)
                    count_alert[food_date_num_alert] = Integer.parseInt(count_tmp[food_date_num_alert].toString().substring(0, 2));
                else if (count_tmp[food_date_num_alert].toString().length() == 2)
                    count_alert[food_date_num_alert] = Integer.parseInt(count_tmp[food_date_num_alert].toString().substring(0, 1));
                else
                    count_alert[food_date_num_alert] = Integer.parseInt(count_tmp[food_date_num_alert].toString().substring(0, 4));
                food_date_num_alert++;
            } else {
                food_date_num_alert = 0;
                return;
            }
        }

        for (int i = 0; i < food_date_num_alert; i++) {
            date_temp = date_tmp[i].toString();
            if ((date_temp.contains("년")) && (date_temp.contains("월")) && date_temp.contains("일")) {
                date_tmp[i].delete(0, date_tmp[i].length());
                idx_date = date_temp.indexOf("년");
                date_tmp[i].append(date_temp.substring(0, idx_date));
                idx_date_1 = date_temp.indexOf("월");
                date_tmp[i].append(date_temp.substring(idx_date + 1, idx_date_1));
                idx_date_2 = date_temp.indexOf("일");
                date_tmp[i].append(date_temp.substring(idx_date_1 + 1, idx_date_2));
                if (date_tmp[i].length() == 7)
                    date_tmp[i].insert(4, "0");
                else if (date_tmp[i].length() == 6) {
                    date_tmp[i].insert(4, "0");
                    date_tmp[i].insert(6, "0");
                }
                date_alert[food_date_num_alert_2] = date_tmp[i].toString();
                food_date_num_alert_2++;
            }
        }
    }

    public void alert_notify(String[] food_str) {
        String str = food_str[j] + "의 유통기한이" + timeDiff_alert[j] + "남았습니다.";
        String str_1 = "남은 유통기한 : " + timeDiff_alert[j];
        final NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        for (int i = 0; i <= j; i++) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(android.R.drawable.ic_dialog_alert)
                    .setContentTitle(str)
                    .setContentText(str_1);
            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent pending = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pending);
            builder.setAutoCancel(true);
            Notification notification = builder.build();
            manager.notify((int) (System.currentTimeMillis() / 1000), notification);
        }
    }

    //입력받은 유통기한 날짜와 현재 날짜 비교
    private void time_get(String[] str1, int num) {
        Date date_now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String tmp_date_now = dateFormat.format(date_now);

        int str_1, str_2, str_3, str_t1, str_t2, str_t3;
        for (int i = 0; i < num; i++) {
            if (Integer.parseInt(str1[i]) > Integer.parseInt(tmp_date_now)) {
                str_1 = Integer.parseInt(str1[i].substring(0, 4));
                str_2 = Integer.parseInt(str1[i].substring(4, 6));
                str_3 = Integer.parseInt(str1[i].substring(6, 8));
                str_t1 = Integer.parseInt(tmp_date_now.substring(0, 4));
                str_t2 = Integer.parseInt(tmp_date_now.substring(4, 6));
                str_t3 = Integer.parseInt(tmp_date_now.substring(6, 8));
                str_1 = str_1 * 365;
                str_2 = str_2 * 30;
                str_t1 = str_t1 * 365;
                str_t2 = str_t2 * 30;
                timeDiff_alert[i] = (str_1 + str_2 + str_3) - (str_t1 + str_t2 + str_t3);
            } else
                toast("유통기한이 지난 물품이 있습니다.");
        }
        dateSort(timeDiff_alert);
    }

    private void dateSort(int[] timeD) {
        int temp;
        String temp_str;
        for (int i = 0; i < food_date_num_alert - 1; i++) {
            for (int j = i + 1; j < food_date_num_alert; j++) {
                if (timeD[i] > timeD[j]) {
                    temp = timeD[j];
                    timeD[j] = timeD[i];
                    timeD[i] = temp;
                    temp_str = food_alert[j];
                    food_alert[j] = food_alert[i];
                    food_alert[i] = temp_str;
                    temp_str = date_alert[j];
                    date_alert[j] = date_alert[i];
                    date_alert[i] = temp_str;
                    temp = count_alert[j];
                    count_alert[j] = count_alert[i];
                    count_alert[i] = temp;
                }
            }
        }
    }

    private void restdate() {
        for (int i = 0; i < food_date_num_alert; i++)
            if (timeDiff_alert[i] <= 0 && food_alert[i] != null) {
                String str = food_alert[i] + "의 유통기한이" + "0일 남았습니다.";
                String str_1 = "남은 유통기한 : " + timeDiff_alert[i];
                final NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_dialog_alert)
                        .setContentTitle(str)
                        .setContentText(str_1);
                Intent intent = new Intent(this, MainActivity.class);
                PendingIntent pending = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pending);
                builder.setAutoCancel(true);
                Notification notification = builder.build();
                manager.notify((int) (System.currentTimeMillis() / 1000), notification);

                food_alert[i] = "";
                date_alert[i] = "";
                timeDiff_alert[i] = 0;
                count_alert[i] = 0;

                food_alert[i] = food_alert[i + 1];
                date_alert[i] = date_alert[i + 1];
                timeDiff_alert[i] = timeDiff_alert[i + 1];
                count_alert[i] = count_alert[i + 1];

                food_date_num_alert--;
            }
            if(food_alert[food_date_num_alert]!=null) {
                food_alert[food_date_num_alert] = "";
                date_alert[food_date_num_alert] = "";
                count_alert[food_date_num_alert] = 0;
                timeDiff_alert[food_date_num_alert] = 0;
            }
    }

    //toast 메시지 출력
    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}

