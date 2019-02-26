package ir.sharif.androidproject

import ir.sharif.androidproject.models.Advertisement
import ir.sharif.androidproject.models.AdvertisementType

object Advertiser {
    interface AdvertiseListener {
        fun receiveData(advertisement: Advertisement)
    }

    private val subscribers = AdvertisementType.values().map { mutableListOf<AdvertiseListener>() }

    fun subscribe(subscriber: AdvertiseListener, advertisementType: AdvertisementType) =
        subscribers[advertisementType.ordinal].add(subscriber)

    fun unSubscribe(subscriber: AdvertiseListener, advertisementType: AdvertisementType) =
        subscribers[advertisementType.ordinal].remove(subscriber)

    fun advertise(advertisement: Advertisement) =
        subscribers[advertisement.type.ordinal].forEach { it.receiveData(advertisement) }
}
