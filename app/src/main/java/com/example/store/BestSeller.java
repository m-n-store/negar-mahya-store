package com.example.store;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import DataBase.Mahsoolat;
import DataBase.PersonDB;

public class BestSeller extends AppCompatActivity {
    public Mahsoolat products;
    public PersonDB person;
    TextView x;


    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_seller);
        x = findViewById(R.id.best_seller1);
        List<String> usernames = new ArrayList<>();
        person = new PersonDB(this);
        Cursor res2 = person.ShowallData();
        while (res2.moveToNext()) {
            if (res2.getString(3).equals("seller")) {
                usernames.add(res2.getString(1));
            }
        }
        List<String> username_pro = new ArrayList<>();
        products = new Mahsoolat(this);
        Cursor a = products.ShowallData();
        while (a.moveToNext()) {
            if(a.getString(3)!=""){
                username_pro.add(a.getString(1));
            }}
        List<Integer> count = new ArrayList<>();
        for(int i=0;i<usernames.size();i++){
            count.add(Collections.frequency(username_pro, usernames.get(i)));
        }
        Integer max = Collections.max(count);

        x.setText(String.valueOf( "best seller is : "+ usernames.get(count.indexOf(max))));

    }

}