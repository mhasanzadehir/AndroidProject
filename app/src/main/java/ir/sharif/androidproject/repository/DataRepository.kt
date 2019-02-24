package ir.sharif.androidproject.repository

import ir.sharif.androidproject.model.Item

/**
 *  @Author: MahdiHS
 *  @Date:   24/02/2019
 */

val INITIAL_DATA = arrayListOf(
    Item("Mahdi Hasanzadeh", "Hi Bro!", ""),
    Item("Alireza Fatemi Jahromi", "I miss you...", ""),
    Item("Reza Zeiny", ":((((", "")
)

object DataRepository {
    private var list = ArrayList<Item>()

    fun fetchNewData(): ArrayList<Item> {
        val newData = ArrayList<Item>()
        for (i in list.size..list.size + 10) {
            newData.add(Item("Title$i", "SubTitle$i", ""))
        }
        list.addAll(newData)
        return newData
    }

    fun getData() = list

    fun clear() = list.clear()

    fun refresh() {
        list = INITIAL_DATA
    }
}