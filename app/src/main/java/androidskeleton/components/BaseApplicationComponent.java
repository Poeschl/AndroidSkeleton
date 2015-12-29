package androidskeleton.components;

import android.app.Application;

import androidskeleton.SkeletonApp;
import androidskeleton.interfaces.AppContainer;

/**
 * Contains all inject methods of the ApplicationComponent. Will be inherited from {@link ApplicationComponent}.
 * Created by Markus Pöschl on 20.12.2015.
 */
public interface BaseApplicationComponent {

    void inject(SkeletonApp skeletonApp);

    //This method stumps are here to make them also visible in the Activity scope!!!
    AppContainer appContainer();

    Application application();
}
