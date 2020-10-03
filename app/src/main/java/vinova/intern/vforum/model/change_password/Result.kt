package vinova.intern.vforum.model.change_password


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    val id: String?,
    @SerializedName("isUpdated")
    val isUpdated: Boolean?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)