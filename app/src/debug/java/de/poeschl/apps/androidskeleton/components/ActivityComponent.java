package de.poeschl.apps.androidskeleton.components;

import dagger.Component;
import de.poeschl.apps.androidskeleton.modules.ActivityModule;


@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                ActivityModule.class
        }
)
public interface ActivityComponent extends BaseActivityComponent {

}
