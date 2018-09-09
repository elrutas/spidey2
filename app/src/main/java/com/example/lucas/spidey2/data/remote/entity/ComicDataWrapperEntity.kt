package com.example.lucas.spidey2.data.remote.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ComicDataWrapperEntity {

    @SerializedName("code")
    @Expose
    var code: String? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("copyright")
    @Expose
    var copyright: String? = null
    @SerializedName("attributionText")
    @Expose
    var attributionText: String? = null
    @SerializedName("attributionHTML")
    @Expose
    var attributionHTML: String? = null
    @SerializedName("dataEntity")
    @Expose
    var dataEntity: ComicDataContainerEntity? = null
    @SerializedName("etag")
    @Expose
    var etag: String? = null

}