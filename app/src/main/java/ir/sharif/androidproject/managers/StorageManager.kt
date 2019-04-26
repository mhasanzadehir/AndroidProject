package ir.sharif.androidproject.managers

import android.preference.PreferenceManager
import ir.sharif.androidproject.Advertiser
import ir.sharif.androidproject.ApplicationContext
import ir.sharif.androidproject.MessageController
import ir.sharif.androidproject.MyApplication
import ir.sharif.androidproject.models.Advertisement
import ir.sharif.androidproject.models.AdvertisementType
import ir.sharif.androidproject.models.Item
import ir.sharif.androidproject.webservice.webservices.posts.PostResponse

object StorageManager {
    private val storage = DispatchQueue("Storage")
    private val prefs = PreferenceManager.getDefaultSharedPreferences(ApplicationContext.context)

    fun loadPosts() {
        storage.postRunnable {
            val posts = MyApplication.database.postDao().getAll().map { postBean ->
                with(postBean) {
                    PostResponse(userId, id, title, body)
                }
            }
            Advertiser.advertise(Advertisement(AdvertisementType.POSTS_LOADED, posts))
        }
    }

    fun loadComments(postId: Int) {
        storage.postRunnable {
            val posts = MyApplication.database.commentDao().getPostComments(postId)
            Advertiser.advertise(Advertisement(AdvertisementType.POSTS_LOADED, posts))
        }
    }


    fun load(last: Int) =
        storage.postRunnable({
            val n = readFromPref()
            if (last >= n) {
                MessageController.onFetchComplete(emptyList(), false)
            } else {
                MessageController.onFetchComplete(
                    (last + 1..last + 10).map { Item(it.toString(), "From Refresh", "") },
                    false
                )
            }

        }, 0)

    fun readFromPref(): Int = prefs.getInt("data", 0)

    fun writeToPref(n: Int) = prefs.edit().putInt("data", n).apply()
}
