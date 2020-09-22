package vinova.intern.vforum.model.comment

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("status")
    val title: String,
    @SerializedName("postId")
    val postId: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("createdBy")
    val createdBy: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("updatedBy")
    val updatedBy: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("countLike")
    val countLike: Int,
    @SerializedName("__v")
    val v: Int
)