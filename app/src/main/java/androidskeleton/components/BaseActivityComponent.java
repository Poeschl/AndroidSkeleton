package androidskeleton.components;


import androidskeleton.activities.MainActivity;

/**
 * Contains all inject methods of the ActivityComponent. Will be inherited from {@link ActivityComponent}.
 * Created by Markus Pöschl on 20.12.2015.
 */
public interface BaseActivityComponent {
    void inject(MainActivity activity);
}
