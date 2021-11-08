package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnToLogOut = findViewById<Button>(R.id.btn_user_logOut)
        val userId = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")
        val tv_user_id = findViewById<TextView>(R.id.tv_user_id)
        val tv_email_id = findViewById<TextView>(R.id.tv_email_id)
        val tv_loggedSuccesfullText = findViewById<TextView>(R.id.tv_loggedSuccesfullText)
        val btn_anonymus = findViewById<Button>(R.id.btn_user_anonymous)

        if (userId == null && emailId == null) {
            btn_anonymus.setVisibility(View.VISIBLE)
            tv_loggedSuccesfullText.text = "you are not logged in"
            btnToLogOut.text = "Log In"
        } else {
            btn_anonymus.setVisibility(View.INVISIBLE)
            tv_user_id.text = "User ID :: $userId"
            tv_email_id.text = "Email ID :: $emailId"
            btnToLogOut.text = "Log Out"
        }
        btnToLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@MainActivity, loggingIn::class.java))
            finish()
        }

        firebaseAuth = FirebaseAuth.getInstance()
        btn_anonymus.setOnClickListener {
            anonymousAuth()
        }
    }

    private fun anonymousAuth() {
        firebaseAuth.signInAnonymously()
            .addOnSuccessListener {
                startActivity(Intent(this, LoggedAsAnonymous::class.java))
            }
            .addOnFailureListener {
                Toast.makeText(
                    this@MainActivity,
                    "can't log in as anonymous",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}