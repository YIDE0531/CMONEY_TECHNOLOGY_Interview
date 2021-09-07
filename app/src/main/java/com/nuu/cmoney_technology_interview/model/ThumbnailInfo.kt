package com.nuu.cmoney_technology_interview.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ThumbnailInfo(
    @SerializedName("description") var description: String?,
    @SerializedName("copyright") var copyright: String?,
    @SerializedName("title") var title: String?,
    @SerializedName("url") var url: String?,
    @SerializedName("apod_site") var apod_site: String?,
    @SerializedName("date") var date: String?,
    @SerializedName("media_type") var media_type: String?,
    @SerializedName("hdurl") var hdurl: String?
): Serializable

