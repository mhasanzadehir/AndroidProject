package ir.sharif.androidproject.models

import android.util.Range

data class Notification(val type: NotificationType, val data: IntRange)

enum class NotificationType {
    DATA_LOADED,
}
