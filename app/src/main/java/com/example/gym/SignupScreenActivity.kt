package com.example.gym

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignupScreenActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var linkLogin: TextView
    private lateinit var isTrainer: CheckBox

    private val TAG = "Firebase_Auth_SignUp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen)
        auth = Firebase.auth
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        etConfirmPassword = findViewById(R.id.et_password_confirm)
        btnSignUp = findViewById(R.id.btn_signup)
        linkLogin = findViewById(R.id.linklogin)
        isTrainer =findViewById(R.id.chk_trainer)

        btnSignUp.setOnClickListener {
            createUser()
        }

        linkLogin.setOnClickListener{
            val intent = Intent (this@SignupScreenActivity, LoginScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createUser() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        val confirmPassword = etConfirmPassword.text.toString()
        val isTrainerChecked = isTrainer.isChecked

        if (TextUtils.isEmpty(email)) {
            etEmail.error = "Email cannot be empty"
            etEmail.requestFocus()
        } else if (TextUtils.isEmpty(password)) {
            etPassword.error = "Password cannot be empty"
            etPassword.requestFocus()
        } else if (TextUtils.isEmpty(confirmPassword)) {
            etConfirmPassword.error = "Confirm Password cannot be empty"
            etConfirmPassword.requestFocus()
        } else if (password != confirmPassword) {
            etConfirmPassword.error = "Passwords don't match"
            etConfirmPassword.requestFocus()
        } else {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign Up success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        Toast.makeText(
                            this@SignupScreenActivity,
                            "Sign Up Successful!",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this@SignupScreenActivity, LoginScreenActivity::class.java)
                        startActivity(intent)
                        if (isTrainerChecked) {
                            // If the user is a trainer, navigate to the trainer home screen
                            val intent = Intent(this@SignupScreenActivity, TrainersHomeScreenActivity::class.java)
                            startActivity(intent)

                    } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure")
                            Toast.makeText(
                                baseContext, "Authentication failed." + task.exception,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                }
        }
    }
}