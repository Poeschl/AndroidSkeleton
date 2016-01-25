package androidskeleton.activities;

import android.app.Activity;
import android.content.Context;
import android.os.PowerManager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.processphoenix.ProcessPhoenix;
import com.jakewharton.scalpel.ScalpelFrameLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidskeleton.annotations.ScalpelEnabled;
import androidskeleton.annotations.ScalpelWireframeEnabled;
import androidskeleton.interfaces.AppContainer;
import androidskeleton.interfaces.DebugDrawerSection;
import androidskeleton.models.BooleanPreference;
import apps.androidskeleton.R;
import butterknife.Bind;
import butterknife.ButterKnife;

import static android.content.Context.POWER_SERVICE;
import static android.os.PowerManager.ACQUIRE_CAUSES_WAKEUP;
import static android.os.PowerManager.FULL_WAKE_LOCK;
import static android.os.PowerManager.ON_AFTER_RELEASE;
import static android.view.WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;

public class DebugAppContainer implements AppContainer {

    @Bind(R.id.debug_activity_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.debug_content)
    ScalpelFrameLayout debugLayoutFrame;

    @Bind(R.id.section_container)
    LinearLayout sectionContainer;

    List<DebugDrawerSection> sections;

    private BooleanPreference scalpelEnabled;
    private BooleanPreference scalpelWireframeEnabled;

    @Inject
    public DebugAppContainer(@ScalpelEnabled BooleanPreference scalpel,
                             @ScalpelWireframeEnabled BooleanPreference scalpelWireframe) {
        this.scalpelEnabled = scalpel;
        this.scalpelWireframeEnabled = scalpelWireframe;
    }

    @Override
    public ViewGroup get(final Activity activity) {

        activity.setContentView(R.layout.debug_activity_frame);

        // Manually find the debug drawer and inflate the drawer layout inside of it.
        ViewGroup drawer = ButterKnife.findById(activity, R.id.debug_drawer);
        LayoutInflater inflater = LayoutInflater.from(activity);
        inflater.inflate(R.layout.debug_drawer_layout, drawer);

        // Inject after inflating the drawer layout so its views are available to inject.
        ButterKnife.bind(this, activity);

        initDrawerSections(activity);
        drawDrawerSections(inflater);

        riseAndShine(activity);
        return debugLayoutFrame;
    }

    void initDrawerSections(Activity activity) {
        sections = new ArrayList<>();
        sections.add(new ScalpelSection(scalpelEnabled, scalpelWireframeEnabled, debugLayoutFrame));
        sections.add(new DeviceInfoSection(activity));
        sections.add(new BuildSection());
    }

    void drawDrawerSections(LayoutInflater inflater) {

        sectionContainer.removeAllViews();

        for (DebugDrawerSection section : sections) {
            View sectionView = inflater.inflate(R.layout.section_layout, null);

            TextView sectionTitle = ButterKnife.findById(sectionView, R.id.section_title);
            sectionTitle.setText(section.getSectionTitle());

            FrameLayout sectionContent = ButterKnife.findById(sectionView, R.id.section_content);
            section.drawSection(sectionContent, inflater);

            sectionContainer.addView(sectionView);
        }

        drawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                for (DebugDrawerSection section : sections) {
                    section.onDrawerOpen();
                }
            }
        });
    }

    /**
     * Show the activity over the lock-screen and wake up the device. If you launched the app manually
     * both of these conditions are already true. If you deployed from the IDE, however, this will
     * save you from hundreds of power button presses and pattern swiping per day!
     */
    static void riseAndShine(Activity activity) {
        activity.getWindow().addFlags(FLAG_SHOW_WHEN_LOCKED);

        PowerManager power = (PowerManager) activity.getSystemService(POWER_SERVICE);
        PowerManager.WakeLock lock =
                power.newWakeLock(FULL_WAKE_LOCK | ACQUIRE_CAUSES_WAKEUP | ON_AFTER_RELEASE, "wakeup!");
        lock.acquire();
        lock.release();
    }

    static void requestRestart(Context context) {
        ProcessPhoenix.triggerRebirth(context);
    }
}
