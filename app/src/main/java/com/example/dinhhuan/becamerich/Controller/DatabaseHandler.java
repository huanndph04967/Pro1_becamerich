package com.example.dinhhuan.becamerich.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dinh_Huan on 11/19/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
private static final  String DATABASE_NAME = "DATA";
private static  final  String TABLE_NAME = "TABLE";
private  static  final  String COLUMN_NOTE = "note";
private  static  final  String COLUMN_MONEY = "0";
private  static  final  String COLUMN_TITLE = "0";
private  static  final Date COLUMN_DATE = Date  ;
private  static  final Date COLUMN_HOUR = Date;
private  static  final  int VERSION = 1;
    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cauTaoBang = "create table " + TABLE_NAME + "(" + COLUMN_TITLE+"text"+ COLUMN_NOTE + " INTEGER, " + COLUMN_MONEY + " text, "+");";
        db.execSQL(cauTaoBang);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  void save(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE,contact.getTitle());
        contentValues.put(COLUMN_NOTE,contact.getnote());
        contentValues.put(COLUMN_MONEY,contact.getMoney());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }
    public ArrayList<Contact> getDulieu(){
    ArrayList<Contact> contactArrayList = new ArrayList<>();
        SQLiteDatabase database= this.getReadableDatabase();
        String cautruyVan = "select * from " + TABLE_NAME;
        Cursor cursor = database.rawQuery(cautruyVan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int money=cursor.getInt(0);
            String note = cursor.getString(1);
            String title = cursor.getString(2);

            Date date = cursor.getColumnCount(3);
            Date hours = cursor.getColumnCount(4);
            Contact contact  = new Contact(title,note,date,money,hours);
            contactArrayList.add(contact);
            cursor.moveToNext();
        }
        return contactArrayList;
    }
    public void xoa(Contact contact){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME,COLUMN_TITLE + " =?",new String[]{String.valueOf(contact.getTitle())});
        database.close();
    }
    public boolean sua(Contact contactold,Contact contactnew){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_TITLE,contactnew.getTitle());
        values.put(COLUMN_NOTE,contactnew.getnote());
        values.put(COLUMN_MONEY,contactnew.getMoney());

        int kt = database.update(TABLE_NAME,values,COLUMN_TITLE + " = " + contactold.getTitle(),null);
        if(kt!=0){
            return true;
        }else {return false;}
    }

}
