package ir.sharif.androidproject.webservices

import ir.sharif.androidproject.webservices.webservices.posts.PostsProcess
import ir.sharif.androidproject.webservices.webservices.posts.PostsResponse
import ir.sharif.vamdeh.webservices.base.WebserviceException
import java.io.IOException

object WebserviceHelper {

    @Throws(IOException::class, WebserviceException::class)
    fun getPosts(): List<PostsResponse> = PostsProcess().process()


}
