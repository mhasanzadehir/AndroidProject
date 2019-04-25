package ir.sharif.androidproject.webservice

import ir.sharif.androidproject.webservice.webservices.posts.PostProcess
import ir.sharif.androidproject.webservice.webservices.posts.PostResponse
import ir.sharif.androidproject.webservice.base.WebserviceException
import ir.sharif.androidproject.webservice.webservices.comments.CommentProcess
import ir.sharif.androidproject.webservice.webservices.comments.CommentResponse
import java.io.IOException

object WebserviceHelper {
    @Throws(IOException::class, WebserviceException::class)
    fun getPosts(): List<PostResponse> = PostProcess().process()

    @Throws(IOException::class, WebserviceException::class)
    fun getComments(postId: Int): List<CommentResponse> = CommentProcess(postId).process()
}
