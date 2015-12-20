package de.poeschl.apps.androidskeleton.activities;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.poeschl.apps.androidskeleton.R;
import de.poeschl.apps.androidskeleton.SkeletonApp;
import de.poeschl.apps.androidskeleton.components.ContextComponent;
import de.poeschl.apps.androidskeleton.components.SkeletonComponent;
import de.poeschl.apps.androidskeleton.interfaces.HasComponent;

/**
 * Created by Markus Pöschl on 20.12.2015.
 */
public class MainActivity extends Activity implements HasComponent<ContextComponent> {

    @Inject
    protected Application application;

    @Bind(R.id.testText)
    protected TextView testText;

    private ContextComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SkeletonApp app = SkeletonApp.get(this);
        onCreateComponent(app.getComponent());

        ButterKnife.bind(this);

        testText.setText(application.getPackageName());
    }

    protected void onCreateComponent(SkeletonComponent appComponent) {
        activityComponent = DaggerContextComponent.builder()
                .androidskeleton(appComponent)

                .build();
        activityComponent.inject(this);
    }

    @Override
    public ContextComponent getComponent() {
        return null;
    }
}
