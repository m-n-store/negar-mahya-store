package com.example.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

    public class AdminPage extends AppCompatActivity {
        Button adminshowproduct,adminshowseller,adminsumprice,adminshowloginnum,best_seller3,priority;

        @Override
        protected void onCreate( Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_page);

            adminshowproduct=findViewById(R.id.admin_show_product);
            adminshowproduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),ShowProductToAdmin.class));
                }
            });
            adminshowseller=findViewById(R.id.show_seller);
            adminshowseller.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),ShowSellerToAdmin.class));
                }
            });
            adminsumprice=findViewById(R.id.sum_price);
            adminsumprice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),SumPrice.class));
                }
            });
            adminshowloginnum=findViewById(R.id.login_num);
            adminshowloginnum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),LoginNumber.class));
                }
            });
            best_seller3=findViewById(R.id.finest_seller);
            best_seller3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),BestSeller.class));
                }
            });

            priority=findViewById(R.id.admin_priority);
            priority.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),AdminPriority.class));
                }
            });



        }
        public void onBackPressed() {
            super.onBackPressed();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

