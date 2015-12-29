package androidskeleton.modules;

import android.content.Context;

import androidskeleton.components.ActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * The module which holds the activity dependent things.
 * Created by Markus Pöschl on 20.12.2015.
 */
@Module
public class ActivityModule {

    private final Context context;

    public ActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    protected Context provideContext() {
        return context;
    }

}
