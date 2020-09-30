package vinova.intern.vforum.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import vinova.intern.vforum.R
import vinova.intern.vforum.ui.main.MainActivity
import vinova.intern.vforum.utils.SaveSharedPreference
import vinova.intern.vforum.utils.SoftInputAssist


class AuthActivity : AppCompatActivity() {
    private lateinit var softInputAssist: SoftInputAssist

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        supportActionBar?.hide()

        setContentView(R.layout.activity_auth)

        if(SaveSharedPreference().getLoggedStatus(this)){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        softInputAssist = SoftInputAssist(this)

    }

    override fun onResume() {
        softInputAssist.onResume()
        super.onResume()
    }

    override fun onPause() {
        softInputAssist.onPause()
        super.onPause()
    }
}