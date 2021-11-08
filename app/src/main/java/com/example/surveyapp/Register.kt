package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val register_email = findViewById<EditText>(R.id.et_register_email)
        val register_password = findViewById<EditText>(R.id.et_register_password)

        val btn_register = findViewById<Button>(R.id.btn_register_Register)


        btn_register.setOnClickListener {
            when {
                TextUtils.isEmpty(register_email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Register,
                        "Enter e-mail",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(register_password.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Register,
                        "Enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String = register_email.text.toString().trim { it <= ' ' }
                    val password: String = register_password.text.toString().trim { it <= ' ' }
                    //creating instance to firebase
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    Toast.makeText(
                                        this@Register,
                                        "You are registered succesfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val intent = Intent(this@Register, MainActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this@Register,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                }
            }
        }

        val btn_ShowHide = findViewById<Button>(R.id.btn_register_Register_passwordHideShow)
        val pwd = findViewById<EditText>(R.id.et_register_password)
        btn_ShowHide.setOnClickListener {
            if (btn_ShowHide.text.toString().equals("SHOW")) {
                pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
                btn_ShowHide.text = "HIDE"
            } else {
                pwd.transformationMethod = PasswordTransformationMethod.getInstance()
                btn_ShowHide.text = "SHOW"
            }
        }
        val tv_switchToLogIn = findViewById<TextView>(R.id.tv_register_swtichToLogIn)
        tv_switchToLogIn.setOnClickListener {
            onBackPressed()
        }

        val tv_goToMainScreen = findViewById<TextView>(R.id.tv_register_backToMainScreen)
        tv_goToMainScreen.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}