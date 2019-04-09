package ir.sharif.androidproject.database.posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Post")
class PostBean(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String? = null,
    @ColumnInfo(name = "userId")
    val userId: String? = null,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "body")
    val body: String? = null
)