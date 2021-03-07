package com.example.bank.Helper;

import android.content.ContentValues;
import android.content.Context;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper{
    private static DBHelper instance;
    private static final int DATABASE_VER=1;
    public static final String DATABASE_NAME="bank.db";
    public static final String TABLE_NAME="user";
    public static final String COLUMN_EMAIL="email";
    public static final String key="esilv";
    private static final String SQL_CREATE_TABLE_QUERY=
            "CREATE TABLE "+TABLE_NAME+"("+COLUMN_EMAIL+" TEXT PRIMARY KEY)";
    private static final String SQL_DELETE_TABLE_QUERY=
            "DROP TABLE IF EXISTS "+TABLE_NAME;
    public DBHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VER);
    }

    static public synchronized DBHelper getInstance(Context context){
        if(instance==null)
            instance=new DBHelper(context);
        return instance;
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_CREATE_TABLE_QUERY);
        onCreate(db);
    }
    public void insertNewEmail(String email){
        SQLiteDatabase db=instance.getWritableDatabase(key);
        ContentValues values=new ContentValues();
        values.put(COLUMN_EMAIL,email);
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public void updateNewEmail(String oldemail,String newemail){
        SQLiteDatabase db=instance.getWritableDatabase(key);
        ContentValues values=new ContentValues();
        values.put(COLUMN_EMAIL,newemail);
        db.update(TABLE_NAME,values,COLUMN_EMAIL+"='"+oldemail+"'",null);
        db.close();
    }
    public void deleteNewEmail(String email){
        SQLiteDatabase db=instance.getWritableDatabase(key);
        ContentValues values=new ContentValues();
        values.put(COLUMN_EMAIL,email);
        db.delete(TABLE_NAME,COLUMN_EMAIL+"='"+email+"'",null);
        db.close();
    }
    public List<String> getAllEmail(){
        SQLiteDatabase db=instance.getWritableDatabase(key);
        Cursor cursor=db.rawQuery(String.format("SELECT * FROM '%s';",TABLE_NAME),null);
        List<String> emails=new ArrayList<>();
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                String email=cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                emails.add(email);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return emails;
    }
}
