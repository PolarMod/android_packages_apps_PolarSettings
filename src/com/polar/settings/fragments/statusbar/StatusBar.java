package com.polar.settings.fragments.statusbar;


import android.provider.SearchIndexableResource;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.provider.Settings;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceCategory;
import androidx.preference.SwitchPreference;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.search.Indexable;
import com.android.settingslib.search.SearchIndexable;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.polar.settings.utils.Utils;


import java.util.ArrayList;
import java.util.List;

@SearchIndexable
public class StatusBar extends SettingsPreferenceFragment implements Indexable{
   
  private SwitchPreference mShow4G;
  private SwitchPreference mOldSignalIndicator;  
  private PreferenceCategory mMobileNetworkCategory;

  @Override
  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.statusbar);
    
    PreferenceScreen mPreferences = getPreferenceScreen();
     
    mShow4G = (SwitchPreference) findPreference("show_fourg_icon");
    mOldSignalIndicator = (SwitchPreference) findPreference("use_old_mobiletype");
    mMobileNetworkCategory = (PreferenceCategory) findPreference("network_icon_customization");
    if(!Utils.isPhone(getActivity())){
      mPreferences.removePreference(mShow4G);
      mPreferences.removePreference(mOldSignalIndicator);
      mPreferences.removePreference(mMobileNetworkCategory);
    }
  }

  @Override
  public int getMetricsCategory() {
    return MetricsEvent.POLAR_SETTINGS;
  }

   public static final SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider() {
                @Override
                public List<SearchIndexableResource> getXmlResourcesToIndex(Context context,
                                                                            boolean enabled) {
                    ArrayList<SearchIndexableResource> result =
                            new ArrayList<SearchIndexableResource>();

                    SearchIndexableResource sir = new SearchIndexableResource(context);
                    sir.xmlResId = R.xml.statusbar;
                    result.add(sir);
                    return result;
                }

                @Override
                public List<String> getNonIndexableKeys(Context context) {
                    List<String> keys = super.getNonIndexableKeys(context);
		    if(!Utils.isPhone(context)){
			    keys.add("show_fourg_icon");
			    keys.add("use_old_mobiletype");
		    }
                    return keys;
                }
            };
}
