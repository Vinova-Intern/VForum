package vinova.intern.vforum.ui.main.post.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.intern.vforum.model.post.PostResponse
import vinova.intern.vforum.network.ApiServiceCaller
import vinova.intern.vforum.utils.Status

class PostViewModel: ViewModel(){
    private val compositeDisposable = CompositeDisposable()
    private val apiManager: ApiServiceCaller by lazy { ApiServiceCaller() }
    val status: MutableLiveData<Status> = MutableLiveData()

    val postData = MutableLiveData<PostResponse>()

    fun getPosts(authorization: String, groupId: String, topicId: String){
        status.value = Status.LOADING
        compositeDisposable.add(
            apiManager.getPosts(authorization, groupId, topicId)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    status.value = Status.SUCCESS
                    postData.value = it
                },{
                    Log.d("PostViewModel", "Failure: ${it.message}")
                })
        )
    }

}