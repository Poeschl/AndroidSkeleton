package androidskeleton.activities;

import android.app.Application;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import androidskeleton.activities.abstracts.BaseActivity;
import androidskeleton.components.ActivityComponent;
import androidskeleton.components.ApplicationComponent;
import androidskeleton.components.DaggerActivityComponent;
import androidskeleton.interfaces.HasComponent;
import androidskeleton.modules.ActivityModule;
import apps.androidskeleton.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements HasComponent<ActivityComponent> {

    @Inject
    protected Application application;

    @Bind(R.id.testText)
    protected TextView testText;

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        testText.setText(application.getPackageName());
    }

    protected void onCreateComponent(ApplicationComponent appComponent) {
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .build();
        activityComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public ActivityComponent getComponent() {
        return activityComponent;
    }
}
