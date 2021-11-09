package com.omerglr.naviapp.api.model.get_user_info

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class GetUserInfoResponse {
    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}