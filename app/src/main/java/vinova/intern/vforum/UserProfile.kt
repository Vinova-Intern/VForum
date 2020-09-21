package vinova.intern.vforum

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.user_profile.*
import kotlinx.android.synthetic.main.vforum_homepage.*


class UserProfile : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile)

        feed_user.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.homepage_menu, menu)
        return true
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.feed_user){
            drawer_layout.openDrawer(GravityCompat.START)
        }
    }
}