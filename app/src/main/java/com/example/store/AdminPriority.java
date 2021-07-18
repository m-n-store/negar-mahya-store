package com.example.store;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import DataBase.Mahsoolat;

public class AdminPriority extends AppCompatActivity {
    RecyclerView recyclerView;
    RecViewAdapter_sellerOne recViewAdapter_sellerOne;
    List<Product_List_One> listOnes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminpriority);
        recyclerView = findViewById(R.id.product_priority);
        recViewAdapter_sellerOne = new RecViewAdapter_sellerOne(this, listOnes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recViewAdapter_sellerOne);
        setData();
    }

    private void setData() {
        Mahsoolat mahsoolat = new Mahsoolat(this);
        Cursor res2 = mahsoolat.ShowallData();
        while (res2.moveToNext()) {
            listOnes.add(new Product_List_One(res2.getString(3), res2.getString(4) , res2.getInt(0)));
        }
        recViewAdapter_sellerOne.notifyDataSetChanged();
    }
}
