package com.example.firma.Conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.firma.tablas.tablas;

public class conexion extends SQLiteOpenHelper {
    //Hacemos referecia mediante un consructor heredar (Super)

    public conexion (Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version){
        super(context,dbname,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        //hacemos el llamado de nuestra tabla
        sqLiteDatabase.execSQL(tablas.CreateTableRegistros);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        sqLiteDatabase.execSQL(tablas.DropTableRegistros);
        onCreate(sqLiteDatabase);
    }
}
