package com.example.gym

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize NavController
        navController = findNavController(R.id.fragmentContainer)

        // Set up BottomNavigationView with NavController
        findViewById<BottomNavigationView>(R.id.nav_graph).setupWithNavController(navController)
    }
}


