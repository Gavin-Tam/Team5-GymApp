package com.example.gym

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginScreenActivity : AppCompatActivity() {
    private lateinit var linkSignup: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var upEmail: EditText
    private lateinit var upPassword: EditText
    private lateinit var btnLogin: Button
    private val TAG = "Firebase_Auth_Login"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        auth = Firebase.auth
        Log.d(TAG, "IS THERE A USER? " + auth.currentUser)

        upEmail = findViewById(R.id.upload_email)
        upPassword = findViewById(R.id.upload_password)
        btnLogin = findViewById(R.id.btn_login)
        linkSignup = findViewById(R.id.linksignup)

        btnLogin.setOnClickListener {
            attemptLogin()
        }

        linkSignup.setOnClickListener {
            val intent = Intent(this@LoginScreenActivity, SignupScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun attemptLogin() {
        val email = upEmail.text.toString()
        val password = upPassword.text.toString()

        if (TextUtils.isEmpty(email)) {
            upEmail.error = "Email cannot be empty"
            upEmail.requestFocus()
        } else if (TextUtils.isEmpty(password)) {
            upPassword.error = "Password cannot be empty"
            upPassword.requestFocus()
        } else {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        val user = auth.currentUser
                        if (user != null) {
                            Log.d(TAG, "signInWithEmail:success ${user.email}")
                        }
                        val intent =
                            Intent(this@LoginScreenActivity, HomeScreenActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }



}