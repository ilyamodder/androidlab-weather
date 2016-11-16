package ru.ilyamodder.contentprovider.sqlite;

import android.provider.BaseColumns;

/**
 * Created by ilya on 02.11.16.
 */

public interface WeatherTable {
    String NAME = "weather";

    interface Columns extends BaseColumns {
        String DATE = "date";
        String WIND_SPEED = "wind_speed";
        String MAX_TEMP = "max_temp";
        String MIN_TEMP = "min_temp";
        String HUMIDITY = "humidity";
    }
}
