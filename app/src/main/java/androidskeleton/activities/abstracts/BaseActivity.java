package androidskeleton.activities.abstracts;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import javax.inject.Inject;

import androidskeleton.SkeletonApp;
import androidskeleton.components.ApplicationComponent;
import androidskeleton.interfaces.AppContainer;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    AppContainer appContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SkeletonApp app = SkeletonApp.get(this);
        onCreateComponent(app.getComponent());

        if (appContainer == null) {
            throw new IllegalStateException("No injection happened. Add component.inject(this) in onCreateComponent() implementation.");
        }

        final LayoutInflater layoutInflater = getLayoutInflater();
        ViewGroup container = appContainer.get(this);
        layoutInflater.inflate(getLayoutId(), container);
    }


    /**
     * Must be implemented by derived activities. Injection must be performed here.
     * Otherwise IllegalStateException will be thrown. Derived activity is
     * responsible to create and store it's component.
     *
     * @param appComponent application level component
     */
    protected abstract void onCreateComponent(ApplicationComponent appComponent);

    @LayoutRes
    protected abstract int getLayoutId();
}
