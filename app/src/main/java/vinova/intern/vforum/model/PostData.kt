package vinova.intern.vforum.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class PostData(
    var success: Boolean?,
    val result: ArrayList<Post>?,
    var message: String?,
    val code: Int?
)

@Parcelize
data class Post(
    val commentsPost:ArrayList<Comment>,
    var countLike:Int?,
    var countCommentPost:Int?,
    val createdBy: String?,
    var commentCounter: Int?,
    val title:String?,
    val description: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val updatedBy: String?,
    @PrimaryKey
    @SerializedName("_id")
    val id: Int?
) : Parcelable