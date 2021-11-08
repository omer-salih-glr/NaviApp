package com.omerglr.naviapp.utils

import android.app.Activity
import android.content.Context

object AccessTokenUtils {
    fun setAccessToken(activity: Activity, token: String) {
        activity.getSharedPreferences("Auth", Context.MODE_PRIVATE).edit().putString("Token", token)
            .apply()
    }

    fun getAccessToken(activity: Activity): String {
        return activity.getSharedPreferences("Auth", Context.MODE_PRIVATE).getString("Token", "").toString();
    }
}