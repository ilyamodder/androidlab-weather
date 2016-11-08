package ru.ilyamodder.contentprovider.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class LoadingService extends IntentService {
    private static final String ACTION_GET_WEATHER = "ru.ilyamodder.contentprovider.service.action.GET_WEATHER";

    public LoadingService() {
        super("LoadingService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void loadWeather(Context context) {
        Intent intent = new Intent(context, LoadingService.class);
        intent.setAction(ACTION_GET_WEATHER);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_WEATHER.equals(action)) {
                handleActionGetWeather();
            }
        }
    }

    /**
     * Handle action in the provided background thread with the provided
     * parameters.
     */
    private void handleActionGetWeather() {

    }
}
