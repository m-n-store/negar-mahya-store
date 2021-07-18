package com.example.store;

import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import DataBase.Counter;

public class LoginNumber extends AppCompatActivity {
    TextView seller_counter,costumer_counter,admin_counter;
   public Counter counter;
     int numbers_costumer=0,numbers_seller=0,numbers_admin =0;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginnumber);
        seller_counter=findViewById(R.id.sellerloginnumbers);
        costumer_counter=findViewById(R.id.customerloginnumber);
        admin_counter=findViewById(R.id.adminloginnumber);
        counter=new Counter(this);
        Cursor res = counter.ShowAllData();
        while (res.moveToNext()) {
            if(res.getInt(1)==1){
                numbers_costumer++;
;            }
            if(res.getInt(2)==1){
                numbers_seller++;
            }
            if(res.getInt(3)==1){
                numbers_admin++;

            }
        }
        seller_counter.setText("seller login number :"+String.valueOf(numbers_seller));
        costumer_counter.setText("costumer login number :"+String.valueOf(numbers_costumer));
        admin_counter.setText("admin login number :"+String.valueOf(numbers_admin));

    }
}
