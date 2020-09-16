package vinova.intern.vforum.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class TopicData(
    var success: Boolean?,
    val result: ArrayList<Topic>?,
    var message: String?,
    val code: Int?
)

@Parcelize
data class Topic(
    @SerializedName("name")
    val topicName: String?,
    val createdBy: String?,
    val createdAt: String?,
    @SerializedName("description")
    val topicDescription: String?,
    val updatedAt: String?,
    val updatedBy: String?,
    @PrimaryKey
    val id: Int?
) : Parcelable