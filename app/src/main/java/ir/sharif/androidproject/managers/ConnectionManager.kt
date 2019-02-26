package ir.sharif.androidproject.managers

import android.util.Range
import ir.sharif.androidproject.NotificationCenter
import ir.sharif.androidproject.models.Notification
import ir.sharif.androidproject.models.NotificationType

object ConnectionManager {

    val cloud = DispatchQueue("connection")

    fun load(number: Int) =
        cloud.postRunnable({
            NotificationCenter.notifySubscribers(Notification(NotificationType.DATA_LOADED, number + 1..number + 10))
        }, 100)

}
