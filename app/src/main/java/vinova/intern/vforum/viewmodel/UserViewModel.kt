package vinova.intern.vforum.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.intern.vforum.model.login.LoginUser
import vinova.intern.vforum.model.sign_up.SignUpUser
import vinova.intern.vforum.network.Client
import vinova.intern.vforum.utils.Status

class UserViewModel: ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val apiService = Client.getClient()

    val signUpData: MutableLiveData<SignUpUser> = MutableLiveData()
    val loginData: MutableLiveData<LoginUser> = MutableLiveData()
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
            apiService.signUp(email, password, displayName, gender)
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
            apiService.login(email, password)
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
}