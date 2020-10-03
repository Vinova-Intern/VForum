package vinova.intern.vforum.model.create_post

import com.google.gson.annotations.SerializedName

data class CreatePostResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("result")
    val result: CreatePost,
    @SerializedName("message")
    val message: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("options")
    val options: Any,
    @SerializedName("error")
    val error: Any
)