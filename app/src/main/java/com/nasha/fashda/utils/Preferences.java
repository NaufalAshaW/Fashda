package com.nasha.fashda.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private static final String PREF_SESSION = "com.nasha.fashda.session";
    private static final String WALKED_STATUS = "WALKED_STATUS";

    private Context context;

    public static void setWalkedStatus(Context context, boolean status) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(WALKED_STATUS, status);
        editor.apply();
    }

    public static boolean getWalkedStatus(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);

        return preferences.getBoolean(WALKED_STATUS, false);
    }
}
