package com.example.usuario.displayoffline.Model.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "tcg_db";
    public static final Integer DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //called only once when the database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        //for each table, a DAODATABSE
        String query = "CREATE TABLE " + DAOTrackDatabase.TABLE_NAME + " (" +
                DAOTrackDatabase.COLUMN_ID + " TEXT PRIMARY KEY, " +
                DAOTrackDatabase.COLUMN_IMAGE + " TEXT NOT NULL, " +
                DAOTrackDatabase.COLUMN_TITLE + " TEXT NOT NULL, " +
                DAOTrackDatabase.COLUMN_ALBUMID + " TEXT NOT NULL);";

        db.execSQL(query);
    }

    //called when the version number is superior
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
