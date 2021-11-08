package com.omerglr.naviapp.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("uuid")
    @Expose
    var uuid: String? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("birthday")
    @Expose
    var birthday: String? = null

    @SerializedName("city_name")
    @Expose
    var cityName: String? = null

    @SerializedName("images")
    @Expose
    var images: List<String>? = null

    @SerializedName("is_online")
    @Expose
    var isOnline: Int? = null

    @SerializedName("gender")
    @Expose
    var gender: Int? = null

    @SerializedName("distance")
    @Expose
    var distance: Float? = null

    @SerializedName("matched")
    @Expose
    var matched: Int? = null
}