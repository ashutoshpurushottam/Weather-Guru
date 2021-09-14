package com.deft.weatherguru.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.deft.weatherguru.BuildConfig
import com.deft.weatherguru.R
import com.deft.weatherguru.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_fragment)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        // control for bottom navigation
        bottom_navigation.setupWithNavController(navController)
        // action bar clicks
        NavigationUI.setupActionBarWithNavController(this, navController)

        // Check if OWM_KEY can be obtained
        Timber.d(BuildConfig.OWM_KEY)
    }

    // Behaviour on clicking the up button
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}