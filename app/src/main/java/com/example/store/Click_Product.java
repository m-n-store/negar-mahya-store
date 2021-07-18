package com.example.store;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import DataBase.Mahsoolat;
import DataBase.PersonDB;

public class Click_Product extends AppCompatActivity {
    RecyclerView recyclerView;
    RecViewAdapter_sellerOne recViewAdapter_sellerOne;
    public static final int GET_FROM_GALLERY = 3;
    public Mahsoolat mahsoolat;
    public PersonDB personDB;
    ImageView imageView;
    EditText product_price, product_name;
    Button addimage, edit,delete;
    String userName, product_group, mobileNumber, nameImageSaved;
    RadioGroup radioGroup;
    RadioButton radioButton_pooshak, radioButton_digital, radioButton_mavadGhazaei;
    int id_product;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_product);

        imageView = findViewById(R.id.imageView55);
        addimage = findViewById(R.id.btnPicture);
        product_name = findViewById(R.id.product_name);
        product_price = findViewById(R.id.product_price);
        edit = findViewById(R.id.bntEdit);
        delete = findViewById(R.id.bntDelete);
        radioGroup = findViewById(R.id.radioGroup_product_group);
        radioButton_pooshak = findViewById(R.id.radioBtn_clothing);
        radioButton_digital = findViewById(R.id.radioBtn_digital);
        radioButton_mavadGhazaei = findViewById(R.id.radioBtn_food);


        Bundle extras2 = getIntent().getExtras();
        int position = extras2.getInt("key_positionrecycler");



        ActivityCompat.requestPermissions(Click_Product.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        if (radioButton_pooshak.isChecked())
            product_group = "Clothing";
        else if (radioButton_digital.isChecked())
            product_group = "Digital";
        else
            product_group = "Foodstuffs";
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButton_pooshak.isChecked())
                    product_group = "Clothing";
                else if (radioButton_digital.isChecked())
                    product_group = "Digital";
                else
                    product_group = "Foodstuffs";

            }
        });
        personDB = new PersonDB(this);
        mahsoolat = new Mahsoolat(this);
       //Bundle extras = getIntent().getExtras();
        //userName = extras.getString("key_username");
        Cursor res2 = mahsoolat.ShowallData();
        while (res2.moveToNext()) {
            if (res2.getInt(0)==position) {
                mobileNumber = res2.getString(6);
                userName=res2.getString(1);
                id_product=res2.getInt(0);
                break;
            }
        }
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mahsoolat.updateData(Integer.toString(id_product),userName, nameImageSaved, product_name.getText().toString().trim(), Integer.parseInt(product_price.getText().toString()), product_group, mobileNumber,0);
              recViewAdapter_sellerOne.notifyItemChanged(position);

              recViewAdapter_sellerOne.notifyDataSetChanged();

                   Intent intent = new Intent(getApplicationContext(), SellerPage.class);
                  intent.putExtra("key_username", userName);
                  startActivity(intent);
                }
    });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mahsoolat.deleteData(Integer.toString(position));
                recViewAdapter_sellerOne.notifyDataSetChanged();

             ///   Intent intent=new Intent(getApplicationContext(),SellerPage.class);
            ///    intent.putExtra("key_username",userName);
            //    startActivity(intent);
            }
        });

        // Toast.makeText(getApplicationContext(), userName, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Detects request codes
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmap);
            Bitmap image = bitmap;
            File path = Environment.getExternalStorageDirectory();
            File dir = new File(path + "/shoping/");
            dir.mkdirs();
            nameImageSaved = System.currentTimeMillis() + "shop.png";
            File file = new File(dir, nameImageSaved);
            OutputStream out = null;
            try {
                out = new FileOutputStream(file);
                image.compress(Bitmap.CompressFormat.PNG, 100, out);
                try {
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }
}