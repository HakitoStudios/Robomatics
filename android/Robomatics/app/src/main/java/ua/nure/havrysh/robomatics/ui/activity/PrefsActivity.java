package ua.nure.havrysh.robomatics.ui.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import ua.nure.havrysh.robomatics.R;

public class PrefsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.prefs);
    }
}
