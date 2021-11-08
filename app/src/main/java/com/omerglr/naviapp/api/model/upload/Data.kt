package com.omerglr.naviapp.api.model.upload

import android.media.Image
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {


    @SerializedName("images")
    @Expose
    var images: List<String>? = null
}