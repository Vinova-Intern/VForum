package vinova.intern.vforum.model.change_password


import com.google.gson.annotations.SerializedName

data class ChangePasswordResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("error")
    val error: Any?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("options")
    val options: Any?,
    @SerializedName("result")
    val result: Result?,
    @SerializedName("success")
    val success: Boolean?
)