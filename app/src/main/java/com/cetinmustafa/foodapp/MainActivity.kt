package com.cetinmustafa.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.homePageFragment,
                R.id.basketFragment,
                R.id.profileFragment -> bottomNavigationView?.visibility = View.VISIBLE
                else -> bottomNavigationView?.visibility = View.GONE
            }
        }
    }

}