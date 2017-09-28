package com.odn.kotlinretrofi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by EDGAR ARANA on 03/07/2017.
 */

class ImageMeme(
        @SerializedName("id")
        @Expose
        var id: String?,
        @SerializedName("description")
        @Expose
        var desc: String?,
        @SerializedName("vote")
        @Expose
        var vote: String?,
        @SerializedName("timestamp")
        @Expose
        var timestamp: String?,
        @SerializedName("type")
        @Expose
        var type: Int
) {
    @SerializedName("url")
    @Expose
    var url: String? = null
}