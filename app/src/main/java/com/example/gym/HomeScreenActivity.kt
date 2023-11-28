package com.example.gym

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView

class HomeScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_fragment)
        val intent = intent
        if (intent.hasExtra("user_name")) {
            val userName = intent.getStringExtra("user_name")

            //welcomeText.text = "Welcome, $userName" "Still trying to fix this"
        } else {
            // Handle the case where user_name is not provided
            //welcomeText.text = "Welcome, User" "Still trying to fix this getting errors here"
        }

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile -> {
                // Handle the "Profile" menu item
                val intent = Intent(this, UserProfileActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.customer_support -> {
                val intent = Intent(this, CustomerSupportActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.sign_out -> {
                val intent = Intent(this, LoginScreenActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}





