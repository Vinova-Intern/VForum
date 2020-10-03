package vinova.intern.vforum.network

import android.util.Log
import io.reactivex.Single
import vinova.intern.vforum.model.change_password.ChangePasswordResponse
import vinova.intern.vforum.model.group.GroupResponse
import vinova.intern.vforum.model.login.LoginUser
import vinova.intern.vforum.model.post.PostResponse
import vinova.intern.vforum.model.sign_up.SignUpUser
import vinova.intern.vforum.model.topic.TopicResponse
import vinova.intern.vforum.utils.BEARER_AUTHORIZATION

class ApiServiceCaller {
    private val _apiRestFull:ApiService by lazy {
        RetrofitClientInstance.getHelperRestFull()!!.create(ApiService::class.java)
    }

    fun login(username:String,password:String): Single<LoginUser> {
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
            "aaaa2",email + "|" +
                    password + "|" + display_name+"|"+gender
        )
        return RetrofitClientInstance.buildRequest(
            _apiRestFull.signUp(
                email = email,
                password = password,
                display_name=display_name,
                gender = gender
            )
        )
    }

    fun getGroups(
        authorization: String
    ):Single<GroupResponse>{
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
    ):Single<TopicResponse>{
        Log.d("GROUP AUTHORIZATION", BEARER_AUTHORIZATION+authorization+"GROUP_ID"+group_id)
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
    ):Single<PostResponse>{
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
    ):Single<ChangePasswordResponse>{
        Log.d("CHANGE PASSWORD", BEARER_AUTHORIZATION+authorization+"GROUP_ID" + old_password + "OLD_PASS" + new_password + "NEW_PASS" + renew_password + "RENEW_PASS")
        return RetrofitClientInstance.buildRequest(
            _apiRestFull.changePassword(
                BEARER_AUTHORIZATION + authorization,
                old_password,
                new_password,
                renew_password
            )
        )
    }
}