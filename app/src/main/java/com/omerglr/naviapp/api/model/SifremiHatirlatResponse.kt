package com.omerglr.naviapp.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SifremiHatirlatResponse {
    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("code")
    @Expose
    var code: Int? = null
}