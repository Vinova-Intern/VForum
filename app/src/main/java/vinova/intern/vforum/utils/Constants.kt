package vinova.intern.vforum.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import vinova.intern.vforum.ui.auth.AuthActivity

const val DISPLAY_NAME_EXISTED = "Display name has been existed"

const val BEARER_AUTHORIZATION = "Bearer "

const val AUTHORIZATION_ARG = "AUTHORIZATION"

const val ACCESS_TOKEN = "ACCESS_TOKEN"

const val LOGGED_IN = "LOGGED_IN_STATUS"

const val EMULATOR_LOCAL_HOST="http:/10.0.2.2:4000/v1/api/"

const val BASE_URL="http:/192.168.3.197:4000/v1/api/"

fun reLogin(){
    val context: Context = VForumApplication.applicationContext()
    SaveSharedPreference().setLoggedIn(context, false)
    val intent = Intent(context, AuthActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    startActivity(context, intent, null)
}