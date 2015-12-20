package de.poeschl.apps.androidskeleton.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.poeschl.apps.androidskeleton.R;

/**
 * Created by Markus Pöschl on 20.12.2015.
 */
public class MainActivity extends Activity {

    @Bind(R.id.testText)
    protected TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        testText.setText(getString(R.string.app_name));
    }
}
