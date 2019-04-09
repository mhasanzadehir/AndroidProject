package ir.sharif.androidproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.sharif.androidproject.database.posts.PostBean
import ir.sharif.androidproject.database.posts.PostDao


@Database(entities = [PostBean::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}