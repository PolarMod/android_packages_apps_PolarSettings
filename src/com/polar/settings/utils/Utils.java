package com.polar.settings.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class Utils{
  public static boolean isPhone(Context context){
    TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    if(mTelephony == null){
      return false;
    }
    return telephony.isVoiceCapable();
  }
}
