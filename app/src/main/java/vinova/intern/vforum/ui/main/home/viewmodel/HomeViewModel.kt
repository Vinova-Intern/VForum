package vinova.intern.vforum.ui.main.home.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.intern.vforum.model.group.GroupResponse
import vinova.intern.vforum.model.topic.TopicResponse
import vinova.intern.vforum.network.ApiServiceCaller
import vinova.intern.vforum.utils.reLogin
import java.util.function.Function

class HomeViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val apiManager: ApiServiceCaller by lazy { ApiServiceCaller() }

    val groupsData = MutableLiveData<GroupResponse>()
    val mediatorGroupData = MediatorLiveData<GroupResponse>()

    private val topicsData: MutableLiveData<TopicResponse> = MutableLiveData()

    fun getGroups(authorization: String) {
        compositeDisposable.add(
            apiManager.getGroups(authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    groupsData.value = it
                    groupsData.value?.result?.forEach { item ->
                        getTopics(authorization, item.id)
                        item.topics = topicsData.value?.result ?: emptyList()
                    }
                }, {
                    Log.d("HomeViewModel", "Failure: ${it.message}")
                    reLogin()
                })
        )
    }

    fun getTopics(authorization: String, groupId: String) {
        compositeDisposable.add(
            apiManager.getTopics(authorization, groupId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    topicsData.value = it
                }, {
                    Log.d("HomeViewModel", "Failure: ${it.message}")
                    reLogin()
                })
        )
    }

}