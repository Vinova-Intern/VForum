package vinova.intern.vforum.model.login


import com.google.gson.annotations.SerializedName

data class LoginResult(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("userId")
    val userId: String
)