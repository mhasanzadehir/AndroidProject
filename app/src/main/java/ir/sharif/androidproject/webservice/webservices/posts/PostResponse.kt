package ir.sharif.androidproject.webservice.webservices.posts

import com.google.gson.annotations.SerializedName

import ir.sharif.androidproject.webservice.base.BaseResponse
import com.google.gson.annotations.Expose


data class PostResponse(
    @SerializedName("userId") @Expose val userId: Int? = null,
    @SerializedName("id") @Expose val id: Int? = null,
    @SerializedName("title") @Expose val title: String? = null,
    @SerializedName("body") @Expose val body: String? = null
) : BaseResponse()