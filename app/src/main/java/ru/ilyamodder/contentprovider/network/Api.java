package ru.ilyamodder.contentprovider.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.ilyamodder.contentprovider.BuildConfig;

/**
 * Created by ilya on 08.11.16.
 */

public class Api {
    private static WeatherService sService;

    static {
        OkHttpClient okHttp = new OkHttpClient.Builder()
                .addInterceptor(new ApiKeyInterceptor())
                .addInterceptor(new HttpLoggingInterceptor())
                .build();
        sService = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp)
                .build()
                .create(WeatherService.class);
    }

    public static WeatherService getService() {
        return sService;
    }
}
