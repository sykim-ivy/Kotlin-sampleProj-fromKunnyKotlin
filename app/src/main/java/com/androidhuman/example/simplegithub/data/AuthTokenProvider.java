package com.androidhuman.example.simplegithub.data;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * access token을 SharedPreferences에 저장하고 제공하는 클래스
 */
public final class AuthTokenProvider {
    private static final String KEY_AUTH_TOKEN = "auth_token";

    private Context context;

    public AuthTokenProvider(@Nullable Context context) {
        this.context = context;
    }

    // SharedPreferences에 access token을 저장
    public void updateToken(@NonNull String token) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(KEY_AUTH_TOKEN, token)
                .apply();
    }

    // SharedPreferences에 저장된 access token을 반환
    // 저장된 access token이 없는 경우 null값을 반환
    @Nullable
    public String getToken() {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(KEY_AUTH_TOKEN, null);
    }

}
