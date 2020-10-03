package vinova.intern.vforum.ui.main.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.intern.vforum.model.create_post.CreatePostResponse
import vinova.intern.vforum.model.group.Group
import vinova.intern.vforum.model.group.GroupResponse
import vinova.intern.vforum.model.topic.TopicResponse
import vinova.intern.vforum.network.ApiServiceCaller
import vinova.intern.vforum.utils.Status
import vinova.intern.vforum.utils.reLogin

class HomeViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val apiManager: ApiServiceCaller by lazy { ApiServiceCaller() }
    private val createPostData=MutableLiveData<CreatePostResponse>()
    val groupsData = MutableLiveData<GroupResponse>()
    val status: MutableLiveData<Status> = MutableLiveData()
    val count: MutableLiveData<Int> = MutableLiveData(0)

    private val topicsData: MutableLiveData<TopicResponse> = MutableLiveData()

    fun getGroups(authorization: String) {
        status.value = Status.LOADING
        compositeDisposable.add(
            apiManager.getGroups(authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    status.value = Status.SUCCESS
                    groupsData.value = it
                    groupsData.value?.result?.forEach { item ->
                        getTopics(authorization, item)
                    }
                }, {
                    Log.d("HomeViewModel", "Failure: ${it.message}")
                    reLogin()
                })
        )
    }

    private fun getTopics(authorization: String, group: Group) {
        compositeDisposable.add(
            apiManager.getTopics(authorization, group.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    count.postValue(count.value?.plus(1))
                    val pos  = groupsData.value?.result?.indexOf(group)
                    groupsData.value?.result?.get(pos?:return@subscribe)?.topics= it.result
                }, {
                    Log.d("HomeViewModel", "Failure: ${it.message}")
                    reLogin()
                })
        )
    }
    fun createPost(
        authorization: String,
        group_id: String,
        topic_id: String,
        post_title: String,
        post_desc: String
    ){
        Log.d("HomeViewModel", "Create post at Group: $group_id at $topic_id title $post_title desc $post_desc")
        compositeDisposable.add(
            apiManager.createPost(
                authorization,
                group_id,
                topic_id,
                post_title,
                post_desc
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    status.value = Status.SUCCESS
                    createPostData.value = it
                    Log.d("HomeViewMode", "Post: $it")
                },{
                    status.value = Status.ERROR
                    Log.d("HomeViewModel", "Failure: ${it.message}")
                })
        )
    }

}