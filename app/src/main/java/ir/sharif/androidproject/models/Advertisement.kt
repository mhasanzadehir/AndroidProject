package ir.sharif.androidproject.models

data class Advertisement<T>(val type: AdvertisementType, val data: T)

enum class AdvertisementType {
    DATA_LOADED,
    POSTS_LOADED;
}
