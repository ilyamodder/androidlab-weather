package ru.ilyamodder.contentprovider.adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.ilyamodder.contentprovider.R;
import ru.ilyamodder.contentprovider.sqlite.WeatherTable;

/**
 * Created by ilya on 16.11.16.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private Cursor mCursor;

    public WeatherAdapter(Cursor cursor) {
        mCursor = cursor;
    }

    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false));
    }

    @Override
    public void onBindViewHolder(WeatherAdapter.ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        DateFormat format = SimpleDateFormat.getDateInstance();
        holder.mDate.setText(format.format(mCursor.getLong(mCursor.getColumnIndex(WeatherTable.Columns.DATE)) * 1000));
        holder.mHumidity.setText(mCursor.getLong(mCursor.getColumnIndex(WeatherTable.Columns.HUMIDITY)) + "%");
        holder.mWindSpeed.setText(mCursor.getLong(mCursor.getColumnIndex(WeatherTable.Columns.WIND_SPEED)) + " м/с");

        long maxTemp = mCursor.getLong(mCursor.getColumnIndex(WeatherTable.Columns.MAX_TEMP));
        long minTemp = mCursor.getLong(mCursor.getColumnIndex(WeatherTable.Columns.MIN_TEMP));

        holder.mTemperature.setText(minTemp + "°C..." + maxTemp + "°C");
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.date)
        TextView mDate;
        @BindView(R.id.humidity)
        TextView mHumidity;
        @BindView(R.id.wind_speed)
        TextView mWindSpeed;
        @BindView(R.id.temperature)
        TextView mTemperature;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
