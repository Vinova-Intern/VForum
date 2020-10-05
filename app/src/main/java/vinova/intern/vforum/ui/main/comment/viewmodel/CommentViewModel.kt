package vinova.intern.vforum.ui.main.comment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.intern.vforum.model.comment.CommentResponse
import vinova.intern.vforum.network.ApiServiceCaller
import vinova.intern.vforum.utils.Status

class CommentViewModel: ViewModel(){
    private val compositeDisposable = CompositeDisposable()
    private val apiManager: ApiServiceCaller by lazy { ApiServiceCaller() }
    val status: MutableLiveData<Status> = MutableLiveData()

    val commentData=MutableLiveData<CommentResponse>()

    fun getComments(authorization: String, groupId: String, topicId: String,postId:String){
        status.value = Status.LOADING
        compositeDisposable.add(
            apiManager.getComments(authorization, groupId, topicId,postId)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    status.value = Status.SUCCESS
                    commentData.value = it
                    Log.d("CommentData",it.result.toString())
                },{
                    Log.d("CommentViewModel", "Failure: ${it.message}")
                })
        )
    }

}