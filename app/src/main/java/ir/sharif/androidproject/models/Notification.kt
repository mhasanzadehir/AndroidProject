package ir.sharif.androidproject.models

data class Notification(val type: NotificationType, val content: String)

enum class NotificationType {
    DATA_LOADED,
}
