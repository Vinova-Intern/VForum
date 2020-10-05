package vinova.intern.vforum.network

import android.util.Log
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import vinova.intern.vforum.utils.BASE_URL
import vinova.intern.vforum.utils.EMULATOR_LOCAL_HOST

class RetrofitClientInstance {

    companion object {

        private var retrofit: Retrofit? = null

        private val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("API", message)
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY)

        private var client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        fun getHelperRestFull(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit
                    .Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(EMULATOR_LOCAL_HOST)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun <T : Any> buildRequest(call: Call<T>): Single<T> {
            return Single.create {
                call.enqueue(object : Callback<T> {
                    override fun onResponse(call: Call<T>, response: Response<T>) {
                        try {
                            when (response.code()) {
                                201 -> {
                                    it.onSuccess(response.body()!!)
                                }
                                200 -> {
                                    it.onSuccess(response.body()!!)
                                }
                                400 -> it.onError(Throwable(response.message()))
                                401 -> it.onError(Throwable(response.message()))
                            }
                        } catch (ex: Exception) {
                            it.onError(ex)
                        }
                    }

                    override fun onFailure(p0: Call<T>, response: Throwable) {
                        it.onError(response)
                    }
                })
            }
        }
    }
}