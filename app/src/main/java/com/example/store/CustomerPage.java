package com.example.store;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import DataBase.Mahsoolat;

public class CustomerPage extends AppCompatActivity {

    public Mahsoolat mahsoolat;
    RecyclerView recyclerView;
    RecViewAdapter_customerPage recViewAdapter_customerPage;
    List<Product_list_rec_customerPage> list = new ArrayList<>();
    SearchView searchView;
    Button Btn_high_to_low, Btn_low_to_high;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);
        recyclerView = findViewById(R.id.recCustomerPage);
        Btn_low_to_high = findViewById(R.id.Btn_low_to_high);
        Btn_high_to_low = findViewById(R.id.Btn_high_to_low);

        Btn_low_to_high.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                list.sort(Comparator.comparing(Product_list_rec_customerPage::getPrice));
                recViewAdapter_customerPage.notifyDataSetChanged();
            }
        });

        Btn_high_to_low.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                list.sort(Comparator.comparing(Product_list_rec_customerPage::getPrice).reversed());
                recViewAdapter_customerPage.notifyDataSetChanged();
            }
        });

        recViewAdapter_customerPage = new RecViewAdapter_customerPage(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recViewAdapter_customerPage);
        setData();


        searchView = findViewById(R.id.searchview_);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                list.clear();
                mahsoolat = new Mahsoolat(getApplicationContext());
                Cursor res2 = mahsoolat.ShowallData();

                if (newText.equals("")) {
                    while (res2.moveToNext()) {
                        String filename = res2.getString(2);
                        String imagePath = Environment.getExternalStorageDirectory() + "/shoping/" + filename;
                        list.add(new Product_list_rec_customerPage(res2.getString(3), res2.getInt(4), imagePath, res2.getInt(0)));
                    }
                } else {
                    while (res2.moveToNext()) {
                        if (res2.getString(3).contains(newText)) {
                            String filename = res2.getString(2);
                            String imagePath = Environment.getExternalStorageDirectory() + "/shoping/" + filename;
                            list.add(new Product_list_rec_customerPage(res2.getString(3), res2.getInt(4), imagePath, res2.getInt(0)));
                        }
                    }
                }
                recViewAdapter_customerPage.notifyDataSetChanged();
                return false;
            }
        });
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
            if(res2.getInt(7)==1){
                String filename = res2.getString(2);
                String imagePath = Environment.getExternalStorageDirectory() + "/shoping/" + filename;
                list.add(new Product_list_rec_customerPage(res2.getString(3), res2.getInt(4), imagePath, res2.getInt(0)));
            }
        }
        Cursor res3=mahsoolat.ShowallData();
        while (res3.moveToNext()) {
            if(res3.getInt(7)==0){
                String filename = res3.getString(2);
                String imagePath = Environment.getExternalStorageDirectory() + "/shoping/" + filename;
               list.add(new Product_list_rec_customerPage(res3.getString(3), res3.getInt(4), imagePath, res3.getInt(0)));
            }
       }



        recViewAdapter_customerPage.notifyDataSetChanged();
    }
}