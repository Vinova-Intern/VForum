package vinova.intern.vforum.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class GroupData(
    var success: Boolean?,
    val result: ArrayList<Group>?,
    var message: String?,
    val code: Int?
)

@Parcelize
data class Group(
    @SerializedName("name")
    val groupName: String?,
    val createdBy: String?,
    val coEdit: Boolean?,
    var commentCounter: Int?,
    val copyCounter: Int?,
    val createdAt: String?,
    val updatedAt: String?,
    val updatedBy: String?,
    @PrimaryKey
    val id: Int?
) : Parcelable
