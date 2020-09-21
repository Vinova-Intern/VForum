package vinova.intern.vforum.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class UserData(
    var success: Boolean?,
    val result: UserInfo?,
    @SerializedName("message")
    var message: String,
    val code: Int?
)

@Parcelize
data class UserInfo(
    val accessToken: String?,
    val createdAt: String?,
    val email: String?,
    val fcm_token: String?,
    val gender: String?,
    val userId: String?,
    val refreshToken: String?,
    val refreshTokenFromClient:String?,
    val updated_at: String?,
    val displayName: String?,
    val postsCounter: Int?
): Parcelable

@Parcelize
data class NotificationSettings(
    val comments: Boolean?,
    val likes: Boolean,
    val newFollows: Boolean?,
    val post_saves: Boolean?,
    val string: Boolean?
) : Parcelable

