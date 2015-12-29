package androidskeleton.interfaces;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface DebugDrawerSection {

    String getSectionTitle();

    View drawSection(ViewGroup root, LayoutInflater inflater);

    void onDrawerOpen();
}
