package vinova.intern.vforum.network

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*
import vinova.intern.vforum.model.group.GroupResponse
import vinova.intern.vforum.model.login.LoginUser
import vinova.intern.vforum.model.sign_up.SignUpUser
import vinova.intern.vforum.model.topic.TopicResponse

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") username: String,
        @Field("password") password: String
    ): Call<LoginUser>

    @FormUrlEncoded
    @POST("signup")
    fun signUp(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("display_name") display_name: String,
        @Field("gender") gender: String
    ): Call<SignUpUser>

    @GET("group")
    fun getGroups(
        @Header("Authorization") authorization: String
    ): Call<GroupResponse>

    @GET("group/{group_id}/topic")
    fun getTopics(
        @Header("Authorization") authorization: String,
        @Path("group_id") group_id: String
    ): Call<TopicResponse>

    ////////////////////////Change Call<TopicResponse>
    @PATCH("info")
    fun changePassword(
        @Header("Authorization") authorization: String,
        @Field("oldpassword") old_password: String,
        @Field("newpassword") new_password: String,
        @Field("renewpassword") renew_password: String
    ): Call<TopicResponse>
}