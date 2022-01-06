package com.polar.settings;

import android.provider.SearchIndexableResource;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.provider.Settings;
import android.os.Bundle;

import com.android.settings.SettingsPreferenceFragment;

import com.android.internal.logging.nano.MetricsProto;

public class PolarSettings extends SettingsPreferenceFragment{

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.main_menu);
    setHasOptionsMenu(true);
  }

  @Override
  public int getMetricsCategory(){
    return MetricsProto.MetricsEvent.POLAR_SETTINGS;
  }

  //TODO: Make PolarSettings Indexable
}
