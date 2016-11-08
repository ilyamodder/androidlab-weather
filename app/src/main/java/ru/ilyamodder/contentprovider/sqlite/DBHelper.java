package ru.ilyamodder.contentprovider.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ilya on 02.11.16.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, "cache.db", null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + RequestsTable.NAME + " (" + RequestsTable.Columns._ID +
            " INTEGER PRIMARY KEY, " + RequestsTable.Columns.RESP_CODE + " INTEGER, " +
            RequestsTable.Columns.URL + " TEXT)");
        db.execSQL("CREATE TABLE " + WeatherTable.NAME + " (" + WeatherTable.Columns._ID +
                " INTEGER PRIMARY KEY, " + WeatherTable.Columns.DATE + " DATE, " +
                WeatherTable.Columns.HUMIDITY + " INTEGER, " + WeatherTable.Columns.MAX_TEMP +
                " INTEGER, " + WeatherTable.Columns.MIN_TEMP + " INTEGER, " +
                WeatherTable.Columns.WIND_SPEED + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
