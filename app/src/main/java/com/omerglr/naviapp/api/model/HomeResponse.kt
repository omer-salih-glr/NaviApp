package com.omerglr.naviapp.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeResponse {
    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null

    @SerializedName("message")
    @Expose
    var message: Any? = null
}