package vinova.intern.vforum.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.intern.vforum.model.login.LoginUser
import vinova.intern.vforum.model.sign_up.SignUpUser
import vinova.kane.string.network.Client

class UserViewModel: ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val apiService = Client.getClient()

    val signUpData: MutableLiveData<SignUpUser> = MutableLiveData()
    val loginData: MutableLiveData<LoginUser> = MutableLiveData()

    fun signUp(
        email: String,
        password: String,
        displayName: String,
        gender: String
    ){
        Log.d("UserViewModel", "Sign up with: $email $password $displayName $gender")
        compositeDisposable.add(
            apiService.signUp(email, password, displayName, gender)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    signUpData.value = it
                    Log.d("UserViewModel", "Data: $it")
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
            apiService.login(email, password)
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