package vinova.intern.vforum.ui.main.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.intern.vforum.model.group.GroupResponse
import vinova.intern.vforum.model.sign_up.SignUpUser
import vinova.intern.vforum.network.Client

class HomeViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val apiService = Client.getClient()

    val groupsData: MutableLiveData<GroupResponse> = MutableLiveData()

    fun getGroups(authorization: String){
        compositeDisposable.add(
            apiService.getGroups(authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    groupsData.value = it
                },{
                    Log.d("HomeViewModel", "Failure: ${it.message}")
                })
        )
    }
}