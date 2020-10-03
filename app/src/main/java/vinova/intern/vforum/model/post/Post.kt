package vinova.intern.vforum.model.post

import com.google.gson.annotations.SerializedName
import vinova.intern.vforum.model.comment.Comment

data class Post(
    @SerializedName("title")
    val title: String,
    @SerializedName("commentsPost")
    val commentsPost: ArrayList<Comment>?,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("createdBy")
    val createdBy: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("countLike")
    val countLike: Int,
    @SerializedName("countCommentPost")
    val countCommentPost: Int
)