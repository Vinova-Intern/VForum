package vinova.intern.vforum.api

import retrofit2.Call
import retrofit2.http.*
import vinova.intern.vforum.model.UserData


interface UserApi {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") username: String,
        @Field("password") password: String
    ): Call<UserData>

    @FormUrlEncoded
    @POST("signup")
    fun signUp(
        @Field("display_name") display_name: String,
        @Field("name") name: String,
        @Field("gender") gender: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<UserData>

    @FormUrlEncoded
    @POST("refresh-token")
    fun refreshToken(
        @Field("code") code: String
    ): Call<UserData>

    @GET("profile/{users_id}")
    fun getUserProfile(
        @Header("Authorization") authorization: String,
        @Path("users_id") users_id: Int
    ): Call<UserData>

    @GET("users-logout")
    fun logOut(
        @Header("Authorization") authorization: String
    ): Call<UserData>

    @FormUrlEncoded
    @POST("users-change-profile")
    fun changeProfile(
        @Field("file") file: String,
        @Field("username") username: String,
        @Field("bio") bio: String,
        @Field("website") website: String,
        @Field("name") name: String,
        @Field("date_of_birth") dayOfBirth: String,
        @Header("Authorization") authorization: String
    ): Call<UserData>

}