package ir.sharif.androidproject.managers

import android.preference.PreferenceManager
import ir.sharif.androidproject.ApplicationContext
import ir.sharif.androidproject.MessageController
import ir.sharif.androidproject.models.Item

object StorageManager {
    private val storage = DispatchQueue("Storage")
    private val prefs = PreferenceManager.getDefaultSharedPreferences(ApplicationContext.context)

    fun load(last: Int) =
        storage.postRunnable({
            val n = readFromPref()
            if (last >= n) {
                MessageController.onFetchComplete(emptyList(), false)
            } else {
                MessageController.onFetchComplete(
                    (last + 1..last + 10).map { Item(it.toString(), "From Refresh", "") },
                    false
                )
            }

        }, 0)

    fun readFromPref(): Int = prefs.getInt("data", 0)

    fun writeToPref(n: Int) = prefs.edit().putInt("data", n).apply()
}
