package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnToLogOut = findViewById<Button>(R.id.btn_user_logOut)
        val userId = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")
        val tv_user_id = findViewById<TextView>(R.id.tv_user_id)
        val tv_email_id = findViewById<TextView>(R.id.tv_email_id)
        val tv_loggedSuccesfullText = findViewById<TextView>(R.id.tv_loggedSuccesfullText)

        if(userId== null && emailId == null){
            tv_loggedSuccesfullText.text = "you are not logged in"
            btnToLogOut.text = "Log In"
        }else{
            tv_user_id.text = "User ID :: $userId"
            tv_email_id.text = "Email ID :: $emailId"
            btnToLogOut.text = "Log Out"
        }
        btnToLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@MainActivity, loggingIn::class.java))
            finish()
        }



//        btnToLogOut.setOnClickListener {
//            FirebaseAuth.getInstance().signOut()
//            startActivity(Intent(this@MainActivity, loggingIn::class.java))
//            finish()
//        }

//        btnLogIn.setOnClickListener {
//            val intent = Intent(this, loggingIn::class.java)
//            startActivity(intent)
//        }
//        val btnSwitchToRegister = findViewById<Button>(R.id.btnSwitchToRegister)
//        btnSwitchToRegister.setOnClickListener {
//            val intent = Intent(this, Register::class.java)
//            startActivity(intent)
//        }


    }
}