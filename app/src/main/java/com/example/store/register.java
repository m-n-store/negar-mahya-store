package com.example.store;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import DataBase.PersonDB;

public class register extends AppCompatActivity {
    public PersonDB personDB;
    RadioButton radioSeller, radioCustomer;
    RadioGroup radioGroup;
    Button btn_ok;
    EditText username, password, address, national_code, mobileNumber;
    String _rol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        radioCustomer = findViewById(R.id.radioBtn_customer);
        radioSeller = findViewById(R.id.radioBtn_seller);
        radioGroup = findViewById(R.id.radioGroupregister);
        btn_ok = findViewById(R.id.btn_ok_Inregister);
        username = findViewById(R.id.useNameInregister);
        password = findViewById(R.id.passwordInregister);
        address = findViewById(R.id.addressInregister);
        national_code = findViewById(R.id.natoinalcodeInregister);
        mobileNumber = findViewById(R.id.mobilenumberInregister);
        if (radioSeller.isChecked()) {
            _rol = "seller";
        } else {
            _rol = "customer";
        }
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (radioSeller.isChecked()) {
                _rol = "seller";
            } else {
                _rol = "customer";
            }
        });

        personDB = new PersonDB(this);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = personDB.ShowallData();

                boolean key_b = true;
                while (res.moveToNext()) {
                    if (username.getText().toString().equals("") || password.getText().toString().equals("") || address.getText().toString().equals("") || national_code.getText().toString().equals("") || mobileNumber.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Please fill the blanket", Toast.LENGTH_SHORT).show();
                        key_b = false;
                        break;
                    } else if (res.getString(1).equals(username.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "This USERName Already Exists", Toast.LENGTH_SHORT).show();
                        key_b = false;
                        break;
                    } else if (res.getString(5).equals(national_code.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "This National Code Already Exists", Toast.LENGTH_SHORT).show();
                        key_b = false;
                        break;
                    } else if (res.getString(5).equals(mobileNumber.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "This Phone Number Already Exists", Toast.LENGTH_SHORT).show();
                        key_b = false;
                        break;
                    }
                }

                if (key_b) {
                    boolean b = personDB.insertData(username.getText().toString().trim(), password.getText().toString().trim(), _rol, address.getText().toString().trim(), national_code.getText().toString().trim(), mobileNumber.getText().toString().trim());
                    if (b == true) {
                        Toast.makeText(getApplicationContext(), "SignUp Successfully", Toast.LENGTH_SHORT).show();
                        if(_rol.equals("seller")) {
                            Intent intent = new Intent(getApplicationContext(), SellerPage.class);
                            intent.putExtra("key_username", username.getText().toString());
                            startActivity(intent);
                        }else{
                            startActivity(new Intent(getApplicationContext(),CustomerPage.class));
                        }
                    } else
                        Toast.makeText(getApplicationContext(), "Not Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}