package de.poeschl.apps.androidskeleton.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.poeschl.apps.androidskeleton.SkeletonApp;

/**
 * A module for all the android specific dependencies which require a {@link android.content.Context} or a{@link android.app.Application} to create.
 * <p/>
 * Created by Markus Pöschl on 03.05.15.
 */
@Module
public class SkeletonAppModule {
    private final SkeletonApp app;

    public SkeletonAppModule(SkeletonApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    protected Application provideApplication() {
        return app;
    }

}
