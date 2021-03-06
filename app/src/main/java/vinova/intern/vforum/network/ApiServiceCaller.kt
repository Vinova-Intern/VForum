package vinova.intern.vforum.network

import android.util.Log
import io.reactivex.Single
import vinova.intern.vforum.model.change_password.ChangePasswordResponse
import vinova.intern.vforum.model.comment.CommentResponse
import vinova.intern.vforum.model.comment.CreateCommentResponse
import vinova.intern.vforum.model.create_post.CreatePostResponse
import vinova.intern.vforum.model.group.GroupResponse
import vinova.intern.vforum.model.login.LoginUser
import vinova.intern.vforum.model.post.PostResponse
import vinova.intern.vforum.model.sign_up.SignUpUser
import vinova.intern.vforum.model.topic.TopicResponse
import vinova.intern.vforum.utils.BEARER_AUTHORIZATION

class ApiServiceCaller {
    private val _apiRestFull: ApiService by lazy {
        RetrofitClientInstance.getHelperRestFull()!!.create(ApiService::class.java)
    }

    fun login(username: String, password: String): Single<LoginUser> {
        return RetrofitClientInstance.buildRequest(
            _apiRestFull.login(
                username, password
            )
        )
    }

    fun signUp(
        email: String,
        password: String,
        display_name: String,
        gender: String
    ): Single<SignUpUser> {
        Log.d(
            "aaaa2", email + "|" +
                    password + "|" + display_name + "|" + gender
        )
        return RetrofitClientInstance.buildRequest(
            _apiRestFull.signUp(
                email = email,
                password = password,
                display_name = display_name,
                gender = gender
            )
        )
    }

    fun getGroups(
        authorization: String
    ): Single<GroupResponse> {
        Log.d("ApiServiceCaller AUTHORIZATION", BEARER_AUTHORIZATION + authorization)
        return RetrofitClientInstance.buildRequest(
            _apiRestFull.getGroups(
                BEARER_AUTHORIZATION + authorization
            )
        )
    }

    fun getTopics(
        authorization: String,
        group_id: String
    ): Single<TopicResponse> {
        Log.d("GROUP AUTHORIZATION", BEARER_AUTHORIZATION + authorization + "GROUP_ID" + group_id)
        return RetrofitClientInstance.buildRequest(
            _apiRestFull.getTopics(
                BEARER_AUTHORIZATION + authorization,
                group_id
            )
        )
    }

    fun getPosts(
        authorization: String,
        group_id: String,
        topic_id: String
    ): Single<PostResponse> {
        return RetrofitClientInstance.buildRequest(
            _apiRestFull.getPosts(
                BEARER_AUTHORIZATION + authorization,
                group_id,
                topic_id
            )
        )
    }

    fun changePassword(
        authorization: String,
        old_password: String,
        new_password: String,
        renew_password: String
    ): Single<ChangePasswordResponse> {
        Log.d(
            "CHANGE PASSWORD",
            BEARER_AUTHORIZATION + authorization + "GROUP_ID" + old_password + "OLD_PASS" + new_password + "NEW_PASS" + renew_password + "RENEW_PASS"
        )
        return RetrofitClientInstance.buildRequest(
            _apiRestFull.changePassword(
                BEARER_AUTHORIZATION + authorization,
                old_password,
                new_password,
                renew_password
            )
        )
    }

    fun createPost(
        authorization: String,
        group_id: String,
        topic_id: String,
        title: String,
        description: String
    ): Single<CreatePostResponse> {
        Log.d(
            "CREATE POST",
            BEARER_AUTHORIZATION + authorization + "GROUP_ID" + group_id + "TOPIC_ID" + topic_id + "POST_TITLE" + title + "POST_DESCRIPTION" + description
        )
        return RetrofitClientInstance.buildRequest(
            _apiRestFull.createPost(
                BEARER_AUTHORIZATION + authorization,
                group_id,
                topic_id,
                title,
                description
            )
        )
    }

    fun getComments(
        authorization: String,
        group_id: String,
        topic_id: String,
        post_id: String
    ): Single<CommentResponse> {
        Log.d(
            "GET_COMMENTS",
            BEARER_AUTHORIZATION + authorization + "GROUP_ID" + group_id + "TOPIC_ID" + topic_id + "POST_ID" + post_id
        )
        return RetrofitClientInstance.buildRequest(
            _apiRestFull.getComments(
                BEARER_AUTHORIZATION + authorization,
                group_id,
                topic_id,
                post_id
            )
        )
    }

    fun addComment(
        authorization: String,
        group_id: String,
        topic_id: String,
        post_id: String,
        description: String
    ): Single<CreateCommentResponse> {
        return RetrofitClientInstance.buildRequest(
            _apiRestFull.addComment(
                BEARER_AUTHORIZATION + authorization,
                group_id,
                topic_id,
                post_id,
                description
            )
        )
    }
}