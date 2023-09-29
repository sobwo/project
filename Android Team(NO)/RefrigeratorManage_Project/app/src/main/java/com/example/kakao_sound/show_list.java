package com.example.kakao_sound;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class show_list extends AppCompatActivity {
    private String[] food_name = new String[30];
    private String[] food_date = new String[30];
    private int[] food_count = new int[30];
    String[] full = new String[30];
    public ArrayList<Fooddata> foodlist = new ArrayList<Fooddata>();
    int food_date_num;
    ListView listView;
    ArrayAdapter arrayAdapter;
    adapter foodadapter;
    Context c;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_showlist);

        if ((Singleton.getInstance().getFood()[0] != null) && (Singleton.getInstance().getDate()[0] != null)) {
            food_name = Singleton.getInstance().getFood();
            food_date = Singleton.getInstance().getDate();
            food_count = Singleton.getInstance().getCount();
            food_date_num = Singleton.getInstance().getFood_date();
        }
        for (int i = 0; i < food_date_num; i++) {
            foodlist.add(new Fooddata(food_name[i], food_count[i], food_date[i], R.drawable.youtube));
        }
        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, foodlist);
        foodadapter = new adapter(getLayoutInflater(), foodlist);
        listView = (ListView) findViewById(R.id.foodname);
        listView.setAdapter(foodadapter);
    }

    public class Fooddata {
        String foodname;
        String fooddate;
        int foodcount;
        int imgId;

        public Fooddata(String name, int count, String date, int imgId) {
            this.foodname = name;
            this.foodcount = count;
            this.fooddate = date;
            this.imgId = imgId;
        }

        public void setName(String name) {
            this.foodname = name;
        }

        public void setCount(int count) {
            this.foodcount = count;
        }

        public void setDate(String date) {
            this.fooddate = date;
        }

        public void setImgId(int imgId) {
            this.imgId = imgId;
        }

        public String getName() {
            return foodname;
        }

        public int getCount() {
            return foodcount;
        }

        public String getDate() {
            return fooddate;
        }

        public int getImgId() {
            return imgId;
        }
    }

    public class adapter extends BaseAdapter {
        ArrayList<Fooddata> datas;
        LayoutInflater inflater;

        public adapter(LayoutInflater inflater, ArrayList<Fooddata> datas) {
            this.datas = datas;
            this.inflater = inflater;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) convertView = inflater.inflate(R.layout.list_row, null);

            TextView text_name = (TextView) convertView.findViewById(R.id.text_name);
            TextView text_count = (TextView) convertView.findViewById(R.id.text_count);
            TextView text_date = (TextView) convertView.findViewById(R.id.text_date);
            ImageView img_flag = (ImageView) convertView.findViewById(R.id.img_flag);

            text_name.setText(datas.get(position).getName());
            text_count.setText(Integer.toString(datas.get(position).getCount()));
            text_date.setText(datas.get(position).getDate());
            img_flag.setImageResource(datas.get(position).getImgId());

            text_count.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    alter(position);
                }
            });

            img_flag.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//                    listView.setOnItemClickListener( new ListViewItemClickListener2() );
                    linkyoutube(position);
                }
            });

            return convertView;
        }
    }

    void alter(int pos) {
        food_date_num = pos;
        AlertDialog.Builder choose = new AlertDialog.Builder(show_list.this);
        choose.setTitle("수정/삭제 선택");
        choose.setNegativeButton("수정", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "수정버튼 클릭됨", Toast.LENGTH_LONG).show();
                show(food_date_num);
            }
        });

        choose.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "삭제버튼 클릭됨", Toast.LENGTH_LONG).show();
                foodlist.remove(food_date_num);
                foodadapter.notifyDataSetChanged();
                Singleton.getInstance().setCount_alter2(0, food_date_num);
                Singleton.getInstance().setFood_alter("", food_date_num);
                Singleton.getInstance().setDate_alter("", food_date_num);
                Singleton.getInstance().setTime_alter(0, food_date_num);
            }
        });
        choose.show();
    }

    void linkyoutube(int num) {
        food_date_num = num;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=" + food_name[num] + "레시피"));
        startActivity(intent);
    }

    void show(int position) {
        food_date_num = position;
        final EditText edittext = new EditText(this);
        AlertDialog.Builder alter = new AlertDialog.Builder(this);
        alter.setTitle("수량 변경");
        alter.setMessage("변경할 수량을 입력하세요");
        alter.setView(edittext);
        alter.setPositiveButton("입력", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String edit;
                Toast.makeText(getApplicationContext(), edittext.getText().toString(), Toast.LENGTH_LONG).show();
                foodlist.set(food_date_num, new Fooddata(food_name[food_date_num], Integer.parseInt(edittext.getText().toString()), food_date[food_date_num], R.drawable.youtube));
                foodadapter.notifyDataSetChanged();
                Singleton.getInstance().setCount_alter(edittext.getText().toString(), food_date_num);
            }
        });

        alter.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alter.show();
    }
}