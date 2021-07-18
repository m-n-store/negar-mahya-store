package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PersonDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "PersonDB.db";
    public static final String TBL_NAME = "PersonDB";

    public PersonDB(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TBL_NAME + "(Id INTEGER PRIMARY KEY AutoIncrement , username TEXT , password TEXT , rol TEXT , address TEXT , nationalcode TEXT , mobilenumber TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(db);
    }

    public boolean insertData(String user_name, String password, String rol, String address, String national_code, String mobile_number) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("username", user_name);
        cv.put("password", password);
        cv.put("rol", rol);
        cv.put("address", address);
        cv.put("nationalcode", national_code);
        cv.put("mobilenumber", mobile_number);

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

    public Cursor ShowallData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TBL_NAME, null);
        return result;
    }

}
