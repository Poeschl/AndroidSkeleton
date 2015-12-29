package androidskeleton.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.jakewharton.scalpel.ScalpelFrameLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

import androidskeleton.annotations.ScalpelEnabled;
import androidskeleton.annotations.ScalpelWireframeEnabled;
import androidskeleton.interfaces.AppContainer;
import androidskeleton.models.BooleanPreference;
import apps.androidskeleton.BuildConfig;
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
    ScalpelFrameLayout scalpelFrameLayout;


    @Bind(R.id.debug_ui_scalpel)
    Switch uiScalpelView;
    @Bind(R.id.debug_ui_scalpel_wireframe)
    Switch uiScalpelWireframeView;

    @Bind(R.id.debug_build_name)
    TextView buildNameView;
    @Bind(R.id.debug_build_code)
    TextView buildCodeView;
    @Bind(R.id.debug_build_sha)
    TextView buildShaView;
    @Bind(R.id.debug_build_date)
    TextView buildDateView;

    @Bind(R.id.debug_device_make)
    TextView deviceMakeView;
    @Bind(R.id.debug_device_model)
    TextView deviceModelView;
    @Bind(R.id.debug_device_resolution)
    TextView deviceResolutionView;
    @Bind(R.id.debug_device_density)
    TextView deviceDensityView;
    @Bind(R.id.debug_device_release)
    TextView deviceReleaseView;
    @Bind(R.id.debug_device_api)
    TextView deviceApiView;

    private Activity activity;

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
        this.activity = activity;
        Context drawerContext = activity;

        activity.setContentView(R.layout.debug_activity_frame);

        // Manually find the debug drawer and inflate the drawer layout inside of it.
        ViewGroup drawer = ButterKnife.findById(activity, R.id.debug_drawer);
        LayoutInflater.from(drawerContext).inflate(R.layout.debug_drawer_layout, drawer);

        // Inject after inflating the drawer layout so its views are available to inject.
        ButterKnife.bind(this, activity);

        drawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                //Do init stuff for the drawer
            }
        });

        setUpScalpel();
        setupBuildSection();
        setupDeviceSection();

        riseAndShine(activity);
        return scalpelFrameLayout;
    }


    private void setUpScalpel() {
        boolean scalpelActive = scalpelEnabled.get();
        scalpelFrameLayout.setLayerInteractionEnabled(scalpelActive);
        uiScalpelView.setChecked(scalpelActive);
        uiScalpelWireframeView.setEnabled(scalpelActive);
        uiScalpelView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                scalpelEnabled.set(isChecked);
                scalpelFrameLayout.setLayerInteractionEnabled(isChecked);
                uiScalpelWireframeView.setEnabled(isChecked);
            }
        });

        boolean wireframeActive = scalpelWireframeEnabled.get();
        scalpelFrameLayout.setDrawViews(!wireframeActive);
        uiScalpelWireframeView.setChecked(wireframeActive);
        uiScalpelWireframeView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                scalpelWireframeEnabled.set(isChecked);
                scalpelFrameLayout.setDrawViews(!isChecked);
            }
        });
    }

    private void setupBuildSection() {
        buildNameView.setText(BuildConfig.VERSION_NAME);
        buildCodeView.setText(String.valueOf(BuildConfig.VERSION_CODE));
        buildShaView.setText(BuildConfig.GIT_SHA);

        try {
            // Parse ISO8601-format time into local time.
            DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.US);
            inFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date buildTime = inFormat.parse(BuildConfig.BUILD_TIME);
            buildDateView.setText(new SimpleDateFormat("yyyy-MM-dd kk:mm", Locale.US).format(buildTime));
        } catch (ParseException e) {
            throw new RuntimeException("Unable to decode build time: " + BuildConfig.BUILD_TIME, e);
        }
    }

    private void setupDeviceSection() {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        String densityBucket = getDensityString(displayMetrics);
        deviceMakeView.setText(truncString(Build.MANUFACTURER, 20));
        deviceModelView.setText(truncString(Build.MODEL, 20));
        deviceResolutionView.setText(displayMetrics.heightPixels + "x" + displayMetrics.widthPixels);
        deviceDensityView.setText(displayMetrics.densityDpi + "dpi (" + densityBucket + ")");
        deviceReleaseView.setText(Build.VERSION.RELEASE);
        deviceApiView.setText(String.valueOf(Build.VERSION.SDK_INT));
    }

    private String truncString(String org, int length) {
        return org.length() > length ? org.substring(0, length) : org;
    }

    private static String getDensityString(DisplayMetrics displayMetrics) {
        switch (displayMetrics.densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                return "ldpi";
            case DisplayMetrics.DENSITY_MEDIUM:
                return "mdpi";
            case DisplayMetrics.DENSITY_HIGH:
                return "hdpi";
            case DisplayMetrics.DENSITY_XHIGH:
                return "xhdpi";
            case DisplayMetrics.DENSITY_XXHIGH:
                return "xxhdpi";
            case DisplayMetrics.DENSITY_XXXHIGH:
                return "xxxhdpi";
            case DisplayMetrics.DENSITY_TV:
                return "tvdpi";
            default:
                return "unknown";
        }
    }

    /**
     * Show the activity over the lock-screen and wake up the device. If you launched the app manually
     * both of these conditions are already true. If you deployed from the IDE, however, this will
     * save you from hundreds of power button presses and pattern swiping per day!
     */
    public static void riseAndShine(Activity activity) {
        activity.getWindow().addFlags(FLAG_SHOW_WHEN_LOCKED);

        PowerManager power = (PowerManager) activity.getSystemService(POWER_SERVICE);
        PowerManager.WakeLock lock =
                power.newWakeLock(FULL_WAKE_LOCK | ACQUIRE_CAUSES_WAKEUP | ON_AFTER_RELEASE, "wakeup!");
        lock.acquire();
        lock.release();
    }
}
