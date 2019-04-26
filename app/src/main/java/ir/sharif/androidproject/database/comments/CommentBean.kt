package ir.sharif.androidproject.database.comments

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Post")
class CommentBean(
    @PrimaryKey
    @ColumnInfo(name = "postId")
    var postId: Int,
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "email")
    var email: String? = null,
    @ColumnInfo(name = "body")
    var body: String? = null
)
