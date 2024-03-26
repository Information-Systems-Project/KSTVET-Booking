package com.alfabett.kstvetbooking.db

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.alfabett.kstvetbooking.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class DbConnect: ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val f_store = FirebaseFirestore.getInstance()
    private val collection = f_store.collection("rooms")
    private val f_db = FirebaseDatabase.getInstance()
    fun loginUser(email: String, password: String, context: Context) {
        if (email == "" || password == "") {
            Toast.makeText(context, "Password or Email Field Cannot Be Empty", Toast.LENGTH_LONG)
                .show()
        } else {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isComplete) {
                        Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()
                        context.startActivity(Intent(context, MainActivity::class.java))
                    } else {
                        Toast.makeText(
                            context,
                            "Invalid Email or Password\n Please try again",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

    }

    fun regUser(email: String, password: String, context: Context) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isComplete) {
                    Toast.makeText(context, "User Registered Successfully", Toast.LENGTH_LONG)
                        .show()
                    context.startActivity(Intent(context, MainActivity::class.java))
                } else {
                    Toast.makeText(
                        context,
                        "Registration Failed\n Please try again",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}