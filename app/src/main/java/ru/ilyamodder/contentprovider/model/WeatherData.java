package ru.ilyamodder.contentprovider.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by ilya on 08.11.16.
 */

public class WeatherData {
    @SerializedName("list")
    private List<Day> mDays;

    public class Day {
        @SerializedName("dt")
        long mDate;
        @SerializedName("speed")
        double mWindSpeed;
        @SerializedName("humidity")
        int mHumidity;
        @SerializedName("temp")
        Temp mTemp;

        public long getDate() {
            return mDate;
        }

        public double getWindSpeed() {
            return mWindSpeed;
        }

        public int getHumidity() {
            return mHumidity;
        }

        public double getMaxTemp() {
            return mTemp.mMax;
        }

        public double getMinTemp() {
            return mTemp.mMin;
        }
    }

    private class Temp {
        @SerializedName("max")
        double mMax;
        @SerializedName("min")
        double mMin;
    }

    public List<Day> getDays() {
        return mDays;
    }
}
