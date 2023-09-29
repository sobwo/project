package com.example.test5;

import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    private ArrayList<Data> babyArray = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.babyitem, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(babyArray.get(position));
    }

    @Override
    public int getItemCount() {
        return babyArray.size();
    }

    void addItem(Data data) {
        babyArray.add(data);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textName;
        private TextView textGender;
        private TextView textBirth;

        ItemViewHolder(View itemview) {
            super(itemview);

            textName = itemview.findViewById(R.id.textName);
            textGender = itemview.findViewById(R.id.textGender);
            textBirth = itemview.findViewById(R.id.textBirth);
        }

        void onBind(Data data) {
            textName.setText(data.getName());
            textGender.setText(data.getGender());
            textBirth.setText(data.getBirthdate());

        }
    }
}