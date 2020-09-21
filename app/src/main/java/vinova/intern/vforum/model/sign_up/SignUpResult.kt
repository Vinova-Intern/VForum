package vinova.intern.vforum.model.sign_up


import com.google.gson.annotations.SerializedName

data class SignUpResult(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("id")
    val id: String
)