package de.poeschl.apps.androidskeleton.components;

import dagger.Component;
import de.poeschl.apps.androidskeleton.SkeletonApp;
import de.poeschl.apps.androidskeleton.modules.ApplicationModule;

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
