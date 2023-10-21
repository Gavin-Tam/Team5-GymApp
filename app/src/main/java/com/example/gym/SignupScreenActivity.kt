package com.example.gym

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.*
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignupScreenActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var upEmail: EditText
    private lateinit var upUsername: EditText
    private lateinit var upPassword: EditText
    private lateinit var upAge: EditText
    private lateinit var upHeight: EditText
    private lateinit var upWeight: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var maleRadioButton: RadioButton
    private lateinit var femaleRadioButton: RadioButton
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var linkLogin: TextView
    private lateinit var isTrainer: CheckBox



    private val TAG = "Firebase_Auth_SignUp"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen)
        auth = Firebase.auth
        FirebaseApp.initializeApp(this)
        val database = FirebaseDatabase.getInstance()
        val dbReference = database.getReference("users")

        upEmail = findViewById(R.id.upload_email)
        upUsername = findViewById(R.id.upload_username)
        upPassword = findViewById(R.id.upload_password)
        upAge = findViewById(R.id.upload_age)
        upHeight = findViewById(R.id.upload_height)
        upWeight = findViewById(R.id.upload_weight)
        etConfirmPassword = findViewById(R.id.et_password_confirm)
        btnSignUp = findViewById(R.id.btn_signup)
        linkLogin = findViewById(R.id.linklogin)
        isTrainer = findViewById(R.id.uplaodchk_trainer)
        genderRadioGroup = findViewById(R.id.gender_radio)
        maleRadioButton = findViewById(R.id.uploadradio_male)
        femaleRadioButton = findViewById(R.id.uploadradio_female)

        btnSignUp.setOnClickListener {
            createUser()
        }

        linkLogin.setOnClickListener{
            val intent = Intent (this@SignupScreenActivity, LoginScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createUser() {
        val email = upEmail.text.toString()
        val username = upUsername.text.toString()
        val password = upPassword.text.toString()
        val confirmPassword = etConfirmPassword.text.toString()
        val age = upAge.text.toString().toIntOrNull()
        val height = upHeight.text.toString().toDoubleOrNull()
        val weight = upWeight.text.toString().toIntOrNull()
        val isTrainer = isTrainer.isChecked
        val selectedGenderId = genderRadioGroup.checkedRadioButtonId
        val genderValue = when (selectedGenderId) {
            R.id.uploadradio_male -> Gender.MALE
            R.id.uploadradio_female -> Gender.FEMALE
            else -> null
        }


        if (TextUtils.isEmpty(email)) {
            upEmail.error = "Email cannot be empty"
            upEmail.requestFocus()
        } else if (TextUtils.isEmpty(password)) {
            upPassword.error = "Password cannot be empty"
            upPassword.requestFocus()
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

                        val userId = auth.currentUser?.uid
                        val dbReference = FirebaseDatabase.getInstance().getReference()
                        val userData = UserData(
                            email = email,
                            username = username,
                            password = password,
                            age = age,
                            height = height,
                            weight = weight,
                            gender = genderValue,
                            trainer = isTrainer
                        )

                        dbReference.child("users").child(userId ?: "").setValue(userData)

                        Log.d(TAG, "UserData saved to Realtime Database")

                        val intent = Intent(this@SignupScreenActivity, LoginScreenActivity::class.java)
                        startActivity(intent)

                    } else {
                        // If sign-in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed." + task.exception,
                            Toast.LENGTH_SHORT
                        ).show()
                        }

                    }
                }
        }
    }
