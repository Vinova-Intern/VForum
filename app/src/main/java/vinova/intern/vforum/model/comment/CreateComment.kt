package vinova.intern.vforum.model.comment


import com.google.gson.annotations.SerializedName

data class CreateComment(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("createdBy")
    val createdBy: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("userId")
    val userId: String
)