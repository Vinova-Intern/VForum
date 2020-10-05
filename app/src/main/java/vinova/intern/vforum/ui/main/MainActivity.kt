package vinova.intern.vforum.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import vinova.intern.vforum.R
import vinova.intern.vforum.utils.SaveSharedPreference
import vinova.intern.vforum.utils.SoftInputAssist

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()

        setContentView(R.layout.activity_main)

        setupViews()

    }

    private fun setupViews() {
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main_fragment) as NavHostFragment

        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.memoryFragment,
                R.id.createPostFragment,
                R.id.notificationFragment,
                R.id.userFragment
            )
        )
        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.homeFragment -> bottomNavView.visibility = View.VISIBLE
                R.id.memoryFragment -> bottomNavView.visibility = View.VISIBLE
                R.id.createPostFragment -> bottomNavView.visibility = View.VISIBLE
                R.id.notificationFragment -> bottomNavView.visibility = View.VISIBLE
                R.id.userFragment -> bottomNavView.visibility = View.VISIBLE
                else -> bottomNavView.visibility = View.GONE
            }
        }
    }
}