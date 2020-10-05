package vinova.intern.vforum.model.comment


import com.google.gson.annotations.SerializedName

data class CreateCommentResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("error")
    val error: Any,
    @SerializedName("message")
    val message: String,
    @SerializedName("options")
    val options: Any,
    @SerializedName("result")
    val result: CreateComment,
    @SerializedName("success")
    val success: Boolean
)