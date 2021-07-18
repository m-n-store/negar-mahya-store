package com.example.store;

import android.app.AppComponentFactory;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import DataBase.PersonDB;
import object.Person;

public class ShowSellerToAdmin extends AppCompatActivity {
    RecyclerView recyclerView;
    RecViewAdapter_sellershow recViewAdapter_sellershow;
    List<Seller_List> listOnes = new ArrayList<>();
    int counter;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_seller_admin);
        recyclerView = findViewById(R.id.admin_recycler2);
        recViewAdapter_sellershow = new RecViewAdapter_sellershow(this, listOnes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recViewAdapter_sellershow);
        textView = findViewById(R.id.item_counts);
        setData();
    }

    private void setData() {
        PersonDB person = new PersonDB(this);
        Cursor res2 = person.ShowallData();
        counter = 0;
        while (res2.moveToNext()) {
            if(res2.getString(3).equals("seller")) {
                listOnes.add(new Seller_List(res2.getString(1)));
                counter++;
            }
        }
        textView.setText("Count: " + String.valueOf(counter));
        recViewAdapter_sellershow.notifyDataSetChanged();
    }
}
