package vinova.intern.vforum.model.comment


import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("error")
    val error: Any,
    @SerializedName("options")
    val options: Any,
    @SerializedName("result")
    val result: List<Comment>,
    @SerializedName("success")
    val success: Boolean
)