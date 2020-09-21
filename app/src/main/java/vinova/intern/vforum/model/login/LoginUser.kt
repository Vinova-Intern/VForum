package vinova.intern.vforum.model.login


import com.google.gson.annotations.SerializedName

data class LoginUser(
    @SerializedName("code")
    val code: Int,
    @SerializedName("error")
    val error: Any,
    @SerializedName("message")
    val message: String,
    @SerializedName("options")
    val options: Any,
    @SerializedName("result")
    val result: LoginResult,
    @SerializedName("success")
    val success: Boolean
)