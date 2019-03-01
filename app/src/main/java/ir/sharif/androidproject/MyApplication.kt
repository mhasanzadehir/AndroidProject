package ir.sharif.androidproject

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ApplicationContext.initialize(this)
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}
