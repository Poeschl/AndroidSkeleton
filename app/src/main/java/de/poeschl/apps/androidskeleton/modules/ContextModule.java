package de.poeschl.apps.androidskeleton.modules;

import android.content.Context;

import dagger.Module;

/**
 * Created by Markus Pöschl on 20.12.2015.
 */
@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }
}
