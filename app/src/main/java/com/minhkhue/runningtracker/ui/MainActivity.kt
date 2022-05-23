package com.minhkhue.runningtracker.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.minhkhue.runningtracker.R
import com.minhkhue.runningtracker.databinding.ActivityMainBinding
import com.minhkhue.runningtracker.utils.Constant
import com.minhkhue.runningtracker.utils.Extensions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController =
            (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
        binding.bottomNavigationView.setupWithNavController(navController)
        binding.bottomNavigationView.setOnItemReselectedListener { /*NO-OP*/ }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.runFragment, R.id.statisticsFragment,R.id.newsFragment ,R.id.foodFragment, R.id.settingsFragment ->
                    binding.bottomNavigationView.visibility = View.VISIBLE
                else ->
                    binding.bottomNavigationView.visibility = View.GONE
            }
        }
        navigateToTrackingFragmentIfNeed(intent)
    }

    private fun navigateToTrackingFragmentIfNeed(intent: Intent?) {
        if (intent?.action == Constant.ACTION_SHOW_TRACKING_FRAGMENT) {
            navController.navigate(R.id.action_global_trackingFragment)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeed(intent)
    }
}