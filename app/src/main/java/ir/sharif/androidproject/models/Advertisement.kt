package ir.sharif.androidproject.models

data class Advertisement(val type: AdvertisementType, val data: List<Int>)

enum class AdvertisementType {
    DATA_LOADED,
}
