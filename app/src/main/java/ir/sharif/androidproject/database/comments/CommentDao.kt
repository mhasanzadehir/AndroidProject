package ir.sharif.androidproject.database.comments

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CommentDao {
    @Query("SELECT * FROM Comment")
    fun getAll(): List<CommentBean>

    @Query("SELECT * FROM Comment WHERE Comment.postId = :postId")
    fun getPostComments(postId: Int): List<CommentBean>

    @Query("SELECT COUNT(*) from Comment")
    fun countComments(): Int

    @Insert
    fun insertAll(vararg comments: CommentBean)

    @Query("DELETE FROM Comment WHERE Comment.postId = :postId")
    fun deletePostComments(postId: Int)

    @Query("DELETE FROM Comment")
    fun nukeTable()
}
