package de.poeschl.apps.androidskeleton.components;

import dagger.Component;
import de.poeschl.apps.androidskeleton.SkeletonApp;
import de.poeschl.apps.androidskeleton.modules.SkeletonAppModule;

/**
 * Created by Markus Pöschl on 20.12.2015.
 */
@AppScope
@Component(
        modules = {
                SkeletonAppModule.class
        }
)
public interface SkeletonComponent extends BaseAppComponent {

    final class Initializer {
        private Initializer() {
        }

        public static SkeletonComponent init(SkeletonApp app) {
            return DaggerSkeletonComponent.builder()
                    .skeletonAppModule(new SkeletonAppModule(app))
                    .build();
        }
    }
}
