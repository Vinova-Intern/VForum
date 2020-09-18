package vinova.intern.vforum.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.intern.vforum.api.UserApiCaller
import vinova.intern.vforum.model.UserData

class UserViewModel: ViewModel() {
    private val compositeDisposable by lazy { CompositeDisposable() }
    private val apiCaller: UserApiCaller by lazy { UserApiCaller() }

    val signUpData = MutableLiveData<UserData>()
    val loginData = MutableLiveData<UserData>().apply { value = null }

    fun signUp(
        email: String,
        password: String,
        displayName: String,
        gender: String
    ){
        Log.d("UserViewModel", "Sign up with: $email $password $displayName $gender")
        compositeDisposable.add(
            apiCaller.signUp(
                email, password, displayName, gender
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    signUpData.value = it
                    Log.d("UserViewModel", "${it.message}")
                },{
                    Log.d("UserViewModel", "Failure: ${it.message}")
                })
        )
    }

    fun login(
        email: String,
        password: String
    ){
        compositeDisposable.add(
            apiCaller.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loginData.value = it
                },{
                    Log.d("UserViewModel", "Failure: ${it.message}")
                })
        )
    }
}