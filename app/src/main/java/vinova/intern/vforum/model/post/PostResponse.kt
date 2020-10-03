package vinova.intern.vforum.model.post


import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("error")
    val error: Any,
    @SerializedName("options")
    val options: Any,
    @SerializedName("result")
    val result: ArrayList<Post>,
    @SerializedName("success")
    val success: Boolean
)