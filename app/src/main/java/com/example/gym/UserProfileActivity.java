package com.example.gym;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfileActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextAge;
    private EditText editTextHeight;
    private EditText editTextWeight;
    private Button btnSave;

    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.edittextEmailAddress);
        editTextAge = findViewById(R.id.editTextAge);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        btnSave = findViewById(R.id.btnSave);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (user != null) {
            userRef = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        UserData userData = dataSnapshot.getValue(UserData.class);
                        if (userData != null) {
                            editTextUsername.setText(userData.getUsername());
                            editTextEmail.setText(userData.getEmail());
                            editTextAge.setText(String.valueOf(userData.getAge()));
                            editTextHeight.setText(String.valueOf(userData.getHeight()));
                            editTextWeight.setText(String.valueOf(userData.getWeight()));
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle errors
                }
            });

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateUserProfile();
                }
            });
        }
    }

    private void updateUserProfile() {
        String newUsername = editTextUsername.getText().toString();
        String newEmail = editTextEmail.getText().toString();
        int newAge = Integer.parseInt(editTextAge.getText().toString());
        double newHeight = Double.parseDouble(editTextHeight.getText().toString());
        double newWeight = Double.parseDouble(editTextWeight.getText().toString());

        userRef.child("username").setValue(newUsername);
        userRef.child("email").setValue(newEmail);
        userRef.child("age").setValue(newAge);
        userRef.child("height").setValue(newHeight);
        userRef.child("weight").setValue(newWeight);

        Toast.makeText(UserProfileActivity.this, "Profile updated", Toast.LENGTH_SHORT).show();
    }
}
