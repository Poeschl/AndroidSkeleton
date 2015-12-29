package androidskeleton.components;

import androidskeleton.SkeletonApp;

/**
 * Contains all inject methods of the ApplicationComponent. Will be inherited from {@link ApplicationComponent}.
 * Created by Markus Pöschl on 20.12.2015.
 */
public interface BaseApplicationComponent {

    void inject(SkeletonApp skeletonApp);

}
