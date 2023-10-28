package com.lcabral.artseventh

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.lcabral.artseventh.core.common.navigation.DashboardNavigation
import com.lcabral.artseventh.core.common.navigation.SearchNavigation
import com.lcabral.artseventh.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding
    private val dashboardNavigation: DashboardNavigation by inject()
    private val searchNavigation: SearchNavigation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.BLACK

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.container_main, dashboardNavigation.create())
            }
        }
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.dashboardFragment-> goToDashboard()
                R.id.searchFragment-> goToSearch()
                R.id.favoriteFragment -> goToFavorite()
            }
            true
        }
    }

    private fun goToDashboard() {
        supportFragmentManager.commit {
            replace(R.id.container_main, dashboardNavigation.create())
        }
    }

    private fun goToSearch() {
        searchNavigation.create(this)
    }

    private fun goToFavorite() {
        Toast.makeText(this, "Implementa  favorite outro PR", Toast.LENGTH_SHORT).show()
    }
}

