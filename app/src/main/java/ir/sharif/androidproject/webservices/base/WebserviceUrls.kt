package ir.sharif.androidproject.webservices.base

import ir.sharif.androidproject.webservices.webservices.posts.PostsRequest
import ir.sharif.androidproject.webservices.webservices.posts.PostsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface WebserviceUrls {

    @GET(WebserviceAdresses.POSTS)
    fun posts(@Body request: PostsRequest): Call<List<PostsResponse>>

}
