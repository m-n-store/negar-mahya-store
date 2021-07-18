package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Counter extends SQLiteOpenHelper {

    public static final String DB_NAME = "Counter.db";
    public static final String TBL_NAME = "Counter";

    public Counter(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TBL_NAME + "(Id INTEGER PRIMARY KEY AutoIncrement , counter_costumer TEXT , counter_seller TEXT , counter_admin TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(db);
    }

    public boolean insertData(Integer counter_costumer, Integer counter_seller,Integer counter_admin) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("counter_costumer", counter_costumer);
        cv.put("counter_seller", counter_seller);
        cv.put("counter_admin", counter_admin);
       long result = db.insert(TBL_NAME, null, cv);

        if (result == -1)
            return false;
        else
            return true;
    }

    public void deleteData(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TBL_NAME, "Id=?", new String[]{id});

    }

    public void updateData(String id, String n) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("Favorite", n);

        long result = db.update(TBL_NAME, cv, "Id=?", new String[]{id});

    }

    public Cursor ShowAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TBL_NAME, null);
        return result;
    }

}
