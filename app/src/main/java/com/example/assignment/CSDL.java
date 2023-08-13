package com.example.assignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CSDL extends SQLiteOpenHelper {
    public CSDL(Context context) {
        super(context, "SPDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table SanPham(" +
                "masp integer primary key," +
                "tensp text," +
                "giaban real," +
                "soluong integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("Drop Table If Exists SanPham");
            onCreate(db);
        }
    }
}
