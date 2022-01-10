package com.polar.settings.fragments.statusbar;


import android.provider.SearchIndexableResource;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.provider.Settings;
import android.os.Bundle;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto.MetricsEvent;

import com.polar.settings.utils;
//TODO: make StatusBar Indexable

public class StatusBar extends SettingsPreferenceFragment{

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.statusbar);
    PreferenceScreen mPreferences = getPreferenceScreen();
    
    mShow4G = (SwitchPreference) findPreference("show_fourg_icon");

    if(!Utils.isPhone(getActivity())){
      mPreferences.removePreference(mShow4G);
    }

  }

  @Override
  public int getMetricsCategory() {
    return MetricsEvent.POLAR_SETTINGS;
  }
}
