package ir.sharif.androidproject

import ir.sharif.androidproject.managers.ConnectionManager
import ir.sharif.androidproject.managers.StorageManager
import ir.sharif.androidproject.models.Advertisement
import ir.sharif.androidproject.models.AdvertisementType
import ir.sharif.androidproject.models.Item

object MessageController {
    private var last = 0

    fun fetch(fromCache: Boolean = false) {
        if (fromCache) {
            StorageManager.load(last)
            last += 10
        } else {
            ConnectionManager.load(StorageManager.readFromPref())
        }
    }

    fun onFetchComplete(data: List<Item>, fromServer: Boolean = false) {
        Advertiser.advertise(Advertisement(AdvertisementType.DATA_LOADED, data))
        if (fromServer) {
            StorageManager.writeToPref(data.last().title.toInt())
        }
    }

    fun clearList() {
        last = 0
    }
}
