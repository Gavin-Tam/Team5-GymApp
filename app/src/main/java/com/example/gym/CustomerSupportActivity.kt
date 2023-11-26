package com.example.gym

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CustomerSupportActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextMessage: EditText
    private lateinit var buttonSubmit: Button
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_support)

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextMessage = findViewById(R.id.editTextMessage)
        buttonSubmit = findViewById(R.id.buttonSubmit)

        databaseReference = FirebaseDatabase.getInstance().getReference("customer_support_entries")

        buttonSubmit.setOnClickListener {
            val name = editTextName.text.toString()
            val email = editTextEmail.text.toString()
            val message = editTextMessage.text.toString()

            // Create a unique key for each form submission
            val submissionKey = databaseReference.push().key

            //Create a HasMap to store the form data
            val formData = HashMap<String, String>()
            formData["name"] = name
            formData["email"] = email
            formData["message"] = message

            // Save the form data to Firebase
            submissionKey?.let {
                databaseReference.child(it).setValue(formData)
            }

            // Clear the form submission afterwards
            editTextName.text.clear()
            editTextEmail.text.clear()
            editTextMessage.text.clear()
        }
    }
}