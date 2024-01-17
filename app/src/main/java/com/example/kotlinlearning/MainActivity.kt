package com.example.kotlinlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.kotlinlearning.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 *
 * Main Activity / First Activity
 *
 * */
@AndroidEntryPoint
open class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding : ActivityMainBinding
    private lateinit var  navController :NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
       // var navController = findNavController(R.id.nav_host_fragment)
        val navigationHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navigationHost.navController


        setContentView(mainBinding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}