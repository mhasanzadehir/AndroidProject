package ir.sharif.androidproject.managers

import ir.sharif.androidproject.MessageController
import ir.sharif.androidproject.models.Item

object ConnectionManager {
    private val cloud = DispatchQueue("Connection")

    fun load(number: Int) =
        cloud.postRunnable({
            MessageController.onFetchComplete(((number + 1..number + 10).map { Item(it.toString(), "From Server", "") }), true)
        }, 100)
}
