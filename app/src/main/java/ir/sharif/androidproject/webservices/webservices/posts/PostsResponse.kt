package ir.sharif.androidproject.webservices.webservices.posts

import com.google.gson.annotations.SerializedName

import ir.sharif.androidproject.webservices.base.BaseResponse
import com.google.gson.annotations.Expose


data class PostsResponse(
    @SerializedName("userId") @Expose val userId: Int? = null,
    @SerializedName("id") @Expose val id: Int? = null,
    @SerializedName("title") @Expose val title: String? = null,
    @SerializedName("body") @Expose val body: String? = null
) : BaseResponse()