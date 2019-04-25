package ir.sharif.androidproject

import android.app.Application
import androidx.room.Room
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import ir.sharif.androidproject.database.AppDatabase

class MyApplication : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        ApplicationContext.initialize(this)
        Logger.addLogAdapter(AndroidLogAdapter())
        database = Room.databaseBuilder(ApplicationContext.context, AppDatabase::class.java, "db").build()
    }
}
