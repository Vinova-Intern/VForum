package vinova.intern.vforum.model.group


import com.google.gson.annotations.SerializedName

data class GroupResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("error")
    val error: Any,
    @SerializedName("options")
    val options: Any,
    @SerializedName("result")
    val result: ArrayList<Group>,
    @SerializedName("success")
    val success: Boolean
)