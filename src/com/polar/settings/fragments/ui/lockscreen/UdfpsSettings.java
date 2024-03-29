/*
 * Copyright (C) 2022 crDroid Android Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.polar.settings.fragments.ui.lockscreen;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.hardware.fingerprint.FingerprintManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;

import androidx.preference.ListPreference;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;

import com.android.internal.logging.nano.MetricsProto;
import com.android.internal.util.polar.Utils;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class UdfpsSettings extends SettingsPreferenceFragment {

    private static final String UDFPS_ICON_PICKER = "udfps_icon_picker";
    private static final String UDFPS_ANIM_PREVIEW = "udfps_recognizing_animation_preview";
    private static final String TAG = "UdfpsSettings";

    private Preference mUdfpsIconPicker;
    private Preference mUdfpsAnimPreview;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.udfps_settings);

        final PreferenceScreen prefSet = getPreferenceScreen();
        Resources resources = getResources();

        final boolean udfpsResPkgInstalled = Utils.isPackageInstalled(getContext(),
                "com.polar.udfps.animations");
        mUdfpsIconPicker = findPreference(UDFPS_ICON_PICKER);
        mUdfpsAnimPreview = findPreference(UDFPS_ANIM_PREVIEW);
        if (!udfpsResPkgInstalled) {
            Log.d(TAG, "Required packages is not installed, but not removing preference due to hotfix"); 
	    prefSet.removePreference(mUdfpsIconPicker);
            prefSet.removePreference(mUdfpsAnimPreview);
        }
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.POLAR_SETTINGS;
    }
}
