package com.example.kakao_sound;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity{
    //OnCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getHashKey();
        setContentView(R.layout.activity_main);
        ImageButton bt_add = findViewById(R.id.add);
        ImageButton bt_leftdoor = findViewById(R.id.leftdoor);
        ImageButton bt_rightdoor = findViewById(R.id.rightdoor);
        ImageButton bt_cart = findViewById(R.id.cart);
        ImageButton bt_showlist = findViewById(R.id.listup);

       bt_leftdoor.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, leftdoor_open.class);
               startActivity(intent);
               overridePendingTransition(0, 0);
           }
       });
       //오른쪽문
       bt_rightdoor.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, rightdoor_open.class);
               startActivity(intent);
//               Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query="+"고기"+"레시피"));
//               startActivity(intent);

               overridePendingTransition(0, 0);
           }
       });

       bt_cart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, Mycart.class);
               startActivity(intent);
           }
       });

       bt_showlist.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,show_list.class);
               startActivity(intent);
           }
       });

        // 대화상자 버튼 설정
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p = new PopupMenu(getApplicationContext(),v);
                getMenuInflater().inflate(R.menu.menu,p.getMenu());
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.m1:
                                CustomDialog customDialog = new CustomDialog(MainActivity.this);
                                customDialog.callFunction();
                                break;
                            case R.id.m2:
                                Intent intent = new Intent(getApplicationContext(),SubActivity.class);
                                startActivity(intent);
                                break;
                        }
                        return false;
                    }
                });
                p.show();
            }
        });
    }
    //Hash Key method
    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }
}

