package vinova.intern.vforum.api

import android.util.Log
import io.reactivex.Single
import vinova.intern.vforum.model.UserData

class UserApiCaller {
    private val _apiRestFull: UserApi by lazy {
        RetrofitClientInstance.getHelperRestFull()!!.create(UserApi::class.java)
    }

    fun login(email: String, password: String): Single<UserData> {
        return RetrofitClientInstance.buildRequest(
            _apiRestFull.login(
                email,
                password
            )
        )
    }

    fun signUp(
        email: String,
        password: String,
        displayName: String,
        gender: String
    ): Single<UserData> {
        return RetrofitClientInstance.buildRequest(
            _apiRestFull.signUp(
                email,
                password,
                displayName,
                gender
            )
        )
    }

}