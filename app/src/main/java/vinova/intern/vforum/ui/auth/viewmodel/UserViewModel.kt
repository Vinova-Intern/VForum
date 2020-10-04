package vinova.intern.vforum.ui.auth.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.intern.vforum.model.change_password.ChangePasswordResponse
import vinova.intern.vforum.model.login.LoginUser
import vinova.intern.vforum.model.sign_up.SignUpUser
import vinova.intern.vforum.network.ApiServiceCaller
import vinova.intern.vforum.utils.Status

class UserViewModel: ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    /*private val apiService = Client.getClient()*/

    private val apiManager:ApiServiceCaller by lazy { ApiServiceCaller() }

    val signUpData: MutableLiveData<SignUpUser> = MutableLiveData()
    val loginData: MutableLiveData<LoginUser> = MutableLiveData()
    val passwordData: MutableLiveData<ChangePasswordResponse> = MutableLiveData()
    val status: MutableLiveData<Status> = MutableLiveData()

    fun signUp(
        email: String,
        password: String,
        displayName: String,
        gender: String
    ){
        Log.d("UserViewModel", "Sign up with: $email $password $displayName $gender")
        status.value = Status.LOADING
        compositeDisposable.add(
            apiManager.signUp(
                    email,
                    password,
                    displayName,
                    gender
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    status.value = Status.SUCCESS
                    signUpData.value = it
                    Log.d("UserViewModel", "Data: $it")
                },{
                    status.value = Status.ERROR
                    Log.d("UserViewModel", "Failure: ${it.message}")
                })
        )
    }

    fun login(
        email: String,
        password: String
    ){
        status.value = Status.LOADING
        compositeDisposable.add(
            apiManager.login(
                    email,
                    password
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    status.value = Status.SUCCESS
                    loginData.value = it
                },{
                    status.value = Status.ERROR
                    Log.d("UserViewModel", "Failure: ${it.message}")
                })
        )
    }

    fun changePassword(
        authorization: String,
        old_password: String,
        new_password: String,
        renew_password: String
    ){
        compositeDisposable.add(
            apiManager.changePassword(authorization, old_password, new_password, renew_password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    passwordData.value = it
                },{
                    Log.d("UserViewModel", "Message: ${it.message}")
                })
        )
    }
}