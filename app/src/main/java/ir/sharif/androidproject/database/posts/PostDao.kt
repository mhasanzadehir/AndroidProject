package ir.sharif.androidproject.database.posts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDao {
    @Query("SELECT * FROM Post")
    fun getAll(): List<PostBean>

    @Query("SELECT * FROM Post where title LIKE :title")
    fun findByTitle(title: String): PostBean

    @Query("SELECT COUNT(*) from Post")
    fun countPosts(): Int

    @Insert
    fun insertAll(vararg posts: PostBean)

    @Delete
    fun delete(user: PostBean)

    @Query("DELETE FROM Post")
    fun nukeTable()
}
