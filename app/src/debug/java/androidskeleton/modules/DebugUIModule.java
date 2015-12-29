package androidskeleton.modules;


import androidskeleton.activities.DebugAppContainer;
import androidskeleton.components.ApplicationScope;
import androidskeleton.interfaces.AppContainer;
import dagger.Module;
import dagger.Provides;

@Module
public class DebugUIModule {

    @Provides
    @ApplicationScope
    AppContainer provideAppContainer(DebugAppContainer debugAppContainer) {
        return debugAppContainer;
    }
}