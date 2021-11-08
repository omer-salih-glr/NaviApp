package com.omerglr.naviapp.api.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class User {
    @SerializedName("uuid")
    @Expose
    var uuid: String? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("lat")
    @Expose
    var lat: String? = null

    @SerializedName("long")
    @Expose
    var long: String? = null

    @SerializedName("lang")
    @Expose
    var lang: String? = null

    @SerializedName("city_name")
    @Expose
    var cityName: String? = null

    @SerializedName("images")
    @Expose
    var images: List<Any>? = null

    @SerializedName("birthday")
    @Expose
    var birthday: String? = null

    @SerializedName("timezone")
    @Expose
    var timezone: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null
}