package com.example.store;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import DataBase.Mahsoolat;

public class SellerPage extends AppCompatActivity {
    public Mahsoolat mahsoolat;
    String userName;
    RecyclerView recyclerView;
    RecViewAdapter_sellerOne recViewAdapter_sellerOne;
    List<Product_List_One> listOnes = new ArrayList<>();
    Button add_products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_page);


        Bundle extras = getIntent().getExtras();
        if (extras.getString("key_username") != null) {
            userName = extras.getString("key_username");
        }

        add_products = findViewById(R.id.add_products);
        add_products.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Add_Products.class);
            intent.putExtra("key_username", userName);
            startActivity(intent);
        });


        recyclerView = findViewById(R.id.recyclerviewsellerOne);
        recViewAdapter_sellerOne = new RecViewAdapter_sellerOne(this, listOnes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recViewAdapter_sellerOne);
        setData();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void setData() {
        mahsoolat = new Mahsoolat(this);
        Cursor res2 = mahsoolat.ShowallData();
        while (res2.moveToNext()) {
            if (res2.getString(1).equals(userName)) {
                listOnes.add(new Product_List_One(res2.getString(3), res2.getString(4) , res2.getInt(0)));
            }
        }
        recViewAdapter_sellerOne.notifyDataSetChanged();
    }
}