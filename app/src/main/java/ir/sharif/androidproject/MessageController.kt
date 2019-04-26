package ir.sharif.androidproject

import com.orhanobut.logger.Logger
import com.soywiz.klock.DateTime
import com.soywiz.klock.minutes
import ir.sharif.androidproject.managers.ConnectionManager
import ir.sharif.androidproject.managers.StorageManager
import ir.sharif.androidproject.models.Advertisement
import ir.sharif.androidproject.models.AdvertisementType
import ir.sharif.androidproject.models.Item
import java.util.*

object MessageController {
    private var last = 0
    private var lastPostFetchTime: DateTime? = null
    private val lastCommentFetchTimes = mutableListOf<Pair<Int, DateTime>>()

    fun fetchPosts() {
        if (lastPostFetchTime == null || (lastPostFetchTime!! + 5.minutes > DateTime.now())) {
            Logger.i("Loading posts from server.")
            ConnectionManager.loadPosts()
            lastPostFetchTime = DateTime.now()
        } else {
            Logger.i("Loading posts from storage.")
            StorageManager.loadPosts()
        }
    }

    fun fetchComments(postId: Int) {
        ConnectionManager.loadComments(postId)
    }

    private fun findLastCommentFetchTime(postId: Int) =
        lastCommentFetchTimes.firstOrNull { pair -> pair.first == postId }


    fun fetch(fromCache: Boolean = false) {
        if (fromCache) {
            StorageManager.load(last)
            last += 10
        } else {
            ConnectionManager.load(StorageManager.readFromPref())
        }
    }

    fun onFetchComplete(data: List<Item>, fromServer: Boolean = false) {
        Advertiser.advertise(Advertisement(AdvertisementType.DATA_LOADED, data))
        if (fromServer) {
            StorageManager.writeToPref(data.last().title.toInt())
        }
    }

    fun clearList() {
        last = 0
    }
}
