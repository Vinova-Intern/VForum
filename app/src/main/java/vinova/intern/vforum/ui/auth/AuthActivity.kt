package vinova.intern.vforum.ui.auth

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import vinova.intern.vforum.R
import vinova.intern.vforum.utils.SoftInputAssist


class AuthActivity : AppCompatActivity() {
    private lateinit var softInputAssist: SoftInputAssist

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()

        setContentView(R.layout.activity_auth)

        softInputAssist = SoftInputAssist(this)

    }

    override fun onResume() {
//        softInputAssist.onResume()
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        softInputAssist.onPause()
//        super.onPause()
    }
}