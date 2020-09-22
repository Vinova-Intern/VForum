package vinova.intern.vforum.model.topic


import com.google.gson.annotations.SerializedName

data class Topic(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("createdBy")
    val createdBy: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String
)