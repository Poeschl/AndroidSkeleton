package androidskeleton.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import androidskeleton.interfaces.DebugDrawerSection;
import apps.androidskeleton.BuildConfig;
import apps.androidskeleton.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mpo on 29.12.15.
 */
public class BuildSection implements DebugDrawerSection {

    @Bind(R.id.debug_build_name)
    TextView buildNameView;
    @Bind(R.id.debug_build_code)
    TextView buildCodeView;
    @Bind(R.id.debug_build_sha)
    TextView buildShaView;
    @Bind(R.id.debug_build_date)
    TextView buildDateView;


    @Override
    public String getSectionTitle() {
        return "Build Information";
    }

    @Override
    public View drawSection(ViewGroup root, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.build_section, root);

        ButterKnife.bind(this, view);

        setupBuildSection();

        return view;
    }

    @Override
    public void onDrawerOpen() {
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
}
