package de.poeschl.apps.androidskeleton.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.poeschl.apps.androidskeleton.R;
import de.poeschl.apps.androidskeleton.SkeletonApp;
import de.poeschl.apps.androidskeleton.components.ActivityComponent;
import de.poeschl.apps.androidskeleton.components.ApplicationComponent;
import de.poeschl.apps.androidskeleton.components.DaggerActivityComponent;
import de.poeschl.apps.androidskeleton.interfaces.HasComponent;

public class MainActivity extends Activity implements HasComponent<ActivityComponent> {

    //Only for testing
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
                .build();
        activityComponent.inject(this);
    }

    @Override
    public ActivityComponent getComponent() {
        return activityComponent;
    }
}
