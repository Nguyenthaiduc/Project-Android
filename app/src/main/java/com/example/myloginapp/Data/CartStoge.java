package com.example.myloginapp.Data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CartStoge extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cartstorage2.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "cartdata";
    private static final String Key_ID = "key_id";
    private static final String NAME = "name";

    public CartStoge(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT)", TABLE_NAME,Key_ID,NAME);

        db.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_table);
        onCreate(db);
    }

    public long addtolist(DTOClip dtoClip)  {
        long result = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(NAME,dtoClip.title);
            result = db.insert(TABLE_NAME, null, values);
            db.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    public ArrayList<DTOClip> getallcart() {

        ArrayList<DTOClip> List = new ArrayList<>();
        String query = "SELECT * FROM cartdata";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            DTOClip cart = new DTOClip();
            cart.setId(cursor.getInt(0));
            cart.setTitle(cursor.getString(1));
            List.add(cart);
            cursor.moveToNext();
        }
        return List;
    }

    public long deleteCart(String ID) {
        long result = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            result = db.delete(TABLE_NAME, Key_ID + " = ?", new String[]{String.valueOf(ID)});
            System.out.println("xoa ok");
            db.close();
        } catch (Exception e) {

        }
        return result;
    }
}
