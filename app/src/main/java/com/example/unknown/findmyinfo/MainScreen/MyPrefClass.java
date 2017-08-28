package com.example.unknown.findmyinfo.MainScreen;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.example.unknown.findmyinfo.R;

/**
 * Created by UNKNOWN on 7/19/2016.
 */
public class MyPrefClass extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.my_pref);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
