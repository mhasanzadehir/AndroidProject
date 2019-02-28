package ir.sharif.androidproject

import android.annotation.SuppressLint
import android.content.Context

/**
 *  @Author: MahdiHS
 *  @Date:   28/02/2019
 */
@SuppressLint("StaticFieldLeak")
object ApplicationContext {

    lateinit var context: Context

    fun initialize(context: Context){
        this.context = context
    }
}