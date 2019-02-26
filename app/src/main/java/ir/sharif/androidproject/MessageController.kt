package ir.sharif.androidproject

import ir.sharif.androidproject.managers.ConnectionManager
import ir.sharif.androidproject.managers.StorageManager
import ir.sharif.androidproject.models.Advertisement
import ir.sharif.androidproject.models.AdvertisementType

object MessageController {
    private var cache = mutableListOf<Int>()

    fun fetch(fromCache: Boolean = false) {
        if (fromCache) {
            StorageManager.load()
        } else {
            ConnectionManager.load(cache.last())
        }
    }

    fun onFetchComplete(data: List<Int>) {
        Advertiser.advertise(Advertisement(AdvertisementType.DATA_LOADED, data))
    }
}
