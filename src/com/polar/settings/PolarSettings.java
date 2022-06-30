package com.polar.settings;

import android.provider.SearchIndexableResource;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.provider.Settings;
import android.os.Bundle;

import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.R;

import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.search.Indexable;
import com.android.settingslib.search.SearchIndexable;

import com.android.internal.logging.nano.MetricsProto;


import java.util.ArrayList;
import java.util.List;

@SearchIndexable
public class PolarSettings extends SettingsPreferenceFragment implements Indexable{

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
   public static final SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider() {
                @Override
                public List<SearchIndexableResource> getXmlResourcesToIndex(Context context,
                                                                            boolean enabled) {
                    ArrayList<SearchIndexableResource> result =
                            new ArrayList<SearchIndexableResource>();

                    SearchIndexableResource sir = new SearchIndexableResource(context);
                    sir.xmlResId = R.xml.main_menu;
                    result.add(sir);
                    return result;
                }

                @Override
                public List<String> getNonIndexableKeys(Context context) {
                    List<String> keys = super.getNonIndexableKeys(context);
                    return keys;
                }
            };
  
}
