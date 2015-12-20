package de.poeschl.apps.androidskeleton.components;

import dagger.Component;
import de.poeschl.apps.androidskeleton.modules.ContextModule;

/**
 * Created by Markus Pöschl on 20.12.2015.
 */
@ContextScope
@Component(
        modules = {ContextModule.class}
)
public interface ContextComponent {

}
