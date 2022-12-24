package com.adiupd123.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity(), OnClickListener {
    private var mAuth: FirebaseAuth? = null

    private lateinit var pwdResetEmailEditText: EditText
    private lateinit var pwdResetButton: Button
    private lateinit var pwdResetSignInButton: Button
    private var email: String ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        mAuth = FirebaseAuth.getInstance()

        pwdResetEmailEditText = findViewById(R.id.pwdResetEmail_editText)
        pwdResetButton = findViewById(R.id.pwdReset_button)
        pwdResetSignInButton = findViewById(R.id.pwdResetSignIn_button)

        pwdResetButton.setOnClickListener(this)
        pwdResetSignInButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.pwdReset_button -> {
                val email = pwdResetEmailEditText.text.toString().trim()
                mAuth!!.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(applicationContext, "Password reset email has been sent.", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(applicationContext, task.exception!!.message, Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            R.id.pwdResetSignIn_button -> startActivity(Intent(applicationContext, LoginActivity::class.java))
        }
    }
}