package ir.sharif.androidproject.webservices.webservices.posts

import java.io.IOException

import ir.sharif.androidproject.webservices.base.BaseProcess
import ir.sharif.androidproject.webservices.base.MyRetrofit
import ir.sharif.androidproject.webservices.base.WebserviceException

class PostsProcess : BaseProcess() {
    private val request: PostsRequest = PostsRequest()

    @Throws(IOException::class, WebserviceException::class)
    override fun process() = send(MyRetrofit.webserviceUrls.posts(request))
}
