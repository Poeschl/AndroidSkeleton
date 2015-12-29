package androidskeleton.data;

import android.content.SharedPreferences;

import androidskeleton.annotations.ScalpelEnabled;
import androidskeleton.annotations.ScalpelWireframeEnabled;
import androidskeleton.components.ApplicationScope;
import androidskeleton.models.BooleanPreference;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Markus PÃ¶schl on 11.05.2015.
 */
@Module(
        includes = {

        }
)
public final class DebugPreferencesModule {

    public static final String DEBUG_SCALPEL_ENABLED_KEY = "debug_scalpel_enabled";
    public static final String DEBUG_SCALPEL_WIREFRAME_ENABLED_KEY = "debug_scalpel_wireframe_enabled";

    @Provides
    @ApplicationScope
    @ScalpelEnabled
    BooleanPreference provideScalpelEnabled(SharedPreferences preferences) {
        return new BooleanPreference(preferences, DEBUG_SCALPEL_ENABLED_KEY, false);
    }

    @Provides
    @ApplicationScope
    @ScalpelWireframeEnabled
    BooleanPreference provideScalpelWireframeEnabled(SharedPreferences preferences) {
        return new BooleanPreference(preferences, DEBUG_SCALPEL_WIREFRAME_ENABLED_KEY, false);
    }

}
