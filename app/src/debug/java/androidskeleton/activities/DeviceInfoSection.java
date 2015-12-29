package androidskeleton.activities;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidskeleton.interfaces.DebugDrawerSection;
import apps.androidskeleton.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class DeviceInfoSection implements DebugDrawerSection {

    @Bind(R.id.debug_device_make)
    protected TextView deviceMakeView;
    @Bind(R.id.debug_device_model)
    protected TextView deviceModelView;
    @Bind(R.id.debug_device_resolution)
    protected TextView deviceResolutionView;
    @Bind(R.id.debug_device_density)
    protected TextView deviceDensityView;
    @Bind(R.id.debug_device_release)
    protected TextView deviceReleaseView;
    @Bind(R.id.debug_device_api)
    protected TextView deviceApiView;

    protected DisplayMetrics displayMetrics;

    DeviceInfoSection(Context context) {
        displayMetrics = context.getResources().getDisplayMetrics();
    }

    @Override
    public View drawSection(ViewGroup root, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.device_info_section, root);

        ButterKnife.bind(this, view);

        setupDeviceSection();

        return view;
    }

    @Override
    public String getSectionTitle() {
        return "Device Information";
    }

    private void setupDeviceSection() {
        String densityBucket = getDensityString(displayMetrics);
        deviceMakeView.setText(truncString(Build.MANUFACTURER, 20));
        deviceModelView.setText(truncString(Build.MODEL, 20));
        deviceResolutionView.setText(displayMetrics.heightPixels + "x" + displayMetrics.widthPixels);
        deviceDensityView.setText(displayMetrics.densityDpi + "dpi (" + densityBucket + ")");
        deviceReleaseView.setText(Build.VERSION.RELEASE);
        deviceApiView.setText(String.valueOf(Build.VERSION.SDK_INT));
    }

    @Override
    public void onDrawerOpen() {
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
}
