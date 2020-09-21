package vinova.intern.vforum.api

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

class RetrofitClientInstance {

    companion object {

        private var retrofit: Retrofit? = null

        private val basic_logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("basic", message)
            }
        }).setLevel(HttpLoggingInterceptor.Level.BASIC)
        private val header_logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("header", message)
            }
        }).setLevel(HttpLoggingInterceptor.Level.BASIC)
        private val body_logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("body", message)
            }
        }).setLevel(HttpLoggingInterceptor.Level.BASIC)

        private var client = OkHttpClient.Builder()
            .addInterceptor(basic_logging)
            .build()

        fun getHelperRestFull(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit
                    .Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("http:/10.0.2.2:4000/v1/api/")
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