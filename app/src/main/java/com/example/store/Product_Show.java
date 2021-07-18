package com.example.store;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.File;

import DataBase.Mahsoolat;

public class Product_Show extends AppCompatActivity {
    public Mahsoolat mahsoolat;

    ImageView imageView;
    TextView textView_name_kala, textView_price, textView_grouh, textView_username,
            textView_mobile_number;
    String betweenMobilenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);


        imageView = findViewById(R.id.image_view_show_kala);
        textView_name_kala = findViewById(R.id.textview_name_kala_show);
        textView_price = findViewById(R.id.textview_price_show);
        textView_grouh = findViewById(R.id.textview_groh_show);
        textView_username = findViewById(R.id.textview_username_show);
        textView_mobile_number = findViewById(R.id.textview_mobilenumber_show);


        Bundle extras = getIntent().getExtras();
        int position = extras.getInt("key_positionrecycler");
        mahsoolat = new Mahsoolat(this);
        Cursor res2 = mahsoolat.ShowallData();


        while (res2.moveToNext()) {
            if (res2.getInt(0) == position) {

                String imagePath = Environment.getExternalStorageDirectory() + "/shoping/" + res2.getString(2);
                Picasso.with(getApplicationContext())
                        .load(new File(imagePath))
                        .into(imageView);
                textView_name_kala.setText(res2.getString(3));
                textView_price.setText(res2.getString(4) + " Toman");
                textView_grouh.setText(res2.getString(5));
                textView_username.setText(res2.getString(1));
                textView_mobile_number.setText(res2.getString(6));
                betweenMobilenumber = res2.getString(6);
                break;
            }


        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), CustomerPage.class));
    }

    public void oncli(View view_) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + betweenMobilenumber));
        startActivity(intent);
    }
}