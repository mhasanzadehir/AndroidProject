package ir.sharif.androidproject

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object ApplicationContext {
    @Volatile
    lateinit var context: Context

    fun initialize(context: Context) {
        this.context = context
    }
}
