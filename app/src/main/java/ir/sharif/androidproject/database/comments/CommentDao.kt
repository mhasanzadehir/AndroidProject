package ir.sharif.androidproject.database.comments

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CommentDao {
    @Query("SELECT * FROM Post")
    fun getAll(): List<CommentBean>

    @Query("SELECT * FROM Post where title LIKE :title")
    fun findByTitle(title: String): CommentBean

    @Query("SELECT COUNT(*) from Post")
    fun countPosts(): Int

    @Insert
    fun insertAll(vararg comments: CommentBean)

    @Delete
    fun delete(user: CommentBean)

    @Query("DELETE FROM Post")
    fun nukeTable()
}