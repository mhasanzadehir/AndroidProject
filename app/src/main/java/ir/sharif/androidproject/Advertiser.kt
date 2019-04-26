package ir.sharif.androidproject

import ir.sharif.androidproject.models.Advertisement
import ir.sharif.androidproject.models.AdvertisementType

object Advertiser {
    interface AdvertiseListener<T> {
        fun receiveData(advertisement: Advertisement<T>)
    }

    private val subscribers =
        AdvertisementType.values().map { mutableListOf<AdvertiseListener<Any>>() }

    fun <T> subscribe(subscriber: AdvertiseListener<T>, advertisementType: AdvertisementType) =
        selectSubscribers<T>(advertisementType).add(subscriber)

    fun <T> unSubscribe(subscriber: AdvertiseListener<T>, advertisementType: AdvertisementType) =
        selectSubscribers<T>(advertisementType).remove(subscriber)

    fun <T> advertise(advertisement: Advertisement<T>) =
        selectSubscribers<T>(advertisement.type).forEach { it.receiveData(advertisement) }

    private fun <T> selectSubscribers(advertisementType: AdvertisementType) =
        (subscribers[advertisementType.ordinal] as MutableList<AdvertiseListener<T>>)
}
