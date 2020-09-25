package vinova.intern.vforum.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.vforum_homepage.*
import vinova.intern.vforum.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user_icon.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.homepage_menu, menu)
        return true
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.user_icon){
            drawer_layout.openDrawer(GravityCompat.START)
        }

        if (view?.id == R.id.log_out){
            Log.d("gg", "mathafaka")
        }
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME).flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}