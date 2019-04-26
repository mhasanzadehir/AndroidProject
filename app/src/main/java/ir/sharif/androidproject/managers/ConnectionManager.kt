package ir.sharif.androidproject.managers

import ir.sharif.androidproject.Advertiser
import ir.sharif.androidproject.MessageController
import ir.sharif.androidproject.models.Advertisement
import ir.sharif.androidproject.models.AdvertisementType
import ir.sharif.androidproject.models.Item
import ir.sharif.androidproject.webservice.WebserviceHelper

object ConnectionManager {
    private val cloud = DispatchQueue("Connection")

    fun loadPosts() {
        cloud.postRunnable {
            val posts = WebserviceHelper.getPosts()
            Advertiser.advertise(Advertisement(AdvertisementType.POSTS_LOADED, posts))
        }
    }

    fun loadComments(postId: Int) {
        cloud.postRunnable {
            val comments = WebserviceHelper.getComments(postId)
            Advertiser.advertise(Advertisement(AdvertisementType.COMMENTS_LOADED, comments))
        }
    }

    fun load(number: Int) =
        cloud.postRunnable({
            MessageController.onFetchComplete(((number + 1..number + 10).map {
                Item(
                    it.toString(),
                    "From Server",
                    ""
                )
            }), true)
        }, 3000)
}
