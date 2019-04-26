package ir.sharif.androidproject.database.comments

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Comment", primaryKeys = ["postId", "id"])
class CommentBean(
    @ColumnInfo(name = "postId")
    var postId: Int,
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "email")
    var email: String? = null,
    @ColumnInfo(name = "body")
    var body: String? = null
)
