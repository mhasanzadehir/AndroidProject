package ir.sharif.androidproject.webservice.base

import ir.sharif.androidproject.webservice.webservices.posts.PostRequest
import ir.sharif.androidproject.webservice.webservices.posts.PostResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface WebserviceUrls {

    @GET(WebserviceAdresses.POSTS)
    fun posts(@Body request: PostRequest): Call<List<PostResponse>>

}
