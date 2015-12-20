import de.poeschl.apps.androidskeleton.modules.SkeletonAppModule;

/**
 * This component is the bridge for the dependency injections for release builds.
 * <p/>
 * Created by Markus Pöschl on 20.12.2015.
 */
@AppScope
@Component(
        modules = {
                SkeletonAppModule.class
        }
)
public interface CallminderComponent extends BaseAppComponent {

    final class Initializer {
        private Initializer() {
        }

        public static CallminderComponent init(CallminderApp app) {
            return DaggerCallminderComponent.builder()
                    .skeletonAppModule(new SkeletonAppModule(app))
                    .build();
        }
    }
}