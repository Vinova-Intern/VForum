package vinova.intern.vforum.network

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*
import vinova.intern.vforum.model.change_password.ChangePasswordResponse
import vinova.intern.vforum.model.comment.CommentResponse
import vinova.intern.vforum.model.comment.CreateCommentResponse
import vinova.intern.vforum.model.create_post.CreatePostResponse
import vinova.intern.vforum.model.group.GroupResponse
import vinova.intern.vforum.model.login.LoginUser
import vinova.intern.vforum.model.post.PostResponse
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

    @GET("group/{group_id}/topic/{topic_id}/post")
    fun getPosts(
        @Header("Authorization") authorization: String,
        @Path("group_id") group_id: String,
        @Path("topic_id") topic_id:String
    ): Call<PostResponse>

    @FormUrlEncoded
    @PATCH("info")
    fun changePassword(
        @Header("Authorization") authorization: String,
        @Field("oldpassword") old_password: String,
        @Field("newpassword") new_password: String,
        @Field("renewpassword") renew_password: String
    ): Call<ChangePasswordResponse>


    @FormUrlEncoded
    @POST("group/{group_id}/topic/{topic_id}/post")
    fun createPost(
        @Header("Authorization") authorization: String,
        @Path("group_id") group_id:String,
        @Path("topic_id") topic_id:String,
        @Field("title") title:String,
        @Field("description") description:String
    ):Call<CreatePostResponse>

    @GET("group/{group_id}/topic/{topic_id}/post/{post_id}/comment")
    fun getComments(
        @Header("Authorization") authorization: String,
        @Path("group_id") group_id:String,
        @Path("topic_id") topic_id:String,
        @Path("post_id") post_id:String
    ):Call<CommentResponse>

    @FormUrlEncoded
    @POST("group/{group_id}/topic/{topic_id}/post/{post_id}/comment")
    fun addComment(
        @Header("Authorization") authorization: String,
        @Path("group_id") group_id:String,
        @Path("topic_id") topic_id:String,
        @Path("post_id") post_id:String,
        @Field("description") description:String
    ):Call<CreateCommentResponse>
}