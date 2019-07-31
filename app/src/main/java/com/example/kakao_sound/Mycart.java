package com.example.kakao_sound;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


//카트 화면 구성
public class Mycart extends AppCompatActivity {
    String[] chooseitem = new String[30];
    String[] items = new String[30];
    int i;
    ListView listView;
    Button delete;
    adapter cart_adapter = new adapter();
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);
        ImageButton add_cart = findViewById(R.id.add_cart);
        listView = findViewById(R.id.cart_list);
        delete = findViewById(R.id.delete_button);
        count = Singleton.getInstance().getFood_date();
        listView.setAdapter(cart_adapter);

        i = 0;
        for (int i = 0; i < count; i++)
            items[i] = Singleton.getInstance().getFood()[i];

        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseitem[i] = "";
                Singleton.getInstance().setfood_dialog(chooseitem[i]);
                if (i != 0) i--;
                Singleton.getInstance().setNum_dialog(i);
                cart_adapter.notifyDataSetChanged();
            }
        });

    }// end onCreate()

    @Override
    public void onStart() {
        super.onStart();
    }

    public void alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("장바구니 선택");
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(Mycart.this, android.R.layout.select_dialog_singlechoice);
        for (int i = 0; i < count; i++)
            adapter.add(items[i]);
        builder.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int pos) {
                add(pos);
                cart_adapter.notifyDataSetChanged();
            }
        });
        builder.show();
    }

    public void add(int pos) {
        i = Singleton.getInstance().getNum_dialog();
        chooseitem[i] = items[pos];
        Toast.makeText(Mycart.this, chooseitem[i] + "를 선택하였습니다.", Toast.LENGTH_SHORT).show();
        Singleton.getInstance().setfood_dialog(chooseitem[i]);
        chooseitem = Singleton.getInstance().getFood_dialog();
        i++;
        Singleton.getInstance().setNum_dialog(i);
    }

    class adapter extends BaseAdapter {
        @Override
        public int getCount() {
            return chooseitem.length;
        }

        @Override
        public Object getItem(int j) {
            return chooseitem[j];
        }

        @Override
        public long getItemId(int j) {
            return j;
        }

        @Override
        public View getView(int j, View view,  ViewGroup viewGroup) {
            TextView view_list = new TextView(Mycart.this);
            view_list.setText(chooseitem[j]);
            view_list.setTextSize(30.0f);
            view_list.setTextColor(Color.BLACK);
            return view_list;
        }
    }
}

