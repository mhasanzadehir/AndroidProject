package ir.sharif.androidproject.webservice.webservices.comments

import com.google.gson.annotations.SerializedName
import ir.sharif.androidproject.webservice.base.BaseResponse
import com.google.gson.annotations.Expose

data class CommentResponse(
    @SerializedName("postId") @Expose val postId: Int? = null,
    @SerializedName("id") @Expose val id: Int? = null,
    @SerializedName("name") @Expose val name: String? = null,
    @SerializedName("email") @Expose val email: String? = null,
    @SerializedName("body") @Expose val body: String? = null
) : BaseResponse()
