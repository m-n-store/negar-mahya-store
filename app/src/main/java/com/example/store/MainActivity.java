package com.example.store;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import DataBase.PersonDB;
import DataBase.Counter;

public class MainActivity extends AppCompatActivity {

    public PersonDB personDB;
    TextView CreatAccount;
    EditText editTextUsername, editTextPassword;
    Button btn_login;
    boolean error;
    public Counter counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create data base
        personDB = new PersonDB(this);
        counter= new Counter(this);

        CreatAccount = findViewById(R.id.textView_createAccont);
        editTextPassword = findViewById(R.id.Et_password);
        editTextUsername = findViewById(R.id.Et_username);
        btn_login = findViewById(R.id.btn_login_);
        btn_login.setOnClickListener(v -> {
            Cursor res = personDB.ShowallData();
            error = true;
            if (editTextUsername.getText().toString().equals("admin") && editTextPassword.getText().toString().equals("admin")) {
                counter.insertData(0,0,1);
                Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                startActivity(intent);

                error = false;
            }
            while (res.moveToNext()) {
                if (res.getString(1).equals(editTextUsername.getText().toString())) {
                    if (res.getString(2).equals(editTextPassword.getText().toString())) {

                        if (res.getString(3).equals("seller")) {
                            counter.insertData(0,1,0);
                            Intent intent = new Intent(getApplicationContext(), SellerPage.class);
                            intent.putExtra("key_username", editTextUsername.getText().toString());
                            startActivity(intent);

                            error = false;
                            break;
                        } else if (res.getString(3).equals("customer")) {
                            counter.insertData(1,0,0);
                            Intent intent = new Intent(getApplicationContext(), CustomerPage.class);
                            startActivity(intent);

                            error = false;
                            break;
                        }

                    }
                }
            }
            if (error)
                Toast.makeText(getApplicationContext(), "UserName or Password is WRONG!", Toast.LENGTH_SHORT).show();

        });

        CreatAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), register.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }


}