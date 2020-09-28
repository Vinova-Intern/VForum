package vinova.intern.vforum.network

import io.reactivex.Single
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
    ): Single<LoginUser>

    @FormUrlEncoded
    @POST("signup")
    fun signUp(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("display_name") display_name: String,
        @Field("gender") gender: String
    ): Single<SignUpUser>

    @GET("group")
    fun getGroups(
        @Header("Authorization") authorization: String
    ): Single<GroupResponse>

    @GET("group/{group_id}/topic")
    fun getTopics(
        @Header("Authorization") authorization: String,
        @Path("group_id") group_id: String
    ): Single<TopicResponse>
}