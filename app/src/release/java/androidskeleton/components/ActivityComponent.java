package androidskeleton.components;

import androidskeleton.modules.ActivityModule;
import dagger.Component;


@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                ActivityModule.class
        }
)
public interface ActivityComponent extends BaseActivityComponent {

}
