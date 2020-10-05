package vinova.intern.vforum.model.comment

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("countLike")
    val countLike: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("createdBy")
    val createdBy: String,
    @SerializedName("updatedBy")
    val updatedBy: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("postId")
    val postId: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("__v")
    val v: Int
)