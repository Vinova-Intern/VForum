package vinova.intern.vforum.model.create_post

import com.google.gson.annotations.SerializedName

data class CreatePost(
    @SerializedName("id")
    val id: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("userId")
    val user_id: String
)