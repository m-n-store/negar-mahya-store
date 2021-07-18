package com.example.store;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import DataBase.Mahsoolat;

public class ShowProductToAdmin extends AppCompatActivity {
    RecyclerView recyclerView;
    RecViewAdapter_sellerOne recViewAdapter_sellerOne;
    List<Product_List_One> listOnes = new ArrayList<>();
    int counter;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product_admin);
        recyclerView = findViewById(R.id.admin_recycler);
        recViewAdapter_sellerOne = new RecViewAdapter_sellerOne(this, listOnes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recViewAdapter_sellerOne);
        textView = findViewById(R.id.item_counts);
        setData();
    }

    private void setData() {
        Mahsoolat mahsoolat = new Mahsoolat(this);
        Cursor res2 = mahsoolat.ShowallData();
        counter = 0;
        while (res2.moveToNext()) {
            listOnes.add(new Product_List_One(res2.getString(3), res2.getString(4) , res2.getInt(0)));
            counter++;
        }
        textView.setText("Count: " + String.valueOf(counter));
        recViewAdapter_sellerOne.notifyDataSetChanged();
    }
}