package ir.sharif.androidproject.repository

import ir.sharif.androidproject.model.Item

/**
 *  @Author: MahdiHS
 *  @Date:   24/02/2019
 */

val USERS = arrayListOf(
    Item("Mahdi Hasanzadeh", "Hi Bro!", ""),
    Item("Alireza Fatemi Jahromi", "I miss you...", ""),
    Item("Reza Zeiny", ":((((", "")
)

object DataRepository {
    fun fetchData() = USERS

    fun generateNewData() {
        for (i in USERS.size..USERS.size + 10) USERS.add(Item("Title$i", "SubTitle$i", ""))
        USERS
    }
}