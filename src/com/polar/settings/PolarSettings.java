package com.polar.Settings;

import android.provider.SearchIndexableResource;
import android.os.Bundle;

import com.android.settings.SettingsPreferenceFragment;

import com.android.internal.logging.nano.MetricsProto;

public class PolarSettings extends SettingsPreferenceFragment{

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
  }

  @Override
  public int getMetricsCategory(){
    return MetricsProto.MetricsEvent.POLAR_SETTINGS;
  }
}
