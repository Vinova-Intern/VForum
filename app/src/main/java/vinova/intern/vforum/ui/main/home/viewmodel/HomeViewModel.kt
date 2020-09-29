package vinova.intern.vforum.ui.main.home.viewmodel

import android.util.Log
import android.view.animation.Transformation
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.intern.vforum.model.group.GroupResponse
import vinova.intern.vforum.model.sign_up.SignUpUser
import vinova.intern.vforum.model.topic.TopicResponse
import vinova.intern.vforum.network.ApiServiceCaller

class HomeViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    /*private val apiService = Client.getClient()*/
    private val apiManager:ApiServiceCaller by lazy { ApiServiceCaller() }

    val groupsData: MutableLiveData<GroupResponse> = MutableLiveData()
    val topicsData: MutableLiveData<TopicResponse> = MutableLiveData()

    fun getGroups(authorization: String){
        compositeDisposable.add(
            apiManager.getGroups(authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    groupsData.value = it
                },{
                    Log.d("HomeViewModel", "Failure: ${it.message}")
                })
        )
    }

    fun getTopics(authorization: String, groupId: String){
        compositeDisposable.add(
            apiManager.getTopics(authorization, groupId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    topicsData.value = it
                },{
                    Log.d("HomeViewModel", "Failure: ${it.message}")
                })
        )
    }
}