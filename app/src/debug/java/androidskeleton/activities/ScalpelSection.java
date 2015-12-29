package androidskeleton.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.jakewharton.scalpel.ScalpelFrameLayout;

import androidskeleton.interfaces.DebugDrawerSection;
import androidskeleton.models.BooleanPreference;
import apps.androidskeleton.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ScalpelSection implements DebugDrawerSection {

    @Bind(R.id.debug_ui_scalpel)
    Switch uiScalpelView;
    @Bind(R.id.debug_ui_scalpel_wireframe)
    Switch uiScalpelWireframeView;

    private ScalpelFrameLayout debugLayoutFrame;

    private BooleanPreference scalpelEnabled;
    private BooleanPreference scalpelWireframeEnabled;

    public ScalpelSection(BooleanPreference scalpelEnabled, BooleanPreference scalpelWireframeEnabled, ScalpelFrameLayout debugLayoutFrame) {
        this.scalpelEnabled = scalpelEnabled;
        this.scalpelWireframeEnabled = scalpelWireframeEnabled;
        this.debugLayoutFrame = debugLayoutFrame;
    }

    @Override
    public String getSectionTitle() {
        return "Scalpel Settings";
    }

    @Override
    public View drawSection(ViewGroup root, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.scalpel_section, root);

        ButterKnife.bind(this, view);

        setUpScalpel();

        return view;
    }

    @Override
    public void onDrawerOpen() {

    }

    private void setUpScalpel() {
        boolean scalpelActive = scalpelEnabled.get();
        debugLayoutFrame.setLayerInteractionEnabled(scalpelActive);
        uiScalpelView.setChecked(scalpelActive);
        uiScalpelWireframeView.setEnabled(scalpelActive);
        uiScalpelView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                scalpelEnabled.set(isChecked);
                debugLayoutFrame.setLayerInteractionEnabled(isChecked);
                uiScalpelWireframeView.setEnabled(isChecked);
            }
        });

        boolean wireframeActive = scalpelWireframeEnabled.get();
        debugLayoutFrame.setDrawViews(!wireframeActive);
        uiScalpelWireframeView.setChecked(wireframeActive);
        uiScalpelWireframeView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                scalpelWireframeEnabled.set(isChecked);
                debugLayoutFrame.setDrawViews(!isChecked);
            }
        });
    }
}
