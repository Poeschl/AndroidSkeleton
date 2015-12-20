package de.poeschl.apps.androidskeleton.components;

import de.poeschl.apps.androidskeleton.activities.MainActivity;

/**
 * Created by Markus Pöschl on 20.12.2015.
 */
public interface BaseContextComponent {
    void inject(MainActivity activity);
}
