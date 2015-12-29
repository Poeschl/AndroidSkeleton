package de.poeschl.apps.androidskeleton.modules;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import de.poeschl.apps.androidskeleton.SkeletonApp;
import de.poeschl.apps.androidskeleton.components.ApplicationScope;

/**
 * A module for all the android specific dependencies which require a a{@link android.app.Application} to create.
 * <p/>
 * Created by Markus Pöschl on 03.05.15.
 */
@Module
public class ApplicationModule {
    private final SkeletonApp app;

    public ApplicationModule(SkeletonApp app) {
        this.app = app;
    }

    @Provides
    @ApplicationScope
    protected Application provideApplication() {
        return app;
    }

}
