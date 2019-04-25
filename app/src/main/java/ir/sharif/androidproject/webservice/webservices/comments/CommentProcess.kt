package ir.sharif.androidproject.webservice.webservices.comments

import java.io.IOException

import ir.sharif.androidproject.webservice.base.BaseProcess
import ir.sharif.androidproject.webservice.base.MyRetrofit
import ir.sharif.androidproject.webservice.base.WebserviceException

class CommentProcess(private val postId: Int) : BaseProcess() {
    @Throws(IOException::class, WebserviceException::class)
    override fun process() = send(MyRetrofit.webserviceUrls.comments(postId))
}
