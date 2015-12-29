package androidskeleton.components;

import androidskeleton.SkeletonApp;
import androidskeleton.data.DebugPreferencesModule;
import androidskeleton.modules.ApplicationModule;
import androidskeleton.modules.DebugUIModule;
import dagger.Component;

@ApplicationScope
@Component(
        modules = {
                ApplicationModule.class,
                DebugUIModule.class,
                DebugPreferencesModule.class
        }
)
public interface ApplicationComponent extends BaseApplicationComponent {

    final class Initializer {
        private Initializer() {
        }

        public static ApplicationComponent init(SkeletonApp app) {
            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(app))
                    .build();
        }
    }
}
