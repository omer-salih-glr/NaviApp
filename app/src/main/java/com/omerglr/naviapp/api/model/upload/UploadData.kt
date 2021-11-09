package com.omerglr.naviapp.api.model.upload

import android.media.Image
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UploadData<T> {


    @SerializedName("images")
    @Expose
    var imagesuploadList: List<String>? = null
}