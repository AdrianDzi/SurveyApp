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
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class loggingIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging_in)

        val btn_login = findViewById<Button>(R.id.btn_login_login)
        val et_login_email = findViewById<EditText>(R.id.et_login_email)
        val et_login_password = findViewById<EditText>(R.id.et_login_password)

        btn_login.setOnClickListener {
            when {
                TextUtils.isEmpty(et_login_email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@loggingIn,
                        "Enter e-mail",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(et_login_password.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@loggingIn,
                        "Enter password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String = et_login_email.text.toString().trim { it <= ' ' }
                    val password: String = et_login_password.text.toString().trim { it <= ' ' }
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this@loggingIn,
                                    "You are logged in succesfully!",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent = Intent(this@loggingIn, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra(
                                    "user_id",
                                    FirebaseAuth.getInstance().currentUser!!.uid
                                )
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this@loggingIn,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                }
            }
        }


        val btn_goToRegister = findViewById<TextView>(R.id.tv_login_register)
        btn_goToRegister.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        val btn_ShowHide = findViewById<Button>(R.id.btn_login_passwordHideShow)
        val pwd = findViewById<EditText>(R.id.et_login_password)
        btn_ShowHide.setOnClickListener {
            if (btn_ShowHide.text.toString().equals("SHOW")) {
                pwd.transformationMethod = HideReturnsTransformationMethod.getInstance()
                btn_ShowHide.text = "HIDE"
            } else {
                pwd.transformationMethod = PasswordTransformationMethod.getInstance()
                btn_ShowHide.text = "SHOW"
            }
        }


    }
}