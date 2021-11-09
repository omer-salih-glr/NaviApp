package com.omerglr.naviapp.api.model.get_user_info

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Data {
    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("uuid")
    @Expose
    var uuid: String? = null

    @SerializedName("images")
    @Expose
    var images: List<Any>? = null

    @SerializedName("city_name")
    @Expose
    var cityName: String? = null

    @SerializedName("is_online")
    @Expose
    var isOnline: Int? = null

    @SerializedName("birthday")
    @Expose
    var birthday: String? = null

    @SerializedName("gender")
    @Expose
    var gender: Int? = null

    @SerializedName("ban")
    @Expose
    var ban: Any? = null

    @SerializedName("balanceAmonunt")
    @Expose
    var balanceAmonunt: Int? = null

    @SerializedName("social_login")
    @Expose
    var socialLogin: Boolean? = null

    @SerializedName("spams")
    @Expose
    var spams: List<Any>? = null

    @SerializedName("calls")
    @Expose
    var calls: List<Any>? = null

    @SerializedName("free_kiss")
    @Expose
    var freeKiss: Boolean? = null

    @SerializedName("detail")
    @Expose
    var detail: Any? = null
}