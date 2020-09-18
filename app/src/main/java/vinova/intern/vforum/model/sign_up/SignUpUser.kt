package vinova.intern.vforum.model.sign_up


import com.google.gson.annotations.SerializedName

data class SignUpUser(
    @SerializedName("code")
    val code: Int,
    @SerializedName("error")
    val error: Any,
    @SerializedName("message")
    val message: String,
    @SerializedName("options")
    val options: Any,
    @SerializedName("result")
    val result: SignUpResult,
    @SerializedName("success")
    val success: Boolean
)