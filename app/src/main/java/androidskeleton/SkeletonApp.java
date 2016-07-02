package androidskeleton;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

import androidskeleton.components.ApplicationComponent;
import timber.log.Timber;

public class SkeletonApp extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = ApplicationComponent.Initializer.init(this);

        Timber.plant(new Timber.DebugTree());
        LeakCanary.install(this);
    }

    public static SkeletonApp get(Context context) {
        return (SkeletonApp) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}