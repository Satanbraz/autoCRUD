package com.example.autocrud.Controler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AutoBD extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "DBVehiculos.db";
    public static final String TABLE = "vehiculos";

    public AutoBD(Context context) {
        super(context,DATABASE_NOMBRE,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE+"("+
        "MARCA TEXT," +
        "MODELO TEXT," +
        "PATENTE TEXT PRIMARY KEY," +
        "COLOR TEXT," +
        "ANIO INT," +
        "CILINDRAJE INT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE "+TABLE);
        onCreate(db);
    }
}

