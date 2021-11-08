package com.example.surveyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoggedAsAnonymous : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_as_anonymus)

        firebaseAuth = FirebaseAuth.getInstance()

        findViewById<TextView>(R.id.tv_anonymousUserId).text = firebaseAuth.currentUser?.uid
        findViewById<Button>(R.id.btn_anonymousSignOut).setOnClickListener {
            signOut()
        }
    }

    private fun signOut() {
        firebaseAuth.signOut()
        finish()
        onBackPressed()
        Toast.makeText(
            this@LoggedAsAnonymous,
            "LoggedOut from anonymous acc",
            Toast.LENGTH_SHORT
        ).show()
    }
}