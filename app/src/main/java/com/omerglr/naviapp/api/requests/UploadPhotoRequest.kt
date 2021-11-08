package com.omerglr.naviapp.api.requests

import com.omerglr.naviapp.api.model.Data
import okhttp3.MultipartBody

class UploadPhotoRequest {

    constructor(combine: String ) {
        this.combine = combine
    }

    var combine: String = "AllowForHakan"

}