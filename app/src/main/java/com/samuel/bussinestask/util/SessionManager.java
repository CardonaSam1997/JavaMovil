package com.samuel.bussinestask.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "auth_prefs";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_ROLE = "role";
    private static final String KEY_ID = "user_id"; // ❌ no Integer

    private SharedPreferences prefs;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveSession(int id, String token, String role) {
        prefs.edit()
                .putInt(KEY_ID, id)
                .putString(KEY_TOKEN, token)
                .putString(KEY_ROLE, role)
                .apply();
    }

    public int getUserId() {
        return prefs.getInt(KEY_ID, -1);
    }

    public String getToken() {
        return prefs.getString(KEY_TOKEN, null);
    }

    public String getRole() {
        return prefs.getString(KEY_ROLE, null);
    }

    public boolean isLoggedIn() {
        return getToken() != null;
    }

    public void logout() {
        prefs.edit().clear().apply();
    }
}