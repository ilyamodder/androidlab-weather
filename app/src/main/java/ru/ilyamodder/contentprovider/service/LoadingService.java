package ru.ilyamodder.contentprovider.service;

import android.app.IntentService;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ilyamodder.contentprovider.model.WeatherData;
import ru.ilyamodder.contentprovider.network.Api;
import ru.ilyamodder.contentprovider.provider.DataProvider;
import ru.ilyamodder.contentprovider.sqlite.RequestsTable;
import ru.ilyamodder.contentprovider.sqlite.WeatherTable;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class LoadingService extends IntentService {
    private static final String ACTION_GET_WEATHER = "ru.ilyamodder.contentprovider.service.action.GET_WEATHER";
    private static final String EXTRA_CITY = "city";
    private static final String EXTRA_KEY = "key";
    public static final int COUNT = 10;

    public LoadingService() {
        super("LoadingService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void loadWeather(Context context, String city, long uniqueKey) {
        Intent intent = new Intent(context, LoadingService.class);
        intent.setAction(ACTION_GET_WEATHER);
        intent.putExtra(EXTRA_CITY, city);
        intent.putExtra(EXTRA_KEY, uniqueKey);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_WEATHER.equals(action)) {
                handleActionGetWeather(intent.getStringExtra(EXTRA_CITY), intent.getLongExtra(EXTRA_KEY, -1));
            }
        }
    }

    /**
     * Handle action in the provided background thread with the provided
     * parameters.
     */
    private void handleActionGetWeather(String city, final long key) {
        Api.getService().getForecast(city, COUNT).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                WeatherData data = response.body();
                ContentValues cv = new ContentValues();
                cv.put(RequestsTable.Columns._ID, key);
                cv.put(RequestsTable.Columns.RESP_CODE, response.code());
                cv.put(RequestsTable.Columns.URL, call.request().url().toString());
                getContentResolver().insert(DataProvider.REQUESTS_URI, cv);

                List<ContentValues> weatherValues = new ArrayList<>();
                for (WeatherData.Day day : data.getDays()) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(WeatherTable.Columns.DATE, day.getDate());
                    contentValues.put(WeatherTable.Columns.HUMIDITY, day.getHumidity());
                    contentValues.put(WeatherTable.Columns.MAX_TEMP, day.getMaxTemp());
                    contentValues.put(WeatherTable.Columns.MIN_TEMP, day.getMinTemp());
                    contentValues.put(WeatherTable.Columns.WIND_SPEED, day.getWindSpeed());
                    weatherValues.add(contentValues);
                }
                getContentResolver().bulkInsert(DataProvider.WEATHER_URI, weatherValues.toArray(new ContentValues[0]));
                getContentResolver().notifyChange(ContentUris.withAppendedId(DataProvider.REQUESTS_URI, key), null);
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Log.e("service", "fail");
                t.printStackTrace();
            }
        });
    }
}
