package androidskeleton.components;

import androidskeleton.SkeletonApp;
import androidskeleton.modules.ApplicationModule;
import dagger.Component;

@ApplicationScope
@Component(
        modules = {
                ApplicationModule.class
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
