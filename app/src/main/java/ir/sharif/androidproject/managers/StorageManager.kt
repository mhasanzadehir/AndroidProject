package ir.sharif.androidproject.managers

import android.content.Context
import com.orhanobut.logger.Logger
import ir.sharif.androidproject.ApplicationContext
import java.io.FileNotFoundException

object StorageManager {
    private val storage = DispatchQueue("Storage")
    private const val filename = "StorageManager"

    fun load(): List<Int> {
        val n = readFromFile()
        return (n + 1..n + 10).toList()
    }

    fun save(n: Int) {
        writeToFile(n)
    }

    private fun readFromFile(): Int {
        return try {
            ApplicationContext.context.openFileInput(filename).read()
        } catch (e: FileNotFoundException) {
            Logger.i("No Storage File Created Yet.")
            0
        }
    }

    private fun writeToFile(n: Int) =
        ApplicationContext.context.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(byteArrayOf(n.toByte()))
        }
}
