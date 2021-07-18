package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Mahsoolat extends SQLiteOpenHelper {

    public static final String DB_NAME="Mahsoolat.db";
    public static final String TBL_NAME="Mahsolat";

    public Mahsoolat(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TBL_NAME + "(Id INTEGER PRIMARY KEY AutoIncrement , username TEXT , image TEXT , namemahsool TEXT , price INTEGER , grouh TEXT , mobilenumber TEXT,priority Boolean)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(db);
    }

    public boolean insertData(String user_name,String image,String namemahsool,int price,String grouh,String mobile_number,Integer priority){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("username",user_name);
        cv.put("image",image);
        cv.put("namemahsool",namemahsool);
        cv.put("price",price);
        cv.put("grouh",grouh);
        cv.put("mobilenumber",mobile_number);
        cv.put("priority",priority);

        long result=db.insert(TBL_NAME,null,cv);

        if(result==-1)
            return false;
        else
            return true;
    }

    public void deleteData(String id){

        SQLiteDatabase db=this.getWritableDatabase();

        long result=db.delete(TBL_NAME,"Id=?",new String[] {id});

    }

    public void updateData(String id,String user_name,String image,String namemahsool,int price,String grouh,String mobile_number,int priority){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put("username",user_name);
        cv.put("image",image);
        cv.put("namemahsool",namemahsool);
        cv.put("price",price);
        cv.put("grouh",grouh);
        cv.put("mobilenumber",mobile_number);
        cv.put("priority",priority);

        db.update(TBL_NAME,cv,"Id=?",new String [] {id});
        db.close();

    }

    public Cursor ShowallData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from " + TBL_NAME,null);
        return result;
    }

}
