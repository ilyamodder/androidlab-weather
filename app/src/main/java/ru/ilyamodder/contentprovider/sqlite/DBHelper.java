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
        db.rawQuery("CREATE TABLE " + RequestsTable.NAME + " (" + RequestsTable.Columns._ID +
            " INT PRIMARY KEY AUTOINCREMENT, " + RequestsTable.Columns.RESP_CODE + " INT, " +
            RequestsTable.Columns.URL + " TEXT)", null);
        db.rawQuery("CREATE TABLE " + WeatherTable.NAME + " (" + WeatherTable.Columns._ID +
                " INT PRIMARY KEY AUTOINCREMENT, " + WeatherTable.Columns.DATE + " DATE, " +
                WeatherTable.Columns.HUMIDITY + " INT, " + WeatherTable.Columns.MAX_TEMP +
                " INT, " + WeatherTable.Columns.MIN_TEMP + "INT, " +
                WeatherTable.Columns.WIND_SPEED + " INT)", null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
