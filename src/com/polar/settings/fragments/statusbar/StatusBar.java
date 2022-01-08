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

//TODO: make StatusBar Indexable

public class StatusBar extends SettingsPreferenceFragment{

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.statusbar);
  }

  @Override
  public int getMetricsCategory() {
    return MetricsEvent.POLAR_SETTINGS;
  }
}
