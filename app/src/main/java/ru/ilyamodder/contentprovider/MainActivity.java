package ru.ilyamodder.contentprovider;

import android.content.ContentUris;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;

import ru.ilyamodder.contentprovider.provider.DataProvider;
import ru.ilyamodder.contentprovider.service.LoadingService;

public class MainActivity extends AppCompatActivity {

    private ContentObserver mContentObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long id = new Random().nextLong();

        mContentObserver = new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange, Uri uri) {
                Log.i("activity", "change received");
            }
        };

        getContentResolver().registerContentObserver(
                ContentUris.withAppendedId(DataProvider.REQUESTS_URI, id), true, mContentObserver);
        LoadingService.loadWeather(this, "Kazan", id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(mContentObserver);
    }
}
