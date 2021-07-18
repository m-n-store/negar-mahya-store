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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import DataBase.Mahsoolat;
import DataBase.PersonDB;

public class Add_Products extends AppCompatActivity {

    public static final int GET_FROM_GALLERY = 3;
    public Mahsoolat mahsoolat;
    public PersonDB personDB;
    ImageView imageView;
    EditText product_price, product_name;
    Button addimage, ok;
    String userName, product_group, mobileNumber, nameImageSaved;
    RadioGroup radioGroup;
    RadioButton radioButton_pooshak, radioButton_digital, radioButton_mavadGhazaei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);


        imageView = findViewById(R.id.imageView55);
        addimage = findViewById(R.id.btnPicture);
        product_name = findViewById(R.id.product_name);
        product_price = findViewById(R.id.product_price);
        ok = findViewById(R.id.bntConfirm);
        radioGroup = findViewById(R.id.radioGroup_product_group);
        radioButton_pooshak = findViewById(R.id.radioBtn_clothing);
        radioButton_digital = findViewById(R.id.radioBtn_digital);
        radioButton_mavadGhazaei = findViewById(R.id.radioBtn_food);


        ActivityCompat.requestPermissions(Add_Products.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

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
        Bundle extras = getIntent().getExtras();
        userName = extras.getString("key_username");
        Cursor res2 = personDB.ShowallData();
        while (res2.moveToNext()) {
            if (res2.getString(1).equals(userName)) {
                mobileNumber = res2.getString(6);
                break;
            }
        }
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = mahsoolat.insertData(userName, nameImageSaved, product_name.getText().toString().trim(), Integer.parseInt(product_price.getText().toString()), product_group, mobileNumber,0);
                if (b == true) {
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SellerPage.class);
                    intent.putExtra("key_username", userName);
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), "Not saved", Toast.LENGTH_SHORT).show();
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