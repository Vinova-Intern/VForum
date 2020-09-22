package vinova.intern.vforum.model.topic


import com.google.gson.annotations.SerializedName

data class TopicResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("error")
    val error: Any,
    @SerializedName("options")
    val options: Any,
    @SerializedName("result")
    val result: List<Topic>,
    @SerializedName("success")
    val success: Boolean
)