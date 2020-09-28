package vinova.intern.vforum.utils

import android.app.Application
import android.content.Context

class VForumApplication: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: VForumApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

}