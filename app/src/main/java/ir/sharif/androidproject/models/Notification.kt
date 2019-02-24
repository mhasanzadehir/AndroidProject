package ir.sharif.androidproject.models

data class Notification(val type: NotificationType)

enum class NotificationType {
    DATA_LOADED,
}
