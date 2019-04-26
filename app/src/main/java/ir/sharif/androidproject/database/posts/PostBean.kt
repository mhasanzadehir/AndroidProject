package ir.sharif.androidproject.database.posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Post")
class PostBean(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "userId")
    var userId: Int,
    @ColumnInfo(name = "title")
    var title: String? = null,
    @ColumnInfo(name = "body")
    var body: String? = null
)
