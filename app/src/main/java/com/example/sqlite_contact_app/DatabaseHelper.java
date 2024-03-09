package com.example.sqlite_contact_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

public  static final String DATABASE_NAME="nareshtech";
public static final int VERSION=1;

public static final String TABLE_NAME="person";
public static final String COL_1="person_id";
public static final String COL_2="person_name";
public static final String COL_3="person_age";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null , VERSION);
    }

    //create table in database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createQuery="create table "+TABLE_NAME+"("+COL_1+" " +
                "integer primary key autoincrement, "+COL_2+" text,"+COL_3+" integer)";
        sqLiteDatabase.execSQL(createQuery);
    }

    //used to upgrade database when version change
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertData(ContentValues values){
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
    }
    //read
    public Cursor getAllData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select *from "+TABLE_NAME,null,null);
        return c;
    }
}
