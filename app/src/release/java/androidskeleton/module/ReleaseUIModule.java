package androidskeleton.module;

import androidskeleton.components.ApplicationScope;
import androidskeleton.interfaces.AppContainer;
import dagger.Module;
import dagger.Provides;

@Module
public class ReleaseUIModule {
    @Provides
    @ApplicationScope
    AppContainer provideAppContainer() {
        return AppContainer.DEFAULT_CONTAINER;
    }
}
