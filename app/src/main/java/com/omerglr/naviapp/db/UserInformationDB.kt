package com.omerglr.naviapp.db

import android.app.Activity
import android.content.Context
import com.omerglr.naviapp.api.model.get_user_info.GetUserInfoResponse
import com.google.gson.Gson

object UserInformationDB {
    fun getUserInformation(activity: Activity): GetUserInfoResponse? {
        val asGson = activity.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
            .getString("userInfo", "")
        return if (asGson!!.length < 1) null else Gson().fromJson(
            asGson,
            GetUserInfoResponse::class.java
        )
    }

    fun setUserInformation(activity: Activity, response: GetUserInfoResponse?) {
        activity.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
            .edit()
            .putString("userInfo", Gson().toJson(response))
            .apply()
    }
}