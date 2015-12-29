package androidskeleton.models;

import android.content.SharedPreferences;

public class BooleanPreference {
    private final SharedPreferences preferences;
    private final String key;
    private final boolean defaultVal;

    public BooleanPreference(SharedPreferences preferences, String key, boolean defaultVal) {
        this.preferences = preferences;
        this.key = key;
        this.defaultVal = defaultVal;
    }

    public boolean get() {
        return preferences.getBoolean(key, defaultVal);
    }

    public boolean isSet() {
        return preferences.contains(key);
    }

    public void set(boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    public void delete() {
        preferences.edit().remove(key).apply();
    }
}
