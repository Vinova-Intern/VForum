package vinova.intern.vforum.ui.main.createpost.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.intern.vforum.model.group.Group
import vinova.intern.vforum.model.group.GroupResponse
import vinova.intern.vforum.model.topic.TopicResponse
import vinova.intern.vforum.network.ApiServiceCaller
import vinova.intern.vforum.utils.reLogin

class CreatePostViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val apiManager: ApiServiceCaller by lazy { ApiServiceCaller() }



    val groupsData = MutableLiveData<GroupResponse>()
    val topicsData = MutableLiveData<TopicResponse>()

    var groupListLiveData = MutableLiveData<ArrayList<Group>>().apply { value = arrayListOf() }
    var listTempt = ArrayList<Group>()


    fun getGroups(authorization: String) {
        compositeDisposable.add(
            apiManager.getGroups(authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    groupsData.value = it
                    groupsData.value?.result?.forEach { item ->
                        getTopics(authorization, item)
                    }

                    groupsData.value?.result?.forEach { item ->
                        listTempt.add(item)
                        groupListLiveData.postValue(listTempt)
                    }

                }, {
                    Log.d("HomeViewModel", "Failure: ${it.message}")
                })
        )
    }

    fun getTopics(authorization: String, group: Group) {
        compositeDisposable.add(
            apiManager.getTopics(authorization, group.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("Tim",it.toString());
                    val pos  = groupsData.value?.result?.indexOf(group)
                    groupsData.value?.result?.get(pos?:return@subscribe)?.topics= it.result
                }, {
                    Log.d("HomeViewModel", "Failure: ${it.message}")
                    reLogin()
                })
        )
    }


}