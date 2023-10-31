package com.example.gym

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
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





