package com.omerglr.naviapp.api.model.get_user_info

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class GetUserInfoResponse {
    @SerializedName("status")
    @Expose
    var status:Boolean = true;

    @SerializedName("data")
    @Expose
    lateinit var data: Data;

    @SerializedName("message")
    @Expose
    lateinit var message: String;
}