package ir.sharif.androidproject.managers

import ir.sharif.androidproject.MessageController

object ConnectionManager {
    private val cloud = DispatchQueue("Connection")

    fun load(number: Int) =
        cloud.postRunnable({
            MessageController.onFetchComplete((number + 1..number + 10).toList())
        }, 100)
}
