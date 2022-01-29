package com.polar.settings.fragments.ui;


import android.provider.SearchIndexableResource;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.provider.Settings;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;

import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceCategory;
import androidx.preference.SwitchPreference;
import androidx.preference.ListPreference;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto.MetricsEvent;

import java.util.List;
import java.util.ArrayList;

//TODO: make UI Indexable

public class UI extends SettingsPreferenceFragment{
  private static final String TAG = "Settings.PolarSettings.fragments.UI";

  private static final String WEATHER_ICON_PACK = "weather_icon_pack";
  private static final String DEFAULT_WEATHER_ICON_PACKAGE = "org.omnirom.omnijaws";
  private static final String DEFAULT_WEATHER_ICON_PREFIX = "outline";
  private static final String WEATHER_SERVICE_PACKAGE = "org.omnirom.omnijaws";
  private static final String CHRONUS_ICON_PACK_INTENT = "com.dvtonder.chronus.ICON_PACK";
  private PreferenceCategory mWeatherCategory;
  private ListPreference mWeatherIconPack;

  @Override
  public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ui);
        final PreferenceScreen prefScreen = getPreferenceScreen();

        String settingHeaderPackage = Settings.System.getString(getContentResolver(),
                    Settings.System.OMNIJAWS_WEATHER_ICON_PACK);
        if (settingHeaderPackage == null) {
              settingHeaderPackage = DEFAULT_WEATHER_ICON_PACKAGE + "." + DEFAULT_WEATHER_ICON_PREFIX;
        }
        mWeatherIconPack = (ListPreference) findPreference(WEATHER_ICON_PACK);

        List<String> entries = new ArrayList<String>();
        List<String> values = new ArrayList<String>();
        getAvailableWeatherIconPacks(entries, values);
        mWeatherIconPack.setEntries(entries.toArray(new String[entries.size()]));
        mWeatherIconPack.setEntryValues(values.toArray(new String[values.size()]));

        int valueIndex = mWeatherIconPack.findIndexOfValue(settingHeaderPackage);
        if (valueIndex == -1) {
                // no longer found
            settingHeaderPackage = DEFAULT_WEATHER_ICON_PACKAGE + "." + DEFAULT_WEATHER_ICON_PREFIX;
            Settings.System.putString(getContentResolver(),
                        Settings.System.OMNIJAWS_WEATHER_ICON_PACK, settingHeaderPackage);
            valueIndex = mWeatherIconPack.findIndexOfValue(settingHeaderPackage);
        }
        mWeatherIconPack.setValueIndex(valueIndex >= 0 ? valueIndex : 0);
        mWeatherIconPack.setSummary(mWeatherIconPack.getEntry());
        mWeatherIconPack.setOnPreferenceChangeListener(this);
   }

  private void getAvailableWeatherIconPacks(List<String> entries, List<String> values) {
        Intent i = new Intent();
        PackageManager packageManager = getPackageManager();
        i.setAction("org.omnirom.WeatherIconPack");
        for (ResolveInfo r : packageManager.queryIntentActivities(i, 0)) {
            String packageName = r.activityInfo.packageName;
            if (packageName.equals(DEFAULT_WEATHER_ICON_PACKAGE)) {
                values.add(0, r.activityInfo.name);
            } else {
                values.add(r.activityInfo.name);
            }
            String label = r.activityInfo.loadLabel(getPackageManager()).toString();
            if (label == null) {
                label = r.activityInfo.packageName;
            }
            if (packageName.equals(DEFAULT_WEATHER_ICON_PACKAGE)) {
                entries.add(0, label);
            } else {
                entries.add(label);
            }
        }
        i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(CHRONUS_ICON_PACK_INTENT);
        for (ResolveInfo r : packageManager.queryIntentActivities(i, 0)) {
            String packageName = r.activityInfo.packageName;
            values.add(packageName + ".weather");
            String label = r.activityInfo.loadLabel(getPackageManager()).toString();
            if (label == null) {
                label = r.activityInfo.packageName;
            }
            entries.add(label);
        }
    }

  public boolean onPreferenceChange(Preference preference, Object objValue) {
        if (preference == mWeatherIconPack) {
            String value = (String) objValue;
            Settings.System.putString(getContentResolver(),
                    Settings.System.OMNIJAWS_WEATHER_ICON_PACK, value);
            int valueIndex = mWeatherIconPack.findIndexOfValue(value);
            mWeatherIconPack.setSummary(mWeatherIconPack.getEntries()[valueIndex]);
        }
        return true;
    }

  private boolean isOmniJawsEnabled() {
        final Uri SETTINGS_URI
            = Uri.parse("content://org.omnirom.omnijaws.provider/settings");

        final String[] SETTINGS_PROJECTION = new String[] {
            "enabled"
        };

        final Cursor c = getContentResolver().query(SETTINGS_URI, SETTINGS_PROJECTION,
                null, null, null);
        if (c != null) {
            int count = c.getCount();
            if (count == 1) {
                c.moveToPosition(0);
                boolean enabled = c.getInt(0) == 1;
                return enabled;
            }
        }
        return true;
  }
  @Override
  public int getMetricsCategory() {
    return MetricsEvent.POLAR_SETTINGS;
  }
}
