package vinova.intern.vforum.model.group


import com.google.gson.annotations.SerializedName
import vinova.intern.vforum.model.topic.Topic

data class Group(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("createdBy")
    val createdBy: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    var topics: List<Topic>

)