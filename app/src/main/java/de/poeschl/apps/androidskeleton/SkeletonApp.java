package de.poeschl.apps.androidskeleton;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Markus Pöschl on 20.12.2015.
 */
public class SkeletonApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

    }
}
