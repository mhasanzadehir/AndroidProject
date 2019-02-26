package ir.sharif.androidproject

import ir.sharif.androidproject.models.Notification
import ir.sharif.androidproject.models.NotificationType

object NotificationCenter {
    interface NotificationCenterDelegate {
        fun receiveData(notification: Notification)
    }

    private val subscribers = NotificationType.values().map { mutableListOf<NotificationCenterDelegate>() }

    fun subscribe(subscriber: NotificationCenterDelegate, notificationType: NotificationType) =
        subscribers[notificationType.ordinal].add(subscriber)

    fun unSubscribe(subscriber: NotificationCenterDelegate, notificationType: NotificationType) =
        subscribers[notificationType.ordinal].remove(subscriber)

    fun notifySubscribers(notification: Notification) =
        subscribers[notification.type.ordinal].forEach { it.receiveData(notification) }
}
