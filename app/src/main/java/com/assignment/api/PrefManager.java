package com.assignment.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

public class PrefManager {
//    SharedPreferences pref;
//    SharedPreferences.Editor editor;
    Set<String> strings;
    private Context _context;
    private static final String LOGGED_IN_USER = "LOGGED_IN_USER";
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "androidhive-welcome";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this._context = context;
//        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
//        editor = pref.edit();
    }



//    public void setFirstTimeLaunch(boolean isFirstTime) {
//        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
//        editor.commit();
//    }

    public Context getContext() {
        return _context;
    }

    public void setContext(Context context) {
        this._context = context;
    }

    protected SharedPreferences getSharedPreferences(String key) {
        return PreferenceManager.getDefaultSharedPreferences(_context);
    }

    private String getString(String key, String def) {
        SharedPreferences prefs = getSharedPreferences(key);
        return prefs.getString(key, def);
    }

    public void setString(String key, String val) {
        SharedPreferences prefs = getSharedPreferences(key);
        SharedPreferences.Editor e = prefs.edit();
        e.putString(key, val);
        e.apply();
    }

    private boolean getBoolean(String key, boolean def) {
        SharedPreferences prefs = getSharedPreferences(key);
        boolean b = prefs.getBoolean(key, def);
        return b;
    }


//    public boolean isFirstTimeLaunch() {
//        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
//    }

    private void setBoolean(String key, boolean val) {
        SharedPreferences prefs = getSharedPreferences(key);
        SharedPreferences.Editor e = prefs.edit();
        e.putBoolean(key, val);
        e.apply();
    }

    public boolean isLoggedInUser() {
        String json = getString(LOGGED_IN_USER, null);
        return json != null;
    }

    public void logOutUser() {
        SharedPreferences prefs = getSharedPreferences(LOGGED_IN_USER);
        SharedPreferences.Editor e = prefs.edit();
        e.clear();
        e.apply();
    }


    private Set<String> getStringSet(String key, Set<String> def) {
        SharedPreferences prefs = getSharedPreferences(key);
        return prefs.getStringSet(key, def);
    }

    public void setStringSet(String key, Set<String> val) {
        SharedPreferences prefs = getSharedPreferences(key);
        SharedPreferences.Editor e = prefs.edit();
        e.putStringSet(key, val);
        e.apply();
    }

}