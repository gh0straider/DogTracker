package com.gh0straider.dogtracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DogsDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dogtracker.db";
    private static final int DATABASE_VERSION = 2;

    public DogsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DogsProvider.Tables.DOGS + " ("
                + DogsContract.ItemsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DogsContract.ItemsColumns.NAME + " TEXT,"
                + DogsContract.ItemsColumns.BIRTH_DATE + " TEXT NOT NULL,"
                + DogsContract.ItemsColumns.TAG_NUMBER + " TEXT NOT NULL,"
                + DogsContract.ItemsColumns.RABIES_DATE + " TEXT NOT NULL,"
                + DogsContract.ItemsColumns.PARVO_DATE + " TEXT NOT NULL,"
                + DogsContract.ItemsColumns.DISTEMPER_DATE + " TEXT NOT NULL,"
                + DogsContract.ItemsColumns.WORM_DATE + " TEXT NOT NULL,"
                + DogsContract.ItemsColumns.CYCLE_DATE + " TEXT NOT NULL,"
                + DogsContract.ItemsColumns.PHOTO_URL + " TEXT NOT NULL"
                + ")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DogsProvider.Tables.DOGS);
        onCreate(db);
    }
}
