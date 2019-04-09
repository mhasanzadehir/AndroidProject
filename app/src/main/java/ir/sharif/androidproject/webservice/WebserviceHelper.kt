package ir.sharif.androidproject.webservice

import ir.sharif.androidproject.webservice.webservices.posts.PostProcess
import ir.sharif.androidproject.webservice.webservices.posts.PostResponse
import ir.sharif.androidproject.webservice.base.WebserviceException
import java.io.IOException

object WebserviceHelper {

    @Throws(IOException::class, WebserviceException::class)
    fun getPosts(): List<PostResponse> = PostProcess().process()


}
