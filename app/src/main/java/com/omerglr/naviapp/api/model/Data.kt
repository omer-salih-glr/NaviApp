package com.omerglr.naviapp.api.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Data {
    @SerializedName("user")
    @Expose
    var user: User? = null

    @SerializedName("access_token")
    @Expose
    var accessToken: String? = null
}