package com.example.store;

        import android.database.Cursor;
        import android.os.Bundle;
        import android.widget.TextView;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

        import DataBase.Mahsoolat;

public class SumPrice extends AppCompatActivity {
    TextView sum_price;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_price);
        sum_price=findViewById(R.id.sum_price1);

            Mahsoolat mahsoolat = new Mahsoolat(this);
            Cursor res2 = mahsoolat.ShowallData();
            int sum = 0;
            while (res2.moveToNext()) {
                sum=sum+res2.getInt(4);

            }
            sum_price.setText("Sum of Prices :"+Integer.toString(sum));

    }
}

