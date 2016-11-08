package ru.ilyamodder.contentprovider.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.ilyamodder.contentprovider.model.WeatherData;

/**
 * Created by ilya on 08.11.16.
 */

public interface WeatherService {
    @GET("forecast/daily?mode=json&units=metric&lang=ru")
    Call<WeatherData> getForecast(@Query("q") String city, @Query("cnt") int count);
}
