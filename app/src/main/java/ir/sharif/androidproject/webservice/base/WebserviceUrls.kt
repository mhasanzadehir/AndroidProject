package ir.sharif.androidproject.webservice.base

import ir.sharif.androidproject.webservice.webservices.comments.CommentResponse
import ir.sharif.androidproject.webservice.webservices.posts.PostResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebserviceUrls {
    @GET(WebserviceAddresses.POSTS)
    fun posts(): Call<List<PostResponse>>

    @GET(WebserviceAddresses.COMMENTS)
    fun comments(@Query("postId") postId: Int): Call<List<CommentResponse>>
}
