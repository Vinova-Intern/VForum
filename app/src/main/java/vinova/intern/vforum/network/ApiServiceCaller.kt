package vinova.intern.vforum.network

import android.util.Log
import io.reactivex.Single
import vinova.intern.vforum.model.group.GroupResponse
import vinova.intern.vforum.model.login.LoginUser
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


}