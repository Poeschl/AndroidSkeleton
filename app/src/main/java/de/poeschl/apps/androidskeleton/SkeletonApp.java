package de.poeschl.apps.androidskeleton;

import android.app.Application;
import android.content.Context;

import de.poeschl.apps.androidskeleton.components.SkeletonComponent;
import timber.log.Timber;

/**
 * Created by Markus Pöschl on 20.12.2015.
 */
public class SkeletonApp extends Application {

    private SkeletonComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = SkeletonComponent.Initializer.init(this);

        Timber.plant(new Timber.DebugTree());

    }

    public static SkeletonApp get(Context context) {
        return (SkeletonApp) context.getApplicationContext();
    }

    public SkeletonComponent getComponent() {
        return component;
    }
}