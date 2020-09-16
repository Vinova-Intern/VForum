package vinova.intern.vforum.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class CommentData(
    var success: Boolean?,
    val result: ArrayList<Comment>?,
    var message: String?,
    val code: Int?
)
@Parcelize
data class Comment(
    var countLike: Int?,
    val status: String?,
    val createdBy:String?,
    val updatedBy:String?,
    val updatedAt:String?,
    val createdAt:String?,
    val description: String?,
    val id: Int?,
    val postId: Int?
) : Parcelable

