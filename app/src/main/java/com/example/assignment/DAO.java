package com.example.assignment;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class DAO {
    final Context context;
    final CSDL csdl;

    public DAO(Context context, CSDL csdl) {
        this.context = context;
        this.csdl = csdl;
    }

    public List<SanPham> layDSSP(){
        List<SanPham> list = new ArrayList<>();
        SQLiteDatabase database = csdl.getWritableDatabase();
        database.beginTransaction();
        try{
            @SuppressLint("Recycle")
            Cursor cursor = database.rawQuery("Select * From SanPham",null);
            if(cursor != null && cursor.getCount() > 0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    SanPham sp = new SanPham(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getInt(3));
                    list.add(sp);
                    cursor.moveToNext();
                }
                database.setTransactionSuccessful();
            } else Toast.makeText(context, "Dữ liệu trống", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Get List SanPham: "+e);
        } finally {
            database.endTransaction();
        }
        return list;
    }

    public boolean addSP(@NonNull SanPham sp){
        SQLiteDatabase database = csdl.getWritableDatabase();
        database.beginTransaction();
        ContentValues values = new ContentValues();
        values.put("masp", sp.ma);
        values.put("tensp", sp.ten);
        values.put("giaban", sp.gia);
        values.put("soluong", sp.sl);
        int row = (int) database.insert("SanPham", null, values);
        return row != -1;
    }

    public boolean removeSP(int masp){
        SQLiteDatabase database = csdl.getWritableDatabase();
        int row = database.delete( "SanPham", "masp = ?", new String[]{String.valueOf(masp)});
        return row != -1;
    }

    public boolean updateSP(SanPham sp){
        SQLiteDatabase database = csdl.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("masp", sp.ma);
        values.put("tensp", sp.ten);
        values.put("giaban", sp.gia);
        values.put("soluong", sp.sl);
        int row = database.update( "SanPham", values, "masp = ?", new String[]{String.valueOf(sp.ma)});
        return row != -1;
    }
}
