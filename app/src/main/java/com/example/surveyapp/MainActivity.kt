package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnSwitchToSurvey = findViewById<Button>(R.id.btnSwitchToSurveys)
        btnSwitchToSurvey.setOnClickListener {
            val intent = Intent(this, showingSurveys::class.java)
            startActivity(intent)
        }
        val btnAddSurveys = findViewById<Button>(R.id.btnSwitchToAddSurveys)
        btnAddSurveys.setOnClickListener {
            val intent = Intent(this, addingSurveys::class.java)
            startActivity(intent)
        }
        val btnLogIn = findViewById<Button>(R.id.btnSwitchToLogIn)
        btnLogIn.setOnClickListener {
            val intent = Intent(this, loggingIn::class.java)
            startActivity(intent)
        }

    }
}