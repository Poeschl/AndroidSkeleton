/**
 * Created by Markus Pöschl o5n 20.12.2015.
 */
@PerActivity
@Component(
        dependencies = CallminderComponent.class,
        modules = {
                ContextModule.class,
                DatabaseModule.class
        }
)
public interface ActivityComponent extends BaseActivityComponent {
}