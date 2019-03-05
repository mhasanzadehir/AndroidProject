package ir.sharif.androidproject

import com.orhanobut.logger.Logger
import ir.sharif.androidproject.managers.ConnectionManager
import ir.sharif.androidproject.managers.StorageManager
import ir.sharif.androidproject.models.Advertisement
import ir.sharif.androidproject.models.AdvertisementType

object MessageController {
    private var last = 0

    fun fetch(fromCache: Boolean = false) {
        if (fromCache) {
            StorageManager.load(last)
            last += 10
        } else {
            Logger.i("Read From File::" + StorageManager.readFromPref())
            ConnectionManager.load(StorageManager.readFromPref())
        }
    }

    fun onFetchComplete(data: List<Int>, fromServer: Boolean = false) {
        if (fromServer) {
            Advertiser.advertise(Advertisement(AdvertisementType.DATA_LOADED, (1..data.last()).toList()))
            StorageManager.writeToPref(data.last())
            Logger.i("Storage Manager::" + data.last())
        }
        else {
            Advertiser.advertise(Advertisement(AdvertisementType.DATA_LOADED, data))
        }
    }

    fun clearList() {
        last = 0
    }
}
