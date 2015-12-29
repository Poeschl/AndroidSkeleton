package androidskeleton.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import androidskeleton.SkeletonApp;
import androidskeleton.components.ActivityComponent;
import androidskeleton.components.ApplicationComponent;
import androidskeleton.components.DaggerActivityComponent;
import androidskeleton.interfaces.HasComponent;
import androidskeleton.modules.ActivityModule;
import apps.androidskeleton.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements HasComponent<ActivityComponent> {

    //Only for testing the DI
    @Inject
    protected Context context;

    @Bind(R.id.testText)
    protected TextView testText;

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SkeletonApp app = SkeletonApp.get(this);
        onCreateComponent(app.getComponent());

        ButterKnife.bind(this);

        testText.setText(context.getPackageName());
    }

    protected void onCreateComponent(ApplicationComponent appComponent) {
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .build();
        activityComponent.inject(this);
    }

    @Override
    public ActivityComponent getComponent() {
        return activityComponent;
    }
}
