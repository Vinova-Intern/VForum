package vinova.intern.vforum.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import vinova.intern.vforum.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        supportActionBar?.hide()

        setContentView(R.layout.activity_auth)
    }
}